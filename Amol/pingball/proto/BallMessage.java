package pingball.proto;

import java.awt.geom.Point2D;

import physics.Circle;
import physics.Vect;
import pingball.board.Edge;

/**
 * Sent when a ball is teleported around.
 */
public class BallMessage extends Message {
	/** The board edge neighboring the wall that was connected. */
	private final Edge edge;	
	/** The center and radius of the ball that is teleported. */
	private final Circle shape;	
	/** The velocity of the ball that is teleported. */
	private final Vect velocity;
	
	// Rep invariant:
	//   everything is non-null
	// Thread safety:
	//   all fields are immutable, just like for Message
	
	/**
	 * Creates a message for teleporting a ball.
	 * 
	 * @param edge the edge of the wall where the ball leaves / arrives
	 * @param center the ball's center
	 * @param velocity the ball's velocity
	 */
	public BallMessage(Edge edge, Circle shape, Vect velocity) {
		assert edge != null;
		assert shape != null;
		assert velocity != null;
		this.edge = edge;
		this.shape = shape;
		this.velocity = velocity;
	}

	/**
	 * The board edge neighboring the wall that was connected.
	 * 
	 * @return the board edge neighboring the wall that was connected
	 */
	public Edge getEdge() {
		return edge;
	}

	public Circle getShape() {
		return shape;
	}
	
	public Vect getVelocity() {
		return velocity;
	}
	
	@Override
	protected String name() {
		return NAME;
	}
	
	@Override
	public String toLine() {
		return NAME + " " + edge.name() + " " +
				shape.getCenter().x() + " " + shape.getCenter().y() + " " +
				shape.getRadius() + " " +
				velocity.x() + " " + velocity.y();
	}
		
	// NOTE: The stuff below is package-private on purpose.
	
	/** This message's name. */
	static final String NAME = "ball";

	/**
	 * Creates a message from a line of text received from a socket.
	 * @param tokens strings that were separated by spaces on the line
	 */
	BallMessage(String[] tokens) {
		assert tokens[0].equals(NAME);
		assert tokens.length >= 7;
		try {
			this.edge = Edge.valueOf(tokens[1]);

			double cx = Double.parseDouble(tokens[2]);
			double cy = Double.parseDouble(tokens[3]);
			double radius = Double.parseDouble(tokens[4]);
			double vx = Double.parseDouble(tokens[5]);
			double vy = Double.parseDouble(tokens[6]);
			
			this.shape = new Circle(new Point2D.Double(cx, cy), radius);
			this.velocity = new Vect(vx, vy);
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException("Invalid version number", e);
		}
	}
}