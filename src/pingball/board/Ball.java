package pingball.board;

import physics.Circle;
import physics.Vect;

/*
 * Ball class.
 * variables: Square currentSquare, tuple(x,y) ballLocation
 * you are free to change these as you wish
 * remember to write thread safety arguments
 */

public class Ball {
    /*
     * Thread Safety Information: The ball class will be threadsafe because
     * all access to position or velocity first synchronize.
     * Vect is immutable so makes those parts of code thread safe (prevents rep exposure)
     */

    private final static double RADIUS = 0.25;

    private Vect position;    
    private Vect velocity;

    private final String name;

    /**
     * Creates a new Ball object
     * @param name the name of the ball
     * @param position the position of the ball
     * @param velocity the velocity of the ball
     */
    public Ball(String name, Vect position, Vect velocity) {
        this.position = position;
        this.velocity = velocity;
        this.name = name;
        
        checkRep();
    }
    
    /*
     *  Rep Invariant:
     *  - no attributes should be null
     */
    private void checkRep() {
        assert position != null;
        assert velocity != null;
        assert name != null;
    }

    /**
     * Returns position of the ball
     * 
     * @return Vect form of position
     */
    public synchronized Vect getPos() {
        return position;
    }

    /**
     * Returns velocity of the ball
     * 
     * @return Vect form of velocity
     */
    public synchronized Vect getVelocity() {
        return velocity;
    }

    /**
     * Returns the name of the ball
     * 
     * @return name of the ball
     */
    public String getName() {
        return name;
    }

    /**
     * @return Circle of the ball
     */
    public Circle getCircle() {
        return new Circle(this.getPos(), RADIUS);
    }

    /**
     * Changes position of the ball.
     * 
     * @param newPos new position of the ball
     */
    public synchronized void changePos(Vect newPos) {
        position = newPos;
        
        checkRep();
    }

    /**
     * Changes velocity of the ball.
     * 
     * @param newVel new velocity of the ball
     */
    public synchronized void changeVelocity(Vect newVel) {
        velocity = newVel;
        
        checkRep();
    }
    
    /**
     * Renders the ball onto input and returns the final board
     * @param input the board string on which to render
     * @return the final rendered board string
     */
    public synchronized String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(Board.getBoardStringIndexFromVect(this.getPos()), '*');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ball)) return false;
        return ((Ball)obj).getName().equals(this.getName());
    }
}
