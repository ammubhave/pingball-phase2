package pingball.server;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import physics.Circle;
import physics.Vect;
import pingball.board.Edge;
import pingball.proto.BallMessage;
import pingball.proto.ConnectWallMessage;
import pingball.proto.DisconnectWallMessage;

public class BoardLinksTest {
	private BoardLinks boardLinks;
	
	@Before
	public void setUp() throws Exception {
		boardLinks = new BoardLinks();
	}

	@Test
	public void testJoinlessConnectionsDisconnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.connected("player2");
		assertEquals(0, messages.size());
	
		messages = boardLinks.disconnected("player2");
		assertEquals(0, messages.size());

		messages = boardLinks.disconnected("player1");
		assertEquals(0, messages.size());
	}
	
	@Test
	public void testRepeatedDisconnections() {
		List<BoardLinks.TargetedMessage> messages;

		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.disconnected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.disconnected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.disconnected("player1");
		assertEquals(0, messages.size());
	}	

	@Test
	public void testJoinBeforeHorizontalConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(0, messages.size());
		
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.connected("player2");
		assertEquals(2, messages.size());
		
		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player2", message1.getBoardName());
		ConnectWallMessage join1 = (ConnectWallMessage)message1.getMessage();
		assertEquals("player1", join1.getNeighborName());
		assertEquals(Edge.LEFT, join1.getEdge());

		BoardLinks.TargetedMessage message2 = messages.get(1);
		assertEquals("player1", message2.getBoardName());
		ConnectWallMessage join2 = (ConnectWallMessage)message2.getMessage();
		assertEquals("player2", join2.getNeighborName());
		assertEquals(Edge.RIGHT, join2.getEdge());
	}
	
	@Test
	public void testJoinBeforeVerticalConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.verticalJoin("player1", "player2");
		assertEquals(0, messages.size());
		
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.connected("player2");
		assertEquals(2, messages.size());
		
		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player2", message1.getBoardName());
		ConnectWallMessage join1 = (ConnectWallMessage)message1.getMessage();
		assertEquals("player1", join1.getNeighborName());
		assertEquals(Edge.TOP, join1.getEdge());

		BoardLinks.TargetedMessage message2 = messages.get(1);
		assertEquals("player1", message2.getBoardName());
		ConnectWallMessage join2 = (ConnectWallMessage)message2.getMessage();
		assertEquals("player2", join2.getNeighborName());
		assertEquals(Edge.BOTTOM, join2.getEdge());
	}
	
	@Test
	public void testDisconnectAfterHorizontalJoin() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(0, messages.size());
		
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.connected("player2");
		assertEquals(2, messages.size());
		// Actual contents tested in testJoinBeforeHorizontalConnections

		messages = boardLinks.disconnected("player2");
		assertEquals(1, messages.size());
		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player1", message1.getBoardName());
		DisconnectWallMessage part1 =
				(DisconnectWallMessage)message1.getMessage();
		assertEquals(Edge.RIGHT, part1.getEdge());

		messages = boardLinks.disconnected("player1");
		assertEquals(0, messages.size());
	}

	@Test
	public void testDisconnectAfterVerticalJoin() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.verticalJoin("player1", "player2");
		assertEquals(0, messages.size());
		
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.connected("player2");
		assertEquals(2, messages.size());
		// Actual contents tested in testJoinBeforeVerticalConnections

		messages = boardLinks.disconnected("player2");
		assertEquals(1, messages.size());
		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player1", message1.getBoardName());
		DisconnectWallMessage part1 =
				(DisconnectWallMessage)message1.getMessage();
		assertEquals(Edge.BOTTOM, part1.getEdge());

		messages = boardLinks.disconnected("player1");
		assertEquals(0, messages.size());
	}	
	
	@Test
	public void testHorizontalJoinAfterConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player2");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player3");
		assertEquals(0, messages.size());
		
		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(2, messages.size());

		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player1", message1.getBoardName());
		ConnectWallMessage join1 = (ConnectWallMessage)message1.getMessage();
		assertEquals("player2", join1.getNeighborName());
		assertEquals(Edge.RIGHT, join1.getEdge());
		
		BoardLinks.TargetedMessage message2 = messages.get(1);
		assertEquals("player2", message2.getBoardName());
		ConnectWallMessage join2 = (ConnectWallMessage)message2.getMessage();
		assertEquals("player1", join2.getNeighborName());
		assertEquals(Edge.LEFT, join2.getEdge());
	}	

	@Test
	public void testVerticalJoinAfterConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player2");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player3");
		assertEquals(0, messages.size());
		
		messages = boardLinks.verticalJoin("player1", "player2");
		assertEquals(2, messages.size());

		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player1", message1.getBoardName());
		ConnectWallMessage join1 = (ConnectWallMessage)message1.getMessage();
		assertEquals("player2", join1.getNeighborName());
		assertEquals(Edge.BOTTOM, join1.getEdge());
		
		BoardLinks.TargetedMessage message2 = messages.get(1);
		assertEquals("player2", message2.getBoardName());
		ConnectWallMessage join2 = (ConnectWallMessage)message2.getMessage();
		assertEquals("player1", join2.getNeighborName());
		assertEquals(Edge.TOP, join2.getEdge());
	}
	
	@Test
	public void testHorizontalBreakingJoinAfterConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player2");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player3");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player4");
		assertEquals(0, messages.size());
		
		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(2, messages.size());
		// Actual contents tested in testHorizontalJoinAfterConnections
		messages = boardLinks.horizontalJoin("player4", "player3");
		assertEquals(2, messages.size());
		// Actual contents tested in testVerticalJoinAfterConnections
		
		messages = boardLinks.horizontalJoin("player1", "player3");
		assertEquals(4, messages.size());
		
		BoardLinks.TargetedMessage message2 = messages.get(1);
		assertEquals("player1", message2.getBoardName());
		ConnectWallMessage join1 = (ConnectWallMessage)message2.getMessage();
		assertEquals("player3", join1.getNeighborName());
		assertEquals(Edge.RIGHT, join1.getEdge());
		
		BoardLinks.TargetedMessage message4 = messages.get(3);
		assertEquals("player3", message4.getBoardName());
		ConnectWallMessage join2 = (ConnectWallMessage)message4.getMessage();
		assertEquals("player1", join2.getNeighborName());
		assertEquals(Edge.LEFT, join2.getEdge());

		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player2", message1.getBoardName());
		DisconnectWallMessage part1 =
				(DisconnectWallMessage)message1.getMessage();
		assertEquals(Edge.LEFT, part1.getEdge());
		
		BoardLinks.TargetedMessage message3 = messages.get(2);
		assertEquals("player4", message3.getBoardName());
		DisconnectWallMessage part2 =
				(DisconnectWallMessage)message3.getMessage();
		assertEquals(Edge.RIGHT, part2.getEdge());
	}

	@Test
	public void testVerticalBreakingJoinAfterConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player2");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player3");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player4");
		assertEquals(0, messages.size());
		
		messages = boardLinks.verticalJoin("player1", "player2");
		assertEquals(2, messages.size());
		// Actual contents tested in testVerticalJoinAfterConnections
		messages = boardLinks.verticalJoin("player4", "player3");
		assertEquals(2, messages.size());
		// Actual contents tested in testVerticalJoinAfterConnections
		
		messages = boardLinks.verticalJoin("player1", "player3");
		assertEquals(4, messages.size());
		
		BoardLinks.TargetedMessage message2 = messages.get(1);
		assertEquals("player1", message2.getBoardName());
		ConnectWallMessage join1 = (ConnectWallMessage)message2.getMessage();
		assertEquals("player3", join1.getNeighborName());
		assertEquals(Edge.BOTTOM, join1.getEdge());
		
		BoardLinks.TargetedMessage message4 = messages.get(3);
		assertEquals("player3", message4.getBoardName());
		ConnectWallMessage join2 = (ConnectWallMessage)message4.getMessage();
		assertEquals("player1", join2.getNeighborName());
		assertEquals(Edge.TOP, join2.getEdge());

		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player2", message1.getBoardName());
		DisconnectWallMessage part1 =
				(DisconnectWallMessage)message1.getMessage();
		assertEquals(Edge.TOP, part1.getEdge());
		
		BoardLinks.TargetedMessage message3 = messages.get(2);
		assertEquals("player4", message3.getBoardName());
		DisconnectWallMessage part2 =
				(DisconnectWallMessage)message3.getMessage();
		assertEquals(Edge.BOTTOM, part2.getEdge());
	}
	
	@Test
	public void testHorizontalRepeatedJoinAfterConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player2");
		assertEquals(0, messages.size());
		
		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(2, messages.size());
		// Actual contents tested in testHorizontalJoinAfterConnections
		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(0, messages.size());
	}

	@Test
	public void testVerticalRepeatedJoinAfterConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());
		messages = boardLinks.connected("player2");
		assertEquals(0, messages.size());
		
		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(2, messages.size());
		// Actual contents tested in testHorizontalJoinAfterConnections
		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(0, messages.size());
	}

	@Test
	public void testHorizontalJoinBetweenConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.horizontalJoin("player1", "player2");
		assertEquals(0, messages.size());

		messages = boardLinks.connected("player2");
		assertEquals(2, messages.size());

		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player2", message1.getBoardName());
		ConnectWallMessage join1 = (ConnectWallMessage)message1.getMessage();
		assertEquals("player1", join1.getNeighborName());
		assertEquals(Edge.LEFT, join1.getEdge());

		BoardLinks.TargetedMessage message2 = messages.get(1);
		assertEquals("player1", message2.getBoardName());
		ConnectWallMessage join2 = (ConnectWallMessage)message2.getMessage();
		assertEquals("player2", join2.getNeighborName());
		assertEquals(Edge.RIGHT, join2.getEdge());
	}	

	@Test
	public void testVerticalJoinBetweenConnections() {
		List<BoardLinks.TargetedMessage> messages;
		messages = boardLinks.connected("player1");
		assertEquals(0, messages.size());

		messages = boardLinks.verticalJoin("player1", "player2");
		assertEquals(0, messages.size());

		messages = boardLinks.connected("player2");
		assertEquals(2, messages.size());

		BoardLinks.TargetedMessage message1 = messages.get(0);
		assertEquals("player2", message1.getBoardName());
		ConnectWallMessage join1 = (ConnectWallMessage)message1.getMessage();
		assertEquals("player1", join1.getNeighborName());
		assertEquals(Edge.TOP, join1.getEdge());

		BoardLinks.TargetedMessage message2 = messages.get(1);
		assertEquals("player1", message2.getBoardName());
		ConnectWallMessage join2 = (ConnectWallMessage)message2.getMessage();
		assertEquals("player2", join2.getNeighborName());
		assertEquals(Edge.BOTTOM, join2.getEdge());
	}
	
	@Test
	public void testTeleportLeftToRight() {
		boardLinks.horizontalJoin("player1", "player2");
		boardLinks.connected("player1");
		boardLinks.connected("player2");
		
		BallMessage ball = new BallMessage("testball", Edge.LEFT, new Circle(0.12, 5, 0.25),
				new Vect(-0.2, 0.3));
		BoardLinks.TargetedMessage message = boardLinks.teleport("player2",
				ball);
		assertEquals("player1", message.getBoardName());
		BallMessage teleported = (BallMessage)message.getMessage();
		assertEquals(Edge.RIGHT, teleported.getEdge());
		assertEquals(20 - 0.12, teleported.getShape().getCenter().x(), 0.00001);
		assertEquals(5, teleported.getShape().getCenter().y(), 0.00001);
		assertEquals(0.25, teleported.getShape().getRadius(), 0.00001);
		assertEquals(-0.2, teleported.getVelocity().x(), 0.00001);
		assertEquals(0.3, teleported.getVelocity().y(), 0.00001);
	}

	@Test
	public void testTeleportRightToLeft() {
		boardLinks.horizontalJoin("player1", "player2");
		boardLinks.connected("player1");
		boardLinks.connected("player2");
		
		BallMessage ball = new BallMessage("testball", Edge.RIGHT,
				new Circle(20 - 0.13, 7, 0.25), new Vect(0.2, 0.3));
		BoardLinks.TargetedMessage message = boardLinks.teleport("player1",
				ball);
		assertEquals("player2", message.getBoardName());
		BallMessage teleported = (BallMessage)message.getMessage();
		assertEquals(Edge.LEFT, teleported.getEdge());
		assertEquals(0.13, teleported.getShape().getCenter().x(), 0.00001);
		assertEquals(7, teleported.getShape().getCenter().y(), 0.00001);
		assertEquals(0.25, teleported.getShape().getRadius(), 0.00001);
		assertEquals(0.2, teleported.getVelocity().x(), 0.00001);
		assertEquals(0.3, teleported.getVelocity().y(), 0.00001);
	}
	
	@Test
	public void testTeleportTopToBottom() {
		boardLinks.verticalJoin("player1", "player2");
		boardLinks.connected("player1");
		boardLinks.connected("player2");
		
		BallMessage ball = new BallMessage("testball", Edge.TOP, new Circle(6, 0.14, 0.25),
				new Vect(0.2, -0.3));
		BoardLinks.TargetedMessage message = boardLinks.teleport("player2",
				ball);
		assertEquals("player1", message.getBoardName());
		BallMessage teleported = (BallMessage)message.getMessage();
		assertEquals(Edge.BOTTOM, teleported.getEdge());
		assertEquals(6, teleported.getShape().getCenter().x(), 0.00001);
		assertEquals(20 - 0.14, teleported.getShape().getCenter().y(), 0.00001);
		assertEquals(0.25, teleported.getShape().getRadius(), 0.00001);
		assertEquals(0.2, teleported.getVelocity().x(), 0.00001);
		assertEquals(-0.3, teleported.getVelocity().y(), 0.00001);
	}
	

	@Test
	public void testTeleportBottomToTop() {
		boardLinks.verticalJoin("player1", "player2");
		boardLinks.connected("player1");
		boardLinks.connected("player2");
		
		BallMessage ball = new BallMessage("testball", Edge.BOTTOM,
				new Circle(8, 20 - 0.15, 0.25), new Vect(0.2, 0.3));
		BoardLinks.TargetedMessage message = boardLinks.teleport("player1",
				ball);
		assertEquals("player2", message.getBoardName());
		BallMessage teleported = (BallMessage)message.getMessage();
		assertEquals(Edge.TOP, teleported.getEdge());
		assertEquals(8, teleported.getShape().getCenter().x(), 0.00001);
		assertEquals(0.15, teleported.getShape().getCenter().y(), 0.00001);
		assertEquals(0.25, teleported.getShape().getRadius(), 0.00001);
		assertEquals(0.2, teleported.getVelocity().x(), 0.00001);
		assertEquals(0.3, teleported.getVelocity().y(), 0.00001);
	}

	@Test
	public void testTeleportLeftReflect() {
		boardLinks.horizontalJoin("player1", "player2");
		boardLinks.connected("player1");
		boardLinks.connected("player2");
		
		BallMessage ball = new BallMessage("testball", Edge.LEFT, new Circle(0.12, 5, 0.25),
				new Vect(-0.2, 0.3));
		BoardLinks.TargetedMessage message = boardLinks.teleport("player1",
				ball);
		assertEquals("player1", message.getBoardName());
		BallMessage reflected = (BallMessage)message.getMessage();
		assertEquals(Edge.LEFT, reflected.getEdge());
		assertEquals(0.12, reflected.getShape().getCenter().x(), 0.00001);
		assertEquals(5, reflected.getShape().getCenter().y(), 0.00001);
		assertEquals(0.25, reflected.getShape().getRadius(), 0.00001);
		assertEquals(0.2, reflected.getVelocity().x(), 0.00001);
		assertEquals(0.3, reflected.getVelocity().y(), 0.00001);
	}

	@Test
	public void testTeleportRightReflect() {
		boardLinks.horizontalJoin("player1", "player2");
		boardLinks.connected("player1");
		boardLinks.connected("player2");
		
		BallMessage ball = new BallMessage("testball", Edge.RIGHT,
				new Circle(20 - 0.13, 7, 0.25), new Vect(0.2, 0.3));
		BoardLinks.TargetedMessage message = boardLinks.teleport("player2",
				ball);
		assertEquals("player2", message.getBoardName());
		BallMessage reflected = (BallMessage)message.getMessage();
		assertEquals(Edge.RIGHT, reflected.getEdge());
		assertEquals(20 - 0.13, reflected.getShape().getCenter().x(), 0.00001);
		assertEquals(7, reflected.getShape().getCenter().y(), 0.00001);
		assertEquals(0.25, reflected.getShape().getRadius(), 0.00001);
		assertEquals(-0.2, reflected.getVelocity().x(), 0.00001);
		assertEquals(0.3, reflected.getVelocity().y(), 0.00001);
	}
	
	@Test
	public void testTeleportTopReflect() {
		boardLinks.verticalJoin("player1", "player2");
		boardLinks.connected("player1");
		boardLinks.connected("player2");
		
		BallMessage ball = new BallMessage("testball", Edge.TOP, new Circle(6, 0.14, 0.25),
				new Vect(0.2, -0.3));
		BoardLinks.TargetedMessage message = boardLinks.teleport("player1",
				ball);
		assertEquals("player1", message.getBoardName());
		BallMessage reflected = (BallMessage)message.getMessage();
		assertEquals(Edge.TOP, reflected.getEdge());
		assertEquals(6, reflected.getShape().getCenter().x(), 0.00001);
		assertEquals(0.14, reflected.getShape().getCenter().y(), 0.00001);
		assertEquals(0.25, reflected.getShape().getRadius(), 0.00001);
		assertEquals(0.2, reflected.getVelocity().x(), 0.00001);
		assertEquals(0.3, reflected.getVelocity().y(), 0.00001);
	}
	

	@Test
	public void testTeleportBottomReflect() {
		boardLinks.verticalJoin("player1", "player2");
		boardLinks.connected("player1");
		boardLinks.connected("player2");
		
		BallMessage ball = new BallMessage("testball", Edge.BOTTOM,
				new Circle(8, 20 - 0.15, 0.25), new Vect(0.2, 0.3));
		BoardLinks.TargetedMessage message = boardLinks.teleport("player2",
				ball);
		assertEquals("player2", message.getBoardName());
		BallMessage reflected = (BallMessage)message.getMessage();
		assertEquals(Edge.BOTTOM, reflected.getEdge());
		assertEquals(8, reflected.getShape().getCenter().x(), 0.00001);
		assertEquals(20 - 0.15, reflected.getShape().getCenter().y(), 0.00001);
		assertEquals(0.25, reflected.getShape().getRadius(), 0.00001);
		assertEquals(0.2, reflected.getVelocity().x(), 0.00001);
		assertEquals(-0.3, reflected.getVelocity().y(), 0.00001);
	}	
}
