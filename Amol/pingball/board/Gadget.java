package pingball.board;

import physics.Vect;

/**
 * Represents a gadget in Pingball
 */
public interface Gadget {
    /**
     * Gets the position of the top left bounding box of this gadget
     * @return the position of the top left bounding box
     */
    public Vect getPosition();
    
    /**
     * Gets the name of the gadgets
     * @return the name of the gadget
     */
    public String getName();
    
    /**
     * The time left until the ball collides with the gadget
     * @param ball the ball to check for collision
     * @return the time until collision
     */
    public double timeUntilCollision(Ball ball);
    
    /**
     * Simulates a collision with ball and mutates ball with the new parameters
     * @param ball the ball on which to perform collision
     */
    public void reflect(Ball ball);
    
    /**
     * Calls all the triggers hooked to this action and does the action of the gadget.
     */
    public void doAction();
    
    /**
     * Hooks the action of the given gadget to the trigger of this gadget.
     */
    public void hookActionToTrigger(Gadget gadget);
    
    /**
     * Trigger this gadget. Calls doAction on all the gadgets who were attached to this trigger.
     */
    public void trigger();
    
    /**
     * Renders this gadget onto input and returns the final result
     * @param input the input string to render upon
     * @return the final rendered text
     */
    public String render(String input);
}
