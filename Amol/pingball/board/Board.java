package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.Vect;
import pingball.proto.BallMessage;
import pingball.proto.ConnectWallMessage;
import pingball.proto.DisconnectWallMessage;
import pingball.proto.Message;

/**
 * Represents a pingball board.
 * 
 * Thread Safety:
 * Any accesses to the functions on board is synchronized.
 * Also the methods which print the board to console and which emulates time first
 * synchronize on the board object first so they both can't run at the same time.
 */
public class Board {
    public static final int size = 20 + 2; 
    private List<Gadget> gadgets;
    private List<Ball> balls;
    private double gravity;
    private double friction1;
    private double friction2;
    private String name;
    
	/** 
	 * Messages that need to be sent to the server.
	 * 
	 * This currently holds {@link BallMessage} instances describing the balls
	 * that will be teleported to other boards.
     */
	private final ArrayList<Message> outgoing;
    
    /**
     * Abstraction Function:
     * gadgets holds a list of all gadgets on the board
     * balls holds a list of all the balls on the board
     * gravity, friction1 and friction2 holds the respective properties of board.
     * 
     * Rep Invariant:
     * gadget's and ball's positions should be within the bounds of the board
     * gadgets and ball shouldn't be null.
     */
    
    private void checkRep() {
        assert gadgets != null && balls != null && outgoing != null;
        synchronized (this.gadgets) {
            for (Gadget gadget : gadgets) {
                assert gadget.getPosition().x() >= 0 && gadget.getPosition().x() <= 19 &&
                        gadget.getPosition().y() >= 0 && gadget.getPosition().y() <= 19;
            }
        }
    }
    
    /**
     * Creates a new board with the given parameters
     * @param gravity the gravity in this board
     * @param friction1 the mu1 coefficient
     * @param friction2 the mu2 coefficient
     */
    public Board(String name, double gravity, double friction1, double friction2) {
        this.name = name;
        this.gravity = gravity;
        this.friction1 = friction1;
        this.friction2 = friction2;
        this.gadgets = new ArrayList<Gadget>();
        this.balls = new ArrayList<Ball>();
		this.outgoing = new ArrayList<Message>();        
        checkRep();
    }
    
    /**
     * Gets the name of this board
     * @return the name of the board
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Adds the gadget to the board to keep track of it. No copy is created.
     * @param gadget the gadget to add to the board
     */
    public synchronized void addGadget(Gadget gadget) {
        gadgets.add(gadget);
        checkRep();
    }
    
    /**
     * Gets the gadget on this board with the name as name. Throws IllegalArgumentException if not found
     * @param name the name of the gadget to get
     * @return the Gadget on the board with name "name"
     */
    public synchronized Gadget getGadgetFromName(String name) {
        for (Gadget gadget : this.gadgets) {
            if (gadget.getName().equals(name))
                return gadget;
        }
        throw new IllegalArgumentException("gadget not found: " + name);
    }
    
    /**
     * Adds ball to the board. No copy is created.
     * @param gadget the ball to add to the board
     */
    public synchronized void addBall(Ball ball) {
        this.balls.add(ball);
        checkRep();
    }
    
    /**
     * Remove ball from the board if it exists
     * @param ball the ball to remove from the board
     */
    public synchronized void removeBall(Ball ball) {
        this.balls.remove(ball);
        checkRep();
    }
    
    /**
     * Gets a board string index (row major) from position.
     * @param position the position to convert to index
     * @return the index in board string
     */
    public static int getBoardStringIndexFromVect(Vect position) {
        return ((int)position.y() + 1) * (Board.size + 1) + (int)position.x() + 1;
    }
    
    /**
     * For every ball, simulate a physics time increment for every ball with no collisions.
     * @param time the time by which to increment, time should be very small
     */
    public synchronized void incrementNoCollisionTime(double time) {
        for (Ball ball : balls) {
            Vect oldV = ball.getVelocity();            
            Vect newV = oldV.times(1-this.friction1*time-this.friction2*oldV.length()*time).plus(new Vect(0, this.gravity*time));
            if (newV.length() < 0.05) // If velocity is too small, kill it
                newV = new Vect(0, 0);
            ball.setVelocity(newV);
            ball.setPosition(ball.getPosition().plus(oldV.times(time)));
        }
        checkRep();
    }
    
    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < 22; y++) {
            for(int x = 0; x < 22;x++)
                sb.append(' ');
            sb.append('\n');
        }
        String boardString = sb.toString();
        for (Ball ball : this.balls) {
            boardString = ball.render(boardString);
        }
        for (Gadget gadget : this.gadgets) {
            boardString = gadget.render(boardString);
        }
        return boardString;
    }
    
    /**
     * Returns shortest time until the next collision event will happen
     * @return the shortest time until next collision
     */
    public synchronized double timeUntilCollision() { 
        double shortestTime = Double.MAX_VALUE;
        // gadget-ball collisions
        for (Gadget gadget: gadgets){
            for (Ball ball: balls){
                double time = gadget.timeUntilCollision(ball);
                if (time == 0) continue;
                if (time < shortestTime)
                    shortestTime = time;
            }
        }
        // ball-ball collisions
        for (Ball ball1 : balls) {
            for (Ball ball2 : balls){
                if (ball1 == ball2) continue;
                double time = Geometry.timeUntilBallBallCollision(ball1.getCircle(), ball1.getVelocity(), ball2.getCircle(), ball2.getVelocity());
                if (time < shortestTime)
                    shortestTime = time;
            }
        }
        return shortestTime;
    }
    
    /**
    * Simulates a collision with next ball and mutates ball with the new parameters
    */
    public synchronized void reflect() {
        double shortestTime = Double.MAX_VALUE;
        int shortestIndexGadget = -1;
        int shortestIndexBall = -1;
        int shortestIndexBall2 = -1;
        // gadget-ball collisions
        for (int i = 0; i < gadgets.size(); i++)
            for (int j = 0; j < balls.size(); j++){
                double time = gadgets.get(i).timeUntilCollision(balls.get(j));
                if (time < shortestTime) {
                    shortestTime = time;
                    shortestIndexGadget = i;
                    shortestIndexBall = j;
            } 
        }
        // ball-ball collisions
        for (int i = 0; i < balls.size(); i++) {
            Ball ball1 = balls.get(i);
            for (int j = 0; j < balls.size(); j++) {
                Ball ball2 = balls.get(j);
                if (ball1 == ball2) 
                    continue;
                double time = Geometry.timeUntilBallBallCollision(ball1.getCircle(), ball1.getVelocity(), ball2.getCircle(), ball2.getVelocity());
                if (time < shortestTime) {
                    shortestIndexGadget = -1;
                    shortestIndexBall = i;
                    shortestIndexBall2 = j;
                }
            }
        }
        
        // perform collision
        if (shortestIndexGadget == -1)
            if (shortestIndexBall == -1)
                return;
            else {
                Ball ball1 = balls.get(shortestIndexBall);
                Ball ball2 = balls.get(shortestIndexBall2);
                VectPair vels = Geometry.reflectBalls(ball1.getPosition(), 1, ball1.getVelocity(), ball2.getPosition(), 1, ball2.getVelocity());
                ball1.setVelocity(vels.v1);
                ball2.setVelocity(vels.v2);
            }
        else
            gadgets.get(shortestIndexGadget).reflect(balls.get(shortestIndexBall));
    }
    
    
    /**
     * Simulates running of the given time
     * @param time the time in seconds for which to run
     */
    public synchronized void simulateTime(double timeLeft) {
        boolean isColliding = true;
        double originalTime = timeLeft;

        while (isColliding) {            
            
            double time = this.timeUntilCollision();
            if (time < originalTime*0.05)// If time is too small then make it bigger
                time = originalTime*0.05; 
            
            if (time < timeLeft){
                isColliding = true;
            } else {
                isColliding = false;
            }
               
            if (isColliding) {
                this.reflect();
                timeLeft = timeLeft - time;                    
            } else {                   
                this.incrementNoCollisionTime(timeLeft);
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Board)) return false;
        return ((Board)obj).name.equals(this.name);
    }
    
	/**
	 * Handles a message received from the server.
	 * 
	 * @param message the message received from the server.
	 */
	public synchronized void onMessage(Message message) {
		if (message instanceof ConnectWallMessage) {
			ConnectWallMessage wallMessage = (ConnectWallMessage)message;
			OuterWallsGadget wall = findWall(wallMessage.getEdge());
			wall.setNeighborName(wallMessage.getNeighborName());
		} else if (message instanceof DisconnectWallMessage) {
			DisconnectWallMessage wallMessage = (DisconnectWallMessage)message;
			OuterWallsGadget wall = findWall(wallMessage.getEdge());
			wall.setNeighborName(null);			
		} else if (message instanceof BallMessage) {
			BallMessage ballMessage = (BallMessage)message;
			Circle shape = ballMessage.getShape();
			Vect center = shape.getCenter();
			Vect velocity = ballMessage.getVelocity();
			Ball ball = new Ball("teleported-ball", center, velocity);			
			addBall(ball);
		}
	}
	
	/**
	 * Returns the wall next to a board edge.
	 * 
	 * @param edge the board edge
	 * @return the wall next to the given board edge
	 */
	private synchronized OuterWallsGadget findWall(Edge edge) {
		for (Gadget gadget : gadgets) {
			if (!(gadget instanceof OuterWallsGadget))
				continue;
			OuterWallsGadget wall = (OuterWallsGadget)gadget;
			if (wall.getEdge() == edge)
				return wall;
		}
		assert false;  // The board should have walls on all four sides.
		return null;
	}
	
	/**
	 * This will find the balls that are outside of the bounds.
	 * 
	 * The balls that are out of bounds are also removed from the board.
	 * 
	 * @return a list of messages that should be sent to the server documenting
	 *   out of bounds balls
	 */
	public synchronized List<Message> getOutOfBoundBallMessages(){
	    List<Message> messages = new ArrayList<Message>();
	    List<Ball> removedBalls = new ArrayList<Ball>();
	    for (Ball ball: balls) {
	        Vect position = ball.getPosition();
        	double x = position.x();
        	double y = position.y();
	        
	        Vect velocity = ball.getVelocity();
	        double vx = velocity.x();
	        double vy = velocity.y();
	        
	        // HACK: Guess the edge that the ball crossed. This should have
	        //       really been handled by the walls.
	        Edge edge = null;
	        if (x < 0 && vx < 0)
	        	edge = Edge.LEFT;
	        else if (x > 20 && vx > 0)
	        	edge = Edge.RIGHT;
	        else if (y < 0 && vy < 0)
	        	edge = Edge.TOP;
	        else if(y > 20 && vy > 0)
	        	edge = Edge.BOTTOM;
	        
	        if (edge != null) {
	        	// HACK: get the ball back on the board for teleporting.
	        	double r = ball.getCircle().getRadius();
	        	if (x < 0)
	        		x = 0;
	        	if (y < 0)
	        		y = 0;
	        	if (x > 20)
	        		x = 20;
	        	if (y > 20)
	        		y = 20;
	        	BallMessage message = new BallMessage(edge,
	        			new Circle(x,  y, r), velocity);
	            messages.add(message);
                
	        	removedBalls.add(ball);
	        }	            
	    }
	    for (Ball ball : removedBalls) {
	    	balls.remove(ball);
	    }
	    return messages;
	}
}
