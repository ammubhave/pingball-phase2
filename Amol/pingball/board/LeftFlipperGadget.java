package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Represents a flipper gadget in pingball.
 */
public class LeftFlipperGadget implements Gadget {
    private String name;
    private Vect position;
    private FlipperOrientation orientation;
    private VertexOrientation vertexOrientation;
    private double coefficientOfReflection;
    private List<Gadget> hookedGadgets;
    
    /**
     * Abstraction Function:
     * position, orientation and coefficientOfReflection represent the respective properties of this gadget.
     * hookedGadgets are all the gadgets which are hooked to this gadget.
     * 
     * Rep Invariant:
     * position, orientation and hookedGadgets should not be null.
     */
    
    private void checkRep(){
        assert this.position != null && this.orientation != null && this.hookedGadgets != null;
    }
    
    public enum FlipperOrientation {
        HORIZONTAL, VERTICAL
    }
    
    public enum VertexOrientation {
        NW, NE, SW, SE
    }
    
    /**
     * Creates a new flipper with the given position, orientation and coefficient of reflection
     * @param position the position of the top left corner of the bounding box
     * @param orientation the orientation of the flipper
     * @param coefficientOfReflection the coefficient of reflection
     */
    public LeftFlipperGadget(String name, Vect position, VertexOrientation vertexOrientation, double coefficientOfReflection) {
        this.name = name;
        this.position = position;
        this.vertexOrientation = vertexOrientation;
        switch (this.vertexOrientation) {
        case NW:
        case SE:
            this.orientation = FlipperOrientation.VERTICAL;
            break;
        case NE:
        case SW:
            this.orientation = FlipperOrientation.HORIZONTAL;
            break;
        }
        this.coefficientOfReflection = coefficientOfReflection;                
        this.hookedGadgets = new ArrayList<Gadget>();
        checkRep();
    }
    
    /**
     * Creates a new flipper with the given position and orientation and coefficient of reflection as 0.95
     * @param position the position of the top left corner of the bounding box
     * @param orientation the orientation of the flipper
     */
    public LeftFlipperGadget(String name, Vect position, VertexOrientation vertexOrientation) {
        this(name, position, vertexOrientation, 0.95);
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Vect getPosition() {
        return this.position;
    }
    
    private LineSegment getSegment() {
        LineSegment segment;
        
        switch(this.vertexOrientation) {
        case NW:
            if (this.orientation == FlipperOrientation.HORIZONTAL)
                segment = new LineSegment(this.position, this.position.plus(new Vect(2, 0)));
            else
                segment = new LineSegment(this.position.plus(new Vect(0, 2)), this.position.plus(new Vect(0, 0)));
        case NE:
            if (this.orientation == FlipperOrientation.HORIZONTAL)
                segment = new LineSegment(this.position, this.position.plus(new Vect(2, 0)));
            else
                segment = new LineSegment(this.position.plus(new Vect(2, 2)), this.position.plus(new Vect(2, 0)));
            break;
        case SE:
            if (this.orientation == FlipperOrientation.HORIZONTAL)
                segment = new LineSegment(this.position.plus(new Vect(2, 2)), this.position.plus(new Vect(0, 2)));
            else
                segment = new LineSegment(this.position.plus(new Vect(2, 2)), this.position.plus(new Vect(2, 0)));
            break;
        case SW:
            if (this.orientation == FlipperOrientation.HORIZONTAL)
                segment = new LineSegment(this.position.plus(new Vect(2, 2)), this.position.plus(new Vect(0, 2)));
            else
                segment = new LineSegment(this.position.plus(new Vect(0, 2)), this.position.plus(new Vect(0, 0)));
            break;
        default:
            throw new IllegalStateException("this gadget is in an illegal state");
        }
        return segment;
    }

    @Override
    public double timeUntilCollision(Ball ball) {
        return Geometry.timeUntilWallCollision(this.getSegment(), ball.getCircle(), ball.getVelocity());
    }
    
    private static final double FLIPPER_SPEED = 18.8495559; // radians per second = 1080 degrees per second
    @Override
    public void reflect(Ball ball) {
        FlipperOrientation orientation = this.orientation;
        this.trigger(); // call triggers
        if (orientation != this.orientation) { // if the trigger actually rotated this
            double speed = -FLIPPER_SPEED;
            if (this.vertexOrientation == VertexOrientation.NW && this.orientation == FlipperOrientation.VERTICAL ||
                this.vertexOrientation == VertexOrientation.NE && this.orientation == FlipperOrientation.HORIZONTAL ||
                this.vertexOrientation == VertexOrientation.SW && this.orientation == FlipperOrientation.VERTICAL ||
                this.vertexOrientation == VertexOrientation.SE && this.orientation == FlipperOrientation.HORIZONTAL)
                speed *= -1;;
            ball.setVelocity(Geometry.reflectRotatingWall(getSegment(), ball.getPosition(), speed, ball.getCircle(), ball.getVelocity(), this.coefficientOfReflection));
        } else {
            ball.setVelocity(Geometry.reflectWall(getSegment(), ball.getVelocity(), this.coefficientOfReflection));
        }
    }

    @Override
    public void doAction() {
        if (this.orientation == FlipperOrientation.HORIZONTAL)
            this.orientation = FlipperOrientation.VERTICAL;
        else if (this.orientation == FlipperOrientation.VERTICAL)
            this.orientation = FlipperOrientation.HORIZONTAL;
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
        if (this.vertexOrientation == VertexOrientation.NW && this.orientation == FlipperOrientation.HORIZONTAL ||
            this.vertexOrientation == VertexOrientation.NE && this.orientation == FlipperOrientation.HORIZONTAL) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(this.position), '-');
            sb.setCharAt(Board.getBoardStringIndexFromVect(this.position)+1, '-');
        } else
        if ((this.vertexOrientation == VertexOrientation.NE && this.orientation == FlipperOrientation.VERTICAL ||
                this.vertexOrientation == VertexOrientation.SE && this.orientation == FlipperOrientation.VERTICAL)) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(this.position.plus(new Vect(2,0))), '|');
            sb.setCharAt(Board.getBoardStringIndexFromVect(this.position.plus(new Vect(2,0)))+Board.size+1, '|');
        } else 
        if (this.vertexOrientation == VertexOrientation.SE && this.orientation == FlipperOrientation.HORIZONTAL ||
            this.vertexOrientation == VertexOrientation.SW && this.orientation == FlipperOrientation.HORIZONTAL) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(this.position.plus(new Vect(0,2))), '-');
            sb.setCharAt(Board.getBoardStringIndexFromVect(this.position.plus(new Vect(0,2)))+1, '-');
        } else 
        if (this.vertexOrientation == VertexOrientation.SW && this.orientation == FlipperOrientation.VERTICAL ||
            this.vertexOrientation == VertexOrientation.NW && this.orientation == FlipperOrientation.VERTICAL) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(this.position), '|');
            sb.setCharAt(Board.getBoardStringIndexFromVect(this.position)+Board.size+1, '|');
        } 
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof LeftFlipperGadget)) return false;
        return ((LeftFlipperGadget)obj).name.equals(this.name);
    }
}
