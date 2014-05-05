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
     * Thread Safety Information: The ball class will be threadsafe because it
     * will be immutable. Therefore, since there is no rep exposure and all
     * fields are final and private, the class is threadsafe.
     */

    private final static double RADIUS = 0.25;
    private double mu1 = 0.025;
    private double mu2 = 0.025;
    private double gravity = 25;
    private final Circle circle;

    private Vect position;
    private Vect velocity;

    private String name;

    public Ball(double xCoord, double yCoord, double xVel, double yVel, String ballName) {
        this.position = new Vect(xCoord, yCoord);
        this.circle = new Circle(position, RADIUS);
        this.velocity = new Vect(xVel, yVel);
        name = ballName;
    }

    /**
     * The environment of the ball is changed from default friction and gravity
     * settings
     * 
     * @param: first friction coefficient
     * @param: second friction coefficient
     * @param: gravity constant
     */
    public void changeEnvironment(double m1, double m2, double g) {
        mu1 = m1;
        mu2 = m2;
        gravity = g;
    }

    /** The ball is moved as long as the time is going */
    public void move(double time) {
        Vect oldVel = this.getVelocity();
        // fricvel = oldvel(1 - MU*time - MU2*time*abs(oldvel))
        Vect fricVel = oldVel.times(1 - (mu1 * time) - (mu2 * time * oldVel.length()));

        // gravVel = fricVel + accel * time, where accel is 0 in x and -25 in y
        Vect grav = new Vect(0, -gravity);
        Vect gravVel = fricVel.plus(grav.times(time));
        // i don't want to find the position vector
        // deltax = vgrav * deltat
        Vect deltaX = gravVel.times(time);
        deltaX = new Vect(deltaX.x(), -deltaX.y());

        this.changeVelocity(gravVel);
        this.changePos(this.getPos().plus(deltaX));
        // yay i think it's done!
    }

    /**
     * Returns position of the ball
     * 
     * @return Vect form of position
     */
    public Vect getPos() {
        double newX = position.x();
        double newY = position.y();
        return new Vect(newX, newY);
    }

    /**
     * Returns velocity of the ball
     * 
     * @return Vect form of velocity
     */
    public Vect getVelocity() {
        double newX = velocity.x();
        double newY = velocity.y();
        return new Vect(newX, newY);
    }

    /**
     * Returns velocity of the ball
     * 
     * @return Vect form of velocity
     */
    public Vect getFlippedVelocity() {
        double newX = velocity.x();
        double newY = velocity.y();
        return new Vect(newX, -newY);
    }

    /**
     * @return Circle of the ball
     */
    public Circle getCircle() {
        Vect pos = this.getPos();
        return new Circle(pos, RADIUS);
    }

    /**
     * Changes position of the ball.
     * 
     * @param newPos
     *            new position of the ball
     */
    public void changePos(Vect newPos) {
        double newX = newPos.x();
        double newY = newPos.y();
        this.position = new Vect(newX, newY);
    }

    /**
     * Changes velocity of the ball.
     * 
     * @param newVel
     *            new velocity of the ball
     */
    public void changeVelocity(Vect newVel) {
        double newX = newVel.x();
        double newY = newVel.y();
        this.velocity = new Vect(newX, newY);
    }

}
