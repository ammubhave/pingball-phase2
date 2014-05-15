package pingball.board;

import java.util.List;

import pingball.proto.Message;

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


    /**
     * Should call action method of all the hooked gadgets
     */
    public void trigger();

    /**
     * Determines the smallest time until the ball is going to collide with it.
     * 
     * @param ball the Ball object with which it may collide
     * @return the time until collision will happen
     */
    public double leastCollisionTime(Ball ball);


    /**
     * Performs the changes which happen to a ball when it collides with this
     * gadget. This may involve changing the velocity/angle/position of the ball. 
     * 
     * @param ball the Ball object which is interacting with the gadget
     * @return Any messages generated from this collision
     */
    public Message reactBall(Ball ball);

    /**
     * Performs action of the gadget on itself.
     * For e.g., flips the flipper, releases the ball from absorber, etc.
     */
    public void action();

    /**
     * @return x coordinate of the upper-leftmost point of the gadget bounding box
     */
    public double getX();

    /**
     * @return y coordinate of the upper-leftmost point of the gadget bounding box
     */
    public double getY();

    /**
     * @return name of the gadget
     */
    public String getName();

    /**
     * Hooks the action of another gadget to the trigger of this gadget
     * 
     * @param gadget the gadget's whose action needs to be hooked
     */
    public void hookActionToTrigger(Gadget gadget);
    
    /**
     * Renders this gadget onto input and returns the final result
     * @param input the input string to render upon
     * @return the final rendered text
     */
    public String render(String input);

}
