package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Represents a triangular bumper in pingball.
 */
public class TriangularBumperGadget implements Gadget {
    String name;
    private Vect position;
    private double coefficientOfReflection;
    private TriangularBumperOrientation orientation;
    private List<Gadget> hookedGadgets;
    
    /**
     * Abstraction Function:
     * The position, coefficientOfReflection and orientation are the respective properties of the gadget. 
     * hookedGadgets are all the gadgets which are hooked to this gadget.
     * 
     * Rep Invariant:
     * The position, orientation, and hookedGadgets should not be null
     */
    
    private void checkRep(){
        assert position != null && orientation != null && hookedGadgets != null;
    }
    
    /**
     * Represents the position of the right angle in a triangular bumper
     */
    public enum TriangularBumperOrientation {
        NW, NE, SW, SE
    }
    
    /**
     * Creates an triangular bumper gadget of pingball
     * @param position the position of the top left corner of the bounding box of triangular bumper
     * @param orientation where the right angle of the triangle is located
     * @param coefficientOfReflection the coefficient of reflection of the gadget
     */
    public TriangularBumperGadget(String name, Vect position, TriangularBumperOrientation orientation, double coefficientOfReflection) {
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        this.coefficientOfReflection = coefficientOfReflection;
        this.hookedGadgets = new ArrayList<Gadget>();
        checkRep();
    }
    
    /**
     * Creates an triangular bumper gadget of pingball with the coefficient of reflection set to 1.0
     * @param position the position of the top left corner of the triangular bumper
     * @param orientation where the right angle of the triangle is located
     */
    public TriangularBumperGadget(String name, Vect position, TriangularBumperOrientation orientation) {
        this(name, position, orientation, 1.0);
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Vect getPosition() {
        return this.position;
    }

    private LineSegment[] getBorders(){ 
        LineSegment[] borders;
        
        switch(this.orientation){
        case NW:
            borders = new LineSegment[] {
                    new LineSegment(this.position, this.position.plus(new Vect(1, 0))),
                    new LineSegment(this.position.plus(new Vect(1,0)), this.position.plus(new Vect(0,1))),
                    new LineSegment(this.position.plus(new Vect(0,1)), this.position)
            };
            break;
        case SW:
            borders = new LineSegment[] {
                    new LineSegment(this.position, this.position.plus(new Vect(0, 1))),
                    new LineSegment(this.position.plus(new Vect(0,1)), this.position.plus(new Vect(1,1))),
                    new LineSegment(this.position.plus(new Vect(1,1)), this.position)
            };
            break;
        case NE:
            borders = new LineSegment[] {
                    new LineSegment(this.position, this.position.plus(new Vect(1,0))),
                    new LineSegment(this.position.plus(new Vect(1,0)), this.position.plus(new Vect(1,1))),
                    new LineSegment(this.position.plus(new Vect(1,1)), this.position)
            };
            break;
        case SE:
            borders = new LineSegment[] {
                    new LineSegment(this.position.plus(new Vect(1,0)), this.position.plus(new Vect(1,1))),
                    new LineSegment(this.position.plus(new Vect(1,1)), this.position.plus(new Vect(0,1))),
                    new LineSegment(this.position.plus(new Vect(0,1)), this.position.plus(new Vect(1,0)))
            };
            break;
        default:
            throw new RuntimeException("The triangle bumper is in an illegal orientation");
        }
        return borders;
    }
    
    
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
       
        if (this.orientation == TriangularBumperOrientation.NW || this.orientation == TriangularBumperOrientation.SE){
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '/');

        }
        
        if (this.orientation == TriangularBumperOrientation.SW || this.orientation == TriangularBumperOrientation.NE){
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '\\');
        }
        return sb.toString();
        
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TriangularBumperGadget)) return false;
        return ((TriangularBumperGadget)obj).name.equals(this.name);
    }
}
