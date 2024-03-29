package pingball.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import physics.Circle;
import physics.Vect;
import pingball.board.Edge;
import pingball.proto.BallMessage;
import pingball.proto.ConnectWallMessage;
import pingball.proto.DisconnectWallMessage;
import pingball.proto.Message;

/**
 * Tracks joins amongst boards.
 * 
 * This class is the server-side equivalent of {@link Board}. It contains all
 * the logic and handles edge cases.
 * 
 * Instances of this class are not thread-safe.
 */
public class BoardLinks {
	/** Maps (board name, edge) to the name of the connected board. */
	private final HashMap<String, HashMap<Edge, String>> links;
	/** The names of the boards of the connected clients. */
	private final HashSet<String> liveClients;
	
	// Rep invariant:
	//   everything is non-null
	//   for every edge, there is an edge in the opposite direction
	// Thread safety:
	//   instances are not thread-safe, and must be contained to the board links
	//   thread
	
	/**
	 * Creates an instance that has no links set up.
	 */
	public BoardLinks() {
		links = new HashMap<String, HashMap<Edge, String>>();
		liveClients = new HashSet<String>();
		
		assert checkRep();
	}
	
	/**
	 * Joins two boards horizontally.
	 * 
	 * @param leftName name of the board whose right wall will be joined
	 * @param rightName name of the board whose left wall will be joined
	 * @return a list of messages that the server should send to the clients
	 */
	public List<TargetedMessage> horizontalJoin(String leftName,
			String rightName) {
		assert leftName != null;
		assert rightName != null;
		
		ArrayList<TargetedMessage> messages = new ArrayList<TargetedMessage>();		
		addLink(leftName, Edge.RIGHT, rightName, messages);
		addLink(rightName, Edge.LEFT, leftName, messages);
		
		assert checkRep();
		return messages;
	}
	
	/**
	 * Joins two boards vertically.
	 * 
	 * @param topName name of the board whose bottom wall will be joined
	 * @param bottomName name of the board whose top wall will be joined
	 * @return a list of messages that the server should send to the clients
	 */
	public List<TargetedMessage> verticalJoin(String topName,
			String bottomName) {
		assert topName != null;
		assert bottomName != null;
		
		ArrayList<TargetedMessage> messages = new ArrayList<TargetedMessage>();
		addLink(topName, Edge.BOTTOM, bottomName, messages);
		addLink(bottomName, Edge.TOP, topName, messages);
		
		assert checkRep();
		return messages;
	}
	
	/**
	 * Acknowledges that a client has disconnected from the server.
	 * 
	 * Removes all the links from/to the client's board board.
	 * 
	 * @param boardName the name of the client's board
	 * @return a list of messages that the server should send to the clients
	 */
	public List<TargetedMessage> disconnected(String boardName) {
		assert boardName != null;
		
		ArrayList<TargetedMessage> messages = new ArrayList<TargetedMessage>();
		liveClients.remove(boardName);
		HashMap<Edge, String> edgeMap = links.remove(boardName);
		if (edgeMap == null)
			return messages;
		for (Entry<Edge, String> entry : edgeMap.entrySet()) {
			String to = entry.getValue();
			if (!to.equals(boardName)) {
				Edge targetEdge = entry.getKey().opposite(); 
				removeLink(to, targetEdge, boardName);
				if (!liveClients.contains(to))
					continue;
				messages.add(new TargetedMessage(
						new DisconnectWallMessage(targetEdge), to));
			}
		}
		
		assert checkRep();
		return messages;
	}
	
	/**
	 * Acknowledges that a board has connected to the server.
	 * 
	 * @param boardName the name of the client's board
	 */
	public List<TargetedMessage> connected(String boardName) {
		assert boardName != null;
		
		ArrayList<TargetedMessage> messages = new ArrayList<TargetedMessage>();
		liveClients.add(boardName);
		HashMap<Edge, String> edgeMap = links.get(boardName);
		if (edgeMap == null)
			return messages;
		for (Entry<Edge, String> entry : edgeMap.entrySet()) {
			String to = entry.getValue();
			Edge edge = entry.getKey();
			if (!liveClients.contains(to))
				continue;				
			messages.add(new TargetedMessage(new ConnectWallMessage(to, edge),
					boardName));
			if (!to.equals(boardName)) {
				Edge targetEdge = entry.getKey().opposite(); 
				messages.add(new TargetedMessage(
						new ConnectWallMessage(boardName, targetEdge), to));
			}
		}
		
		assert checkRep();
		return messages;
	}
	
	/**
	 * Computes the destination of teleporting a ball.
	 * 
	 * @param from the name of the board that the ball is coming from
	 * @param message the {@link BallMessage} containing information about the
	 *   ball
	 * @return a {@link TargetedMessage} that should be sent by the server
	 */
	public TargetedMessage teleport(String from, BallMessage source) {
		HashMap<Edge, String> edgeMap = links.get(from);
		if (edgeMap != null) {
			Edge edge = source.getEdge();
			final String to = edgeMap.get(edge);
			if (to != null) {
				Circle shape = source.getShape();
				Vect center = shape.getCenter();
				Vect targetCenter;
				if (edge.isHorizontal()) {
					targetCenter = new Vect(center.x(),
							20.0 - center.y());
				} else {
					targetCenter = new Vect(20.0 - center.x(),
							center.y());					
				}
				final BallMessage target = new BallMessage(edge.opposite(),
						new Circle(targetCenter, shape.getRadius()),
						source.getVelocity());
				return new TargetedMessage(target, to);
			}
		}

		// A client sent the ball to the sever, but the client at the other side
		// of the wall left. We'll send back the ball to the original client.
		final String to = from;
		Edge edge = source.getEdge();
		Vect velocity = source.getVelocity();
		Vect targetVelocity;
		if (edge.isHorizontal())
			targetVelocity = new Vect(velocity.x(), -velocity.y());
		else
			targetVelocity = new Vect(-velocity.x(), velocity.y());			
		final BallMessage target = new BallMessage(edge, source.getShape(),
				targetVelocity);
		
		TargetedMessage message = new TargetedMessage(target, to); 
		assert checkRep();		
		return message;
	}
	
	
	/** Pairs a board name with a Message for the client owning the board. */
	public static class TargetedMessage {
		private final Message message;
		private final String boardName;
		
		public TargetedMessage(Message message, String boardName) {
			assert message != null;
			assert boardName != null;
			this.boardName = boardName;
			this.message = message;
		}
		public String getBoardName() {
			return boardName;
		}
		public Message getMessage() {
			return message;
		}
	}	

	/**
	 * Creates a directed link.
	 *
	 * The caller is responsible for adding the opposite link.
	 * 
	 * @param from the name of the board where the link starts
	 * @param edge the edge where the link starts
	 * @param to the name of the board where the link ends
	 */
	private void addLink(String from, Edge edge, String to,
			List<TargetedMessage> messages) {
		assert from != null;
		assert edge != null;
		assert to != null;
		
		HashMap<Edge, String> edgeMap = links.get(from);
		if (edgeMap == null) {
			edgeMap = new HashMap<Edge, String>();
			links.put(from, edgeMap);
		} else {
			String oldTo = edgeMap.get(edge);
			if (to.equals(oldTo)) {
				// The early return is better than sending a
				// DisconnectWallMessage and then sending a ConnectWallMessage.
				// The latter message could be arbitrarily delayed, creating a
				// period of time when the client's wall bounces balls.
				return;
			}
			if (oldTo != null) {
				Edge oppositeEdge = edge.opposite();
				removeLink(oldTo, oppositeEdge, from);
				if (liveClients.contains(from) && liveClients.contains(oldTo)) {
					messages.add(new TargetedMessage(
							new DisconnectWallMessage(oppositeEdge), oldTo));
				}
			}
		}
		edgeMap.put(edge, to);
		if (liveClients.contains(from) && liveClients.contains(to)) {
			messages.add(new TargetedMessage(
					new ConnectWallMessage(to, edge), from));
		}
	}

	/**
	 * Removes a directed link.
	 * 
	 * The caller is responsible for removing the opposite link.
	 * 
	 * @param from the name of the board where the link starts
	 * @param edge the edge where the link starts
	 * @param to the name of the board where the link ends
	 */
	private void removeLink(String from, Edge edge, String to) {
		assert from != null;
		assert edge != null;
		assert to != null;
		
		HashMap<Edge, String> edgeMap = links.get(from);
		assert edgeMap.get(edge).equals(to);
		edgeMap.remove(edge);
		if (edgeMap.isEmpty())
			links.remove(from);
	}
	
	/** Checks this instance's representation invariant. */
	private boolean checkRep() {
		if (links == null)
			return false;
		if (liveClients == null)
			return false;
		
		for (String liveClient : liveClients) {
			if (liveClient == null)
				return false;
		}
		
		for (Entry<String, HashMap<Edge, String>> entry : links.entrySet()) {
			String from = entry.getKey();
			if (from == null)
				return false;
			HashMap<Edge, String> edgeMap = entry.getValue();
			for (Entry<Edge, String> edgeEntry : edgeMap.entrySet()) {
				Edge edge = edgeEntry.getKey();
				if (edge == null)
					return false;
				String to = edgeEntry.getValue();
				if (to == null)
					return false;
				
				HashMap<Edge, String> reverseMap = links.get(to);
				if (reverseMap == null)
					return false;
				String reverseTo = reverseMap.get(edge.opposite());
				if (!reverseTo.equals(from))
					return false;
			}
		}
		return true;
	}
}
