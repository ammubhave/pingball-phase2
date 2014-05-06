package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;

/**
 * Represents the circular bumper gadgets for pingball.
 */
public class CircularBumperGadget implements Gadget {
    private String name;
    private Vect position;
    private double coefficientOfReflection;
    private List<Gadget> hookedGadgets;
    
    /**
     * Abstraction Fuction:
     * position and coefficientOfReflection represent the respective properties of this gadget
     * hookedGadgets are all the gadgets which are hooked to this gadget.
     * 
     * Rep Invariant:
     * position and hookedGadgets are not null.
     */
    
    private void checkRep(){
        assert position != null && hookedGadgets != null;
    }
    
    /**
     * Creates a new circular bumper with the given position and coefficient of reflection
     * @param position the position of the top left corner of the bounding box
     * @param coefficientOfReflection the coefficient of reflection
     */
    public CircularBumperGadget(String name, Vect position, double coefficientOfReflection) {
        this.name = name;
        this.position = position;
        this.coefficientOfReflection = coefficientOfReflection;
        this.hookedGadgets = new ArrayList<Gadget>();
        checkRep();
    }
    
    /**
     * Creates a new circular bumper with the given and coefficientO  of reflection = 1.0
     * @param position the position of the top left corner of the bounding box
     */
    public CircularBumperGadget(String name, Vect position) {
        this(name, position, 1.0);
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Vect getPosition() {
        return this.position;
    }

    @Override
    public double timeUntilCollision(Ball ball) {
        return Geometry.timeUntilCircleCollision(new Circle(this.position.plus(new Vect(0.5, 0.5)), 0.5), ball.getCircle(), ball.getVelocity());
    }

    @Override
    public void reflect(Ball ball) {
        ball.setVelocity(Geometry.reflectCircle(this.position, ball.getPosition(), ball.getVelocity(), this.coefficientOfReflection));
        this.trigger();
    }

    @Override
    public void doAction() {
        // No Action
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        this.hookedGadgets.add(gadget);
    }

    @Override
    public void trigger() {
        for (Gadget gadget : this.hookedGadgets) {
            gadget.doAction();
        }
    }

    @Override
    public String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(Board.getBoardStringIndexFromVect(this.position), 'O');
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CircularBumperGadget)) return false;
        return ((CircularBumperGadget)obj).name.equals(this.name);
    }
}
