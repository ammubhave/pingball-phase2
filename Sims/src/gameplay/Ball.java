package gameplay;

import java.util.HashMap;

import physics.Geometry;
import physics.Geometry.VectPair;
import physics.Vect;
import utilities.*;
import physics.*;

public class Ball {
    private Coords center;
    private Vect velocity;
    private double radius = 0.5d / 2; // 0.5 is the diameter.
    private Circle circleObject;
    private double reflectionCoefficient = 1.0d;
    
    /**
     * Creates a new ball instance.
     * @param center coordinates of the center of the ball.
     * @param velocity velocity of the ball- what Physics defines it as; has magnitude and direction.
     *                 negative defines opposite direction. 
     */
    public Ball(Coords center, Vect velocity) {
        this.center = center;
        this.velocity  = velocity;
        this.circleObject = new Circle(this.center.getX(), this.center.getY(), this.radius);
    }
    
    /**
     * Returns the geometry.Circle object associated with the current Ball.
     * @return a geometry.Circle object associated with the current Ball.
     */
    public synchronized Circle getCircleObject() {
        return this.circleObject;
    }

    /**
     * Gets the X coordinate of the ball.
     * @return x coordinate of the ball.
     */
    public synchronized double getX() {
        return center.getX();
    }

    /**
     * Gets the Y coordinate of the ball.
     * @return y coordinate of the ball.
     */
    public synchronized double getY() {
        return center.getY();
    }
    
    /**
     * Getter for the center property.
     * @return center of the current ball.
     */
    public synchronized Coords getCenter() {
        return this.center;
    }
    
    /**
     * Stops the ball- sets the velocity to 0.
     */
    public synchronized void stop() {
        this.setVelocity(new Vect(0, 0));
    }
    

    /**
     * Moves a stationary ball.
     * Also checks to see if the ball is supposed to bounce / move to another board.
     *
     * @return a new instance of Ball that's been moved to it's currentLocation + it's speed * delta T.
     */
    public synchronized Ball move() {
        
        double[] newCenter = new double[] {center.getX(),center.getY()};
        
        ////System.out.println("Horizontal Velocity: " + velocity.x());
        ////System.out.println("Vertical Velocity:   " + velocity.y());
        
        newCenter[0] += velocity.x() / 20.0;
        newCenter[1] += velocity.y() / 20.0;
        
        Coords newCenterCoords = new Coords(newCenter[0], newCenter[1]);
        Vect sameVelocity = this.velocity;
        
        return new Ball(newCenterCoords, sameVelocity);
    }
    
    /**
     * Sets the ball's velocity.
     * @param velocity velocity of the ball- what Physics defines it as; has magnitude and direction.
     *                 negative defines opposite direction.
     *                 Passing (0, 0) as the velocity stops the ball but that should NOT be used for stopping the ball.
     *                 Use stop() instead.
     */
    public synchronized void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }
    
    /**
     * Sets the ball's center.
     * 
     * @param center the coordinates of the center of the ball that we're about to create.
     */
    public synchronized void setCenter(Coords center) {
        this.center = center;
    }
    
    /**
     * Gets the current velocity of the ball.
     * @return current velocity of the ball.
     */
    public synchronized Vect getVelocity() {
        return this.velocity;
    }
    
    /**
     * Returns the ball's radius.
     * @return the ball's radius.
     */
    public synchronized double getRadius() {
        return this.radius;
    }
    
    /**
     * Finds out if the ball passed in as an argument is touching (or is inside) the current ball or not.
     * @param b the next ball object which we suppose might be touching the current ball.
     * @return whether the given ball object is touching the current ball.
     */
    public synchronized boolean inContact(Ball b) {
        double distance = this.center.getDistance(b.getCenter());
        if (distance <= (this.radius + b.getRadius()))
            return true;
        return false;
    }
    
    @Override
    public String toString() {
        return "*";
    }
    
    @Override
    public synchronized boolean equals(Object o) {
        if ( ! ( o instanceof Ball))
            return false;
        
        Ball b = (Ball) o;
        if (b.getCenter().equals(this.getCenter()) && b.getVelocity().equals(this.getVelocity()))
                return true;
        return false;
    }
    
    /**
     * Bounces the current ball with another ball b.
     * @param b
     *          the ball that just hit with the current ball.
     * @return  a new instance of Ball that represents where the ball b is supposed to be at
     *          right after the collision.
     */
    public synchronized Ball bounce(Ball b) {
        VectPair returnVel = Geometry.reflectBalls(this.circleObject.getCenter(), 1.0d, this.velocity, b.getCircleObject().getCenter(), 1.0d, b.getVelocity());
        
        this.setVelocity(returnVel.v1);
        b.setVelocity(returnVel.v2);
        
        // Move the current Ball.
        double currentX = this.getX() + 10*this.getVelocity().x() / 20.0;
        double currentY = this.getY() + 10*this.getVelocity().y() / 20.0;
        this.setCenter(new Coords(currentX, currentY));
        
        // Move the bounced Ball.
        double bouncedX = b.getX() + 10*b.getVelocity().x() / 20.0;
        double bouncedY = b.getY() + 10*b.getVelocity().y() / 20.0;
        b.setCenter(new Coords(bouncedX, bouncedY));
        
        return b; 
    }
    
    /**
     * Returns the expected collision time between the passed in ball b and the current ball.
     * 
     * @param b
     *          the ball that we think might collide with the current ball.
     * @param v
     *          the velocity of ball b.
     * @return
     *          a hashmap of <String, Double>. The key is "BALL" and the value is the expected time before collision.
     */
    public synchronized HashMap<String, Double> leastCollisionTime(Ball b, Vect v) {
        HashMap<String, Double> allTimes = new HashMap<String, Double>();        
        double minimum = Double.POSITIVE_INFINITY;
        String identifier = "BALL";
        
        minimum = Geometry.timeUntilBallBallCollision(b.getCircleObject(), b.getVelocity(), this.circleObject, this.getVelocity());
        allTimes.put(identifier, minimum);
        return allTimes;
    }
    
}
