package pingball.board;

/*
 * Gadget superclass OR interface, have lots of implementing/extending gadget classes.
 * For now, has been implemented as an interface
 */

/**
 * Thread safety: Synchronize most* methods to ensure no more than one Ball is
 * accessing them at one time. In cases where there are multiple balls
 * interacting with the gadget at once, the higher-level program using the
 * gadget will run both actions separately.
 * 
 * *Some objects, such as a Square bumper, can safely interact with multiple
 * objects, so they may not have to synchronize their action methods. In fact,
 * the only one I can see needs a reasonable amount of synchronicity is the
 * flipper.
 * 
 */
public interface Gadget {
    // instance variables should include the following plus any other
    // class-unique variables

    // private final double reflCoeff - the reflection coefficient of said
    // gadget
    // private boolean triggered - lets you know if the gadget is in the process
    // of performing an action.

    // TODO: Implement this. Loop over all the hooked gadgets, and call action
    // on them.

    /**
     * Should call action method of all the hooked gadgets
     */
    public void trigger();

    /**
     * Determines the smallest time until the ball is going to collide with it.
     * 
     * @param ball
     *            the Ball object with which it may collide
     * @return the time until collision will happen
     */
    public double leastCollisionTime(Ball ball);

    // TODO: Look up this code and make changes so that this does not affect the
    // gadget themselved
    /**
     * Performs the action required once a trigger has occurred. This could
     * involve changing the velocity/angle/position of the ball. (NOTE: The
     * freezing may be necessary when multiple balls are interacting with one
     * gadget: it is just there to serve as a reminder.)
     * 
     * @param ball
     *            the Ball object inside this.containingSquare that is
     *            interacting with the gadget
     */
    public void reactBall(Ball ball);

    // TODO: Implement this. Mostly move partial code from react (current
    // action) so that
    // the code that changes the gadget it self is here in action

    /**
     * Performs action of the gadget
     */
    public void action();

    /**
     * Gets the reflection coefficient of the gadget.
     * 
     * @return this.reflCoeff
     */
    public double getReflCoeff();

    /**
     * @return x coordinate of the upper-leftmost point of the gadget
     */
    public double getX();

    /**
     * @return y coordinate of the upper-leftmost point of the gadget
     */
    public double getY();

    /**
     * @return name of the gadget
     */
    public String getName();

    // TODO: Try not to use this method anywhere, similar to instanceOf
    /**
     * @return type of the gadget
     */
    public String type();

    // TODO: Implement this
    /**
     * Hooks the action of another gadget to the trigger of this gadget
     * 
     * @param gadget
     *            the gadget's whose action needs to be hooked
     */
    public void hookActionToTrigger(Gadget gadget);

}
