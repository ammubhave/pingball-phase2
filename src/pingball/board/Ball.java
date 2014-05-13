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
    /**
     * Thread Safety Information: The ball class will be threadsafe because
     * all access to position or velocity first synchronize on them separately.
     * Vect is immutable so makes those parts of code thread safe (prevents rep exposure)
     */

    private final static double RADIUS = 0.25;

    private Vect position;    
    private Vect velocity;

    private final String name;

    /**
     * Creates a new Ball object
     * @param ballName the name of the ball
     * @param pos the position of the ball
     * @param vel the velocity of the ball
     */
    public Ball(String ballName, Vect pos, Vect vel) {
        this.position = pos;
        this.velocity = vel;
        name = ballName;
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
    }

    /**
     * Changes velocity of the ball.
     * 
     * @param newVel new velocity of the ball
     */
    public synchronized void changeVelocity(Vect newVel) {
        velocity = newVel;
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

}
