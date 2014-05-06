package pingball.board;

import physics.Circle;
import physics.Vect;

/**
 * Represents a ball in pingball
 * 
 * Thread Safety:
 * Ball can only be modified by any one of the gadgets. Any accesses to the properties of the 
 * ball should be made synchronized.
 */
public class Ball {
    private Vect position;
    private Vect velocity;
    private String name;
    
    /**
     * Abstraction function:
     * position, velocity holds the position and velocity of the ball respectively
     * name holds the name of the ball.
     * 
     * Rep Invariant:
     * position should be non-negative
     * velocity should be between 0.01 L/sec to 200.0 L/sec
     */
    
    private void checkRep(){
        assert position.x() >= 0 && position.y() >= 0;
        assert velocity.length() >= 0.01 && velocity.length() <= 200.0;
    }
    
    /**
     * Creates a new Ball with given parameters
     * @param position the position of the ball
     * @param velocity the velocity of the ball
     */
    public Ball(String name, Vect position, Vect velocity) {
        this.name = name;
        this.position = position;
        this.velocity = velocity;
        checkRep();
    }
    
    /**
     * Gets the position of the ball on the board
     * @returm the position of the ball
     */
    public Vect getPosition() {
        return this.position;
    }
    
    /**
     * Gets the velocity of the ball
     * @return velocity the velocity of the ball
     */
    public Vect getVelocity() {
        return this.velocity;
    }
    
    /**
     * Sets the position of the ball to position
     * @param position the new position of the ball
     */
    public void setPosition(Vect position) {
        this.position = position;
    }
    
    /**
     * Sets the velocity of the ball to velocity
     * @param velocity the new velocity of the ball
     */
    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }
    
    /**
     * Renders the ball onto input and returns the final board
     * @param input the board string on which to render
     * @return the final rendered board string
     */
    public String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(Board.getBoardStringIndexFromVect(this.position), '*');
        return sb.toString();
    }
    
    /**
     * Gets this ball as a circle with radius 0.25 L
     * @return
     */
    public Circle getCircle(){
        return new Circle(position, 0.25);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Ball)) return false;
        return ((Ball)obj).name.equals(this.name);
    }
}
