package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Represents a square bumper in pingball.
 */
public class SquareBumperGadget implements Gadget {
    String name;
    private Vect position;
    private double coefficientOfReflection;
    private List<Gadget> hookedGadgets;
    
    /**
     * Abstraction Function:
     * The position and coefficientOfReflection is the respective property of the gadget. 
     * hookedGadgets are all the gadgets which are hooked to this gadget.
     * 
     * Rep Invariant:
     * The position and hookedGadgets should not be null
     */

    private void checkRep(){
        assert position != null && hookedGadgets != null;
    }
    
    
    /**
     * Creates a new square bumper with the given position and coefficient of reflection
     * @param position the position of the top left corner of the gadget
     * @param coefficientOfReflection the coefficient of reflection of the gadget
     */
    public SquareBumperGadget(String name, Vect position, double coefficientOfReflection) {
        this.name = name;
        this.position = position;
        this.coefficientOfReflection = coefficientOfReflection;
        this.hookedGadgets = new ArrayList<Gadget>();
        checkRep();
    }
    
    /**
     * Creates a new square bumper with the given position and coefficient of reflection of 1.0
     * @param position the position of the top left corner of the gadget
     */
    public SquareBumperGadget(String name, Vect position){
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
    

    private LineSegment[] getBorders() {
        return new LineSegment[] {
                new LineSegment(this.position, this.position.plus(new Vect(1, 0))),
                new LineSegment(this.position.plus(new Vect(1,0)), this.position.plus(new Vect(1,1))),
                new LineSegment(this.position.plus(new Vect(1,1)), this.position.plus(new Vect(0,1))),
                new LineSegment(this.position.plus(new Vect(0,1)), this.position),
        };
    }

    @Override
    public double timeUntilCollision(Ball ball) {
        LineSegment[] borders = this.getBorders(); 
        double shortestTime = Double.MAX_VALUE;
        for (LineSegment segment : borders) {
            double time = Geometry.timeUntilWallCollision(segment, ball.getCircle(), ball.getVelocity());
            if (time < shortestTime)
                shortestTime = time;
        }
        
        return shortestTime;
    }

    @Override
    public void reflect(Ball ball) {        
        LineSegment[] borders = this.getBorders(); 
        double shortestTime = Double.MAX_VALUE;
        int shortestIndex = -1;

        for (int i = 0; i < borders.length; i++) {
            LineSegment segment = borders[i];
            double time = Geometry.timeUntilWallCollision(segment, ball.getCircle(), ball.getVelocity());
            if (time < shortestTime) {
                shortestTime = time;
                shortestIndex = i;
            }
        }
        if (shortestIndex == -1)
            return;

        ball.setVelocity(Geometry.reflectWall(borders[shortestIndex], ball.getVelocity(), this.coefficientOfReflection));
        
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
        
        sb.setCharAt(Board.getBoardStringIndexFromVect(position), '#');
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SquareBumperGadget)) return false;
        return ((SquareBumperGadget)obj).name.equals(this.name);
    }
}
