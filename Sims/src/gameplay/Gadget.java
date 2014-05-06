package gameplay;

import java.util.HashMap;

import physics.Vect;
import utilities.*;

public interface Gadget {

    /**
     * Returns a new instance of Ball with changed velocity. Changed because the
     * ball being an immutable object is a good idea. Helps for concurrency.
     * 
     * @param b
     *            the ball object that will be bouncing
     * @param identifier
     *            to represent what part of the gadget the ball should bounce
     *            off of
     * @return ball object with a velocity resulting from bounce
     */
    public Ball bounce(Ball b, String identfier);

    /**
     * If the ball bounces off the gadget, it triggers the gadget g
     * 
     * @param gadget that is triggered
     */
    public void addTrigger(Gadget g);
    
    /**
     * Depending on the gadget, performs the action it's supposed to on the
     * gadget itself. For example, rotating the flipper. Action on the ball
     * should be handled by trigger / bounce itself. This is only for modifying
     * the gadget.
     */
    public void action();

    /**
     * Get the reflection coefficient of the current gadget.
     * 
     * @return reflection coefficient of current gadget.
     */
    public float getReflectionCoefficient();

    /**
     * Get the position of the current gadget
     * 
     * @return coordinate position of the current gadget
     */
    public Coords getPosition();

    /**
     * Part of the constructor that creates specific parts (lineSegments and Circle objects)
     * that make up the gadget.
     */
    public void createGeoObjects();

    /**
     * Get the name of the gadget
     * 
     * @return string that gives the name of the gadget
     */
    public String getType();

    /**
     * Goes through all the lineSegments and Circle objects created by the createGeoObjects() method
     * while instantiating a Gadget and finds which gadget and which part of it will the ball
     * collide with next.
     * 
     * @param b the ball whose collision we're testing.
     * @param v the velocity vector of the ball b.
     * @return a hashmap of <String, Double>. The key is an identifier representing which side of the current
     * gadget the ball is going to collide. For example, for a square bumper, it's one of
     * "top", "left", "right" or "bottom". The value is the time before collision.
     */
    public HashMap<String, Double> leastCollisionTime(Ball b, Vect v);
}
