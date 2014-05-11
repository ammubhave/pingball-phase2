package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.LineSegment;
import physics.Vect;

/** Represents the Flipper gadget class */

public class Flipper implements Gadget {

    public enum FlipperOrientation {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private final double coefficientOfReflection = 0.95;
    private final double boundingBoxLength = 2.0;
    private final static double NULL = 5;

//    private LineSegment boundingBoxTop;
//    private LineSegment boundingBoxBottom;
//    private LineSegment boundingBoxLeft;
//    private LineSegment boundingBoxRight;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();
    private double xCoord;
    private double yCoord;
    private Vect position;

    public Flipper(Vect vect) {
        xCoord = vect.x();
        yCoord = vect.y();
        position = vect;
        
//        boundingBoxTop = new LineSegment(xCoord, yCoord, xCoord + boundingBoxLength, yCoord);
//        boundingBoxBottom = new LineSegment(xCoord, yCoord + boundingBoxLength, xCoord + boundingBoxLength, yCoord
//                + boundingBoxLength);
//        boundingBoxLeft = new LineSegment(xCoord, yCoord, xCoord, yCoord + boundingBoxLength);
//        boundingBoxRight = new LineSegment(xCoord + boundingBoxLength, yCoord, xCoord + boundingBoxLength, yCoord
//                + boundingBoxLength);
    }

    /** Returns the top line segment of the bounding box */
    public LineSegment getTop() {
        throw new RuntimeException();
    }

    /** Returns the bottom line segment of the bounding box */
    public LineSegment getBottom() {
        throw new RuntimeException();
    }

    /** Returns the left line segment of the bounding box */
    public LineSegment getLeft() {
        throw new RuntimeException();
    }

    /** Returns the right line segment of the bounding box */
    public LineSegment getRight() {
        throw new RuntimeException();
    }

    /** Returns the time before collision */
    public double leastCollisionTime(Ball ball) {
        throw new RuntimeException();
    }

    @Override
    public void trigger() {
        throw new RuntimeException();

    }

    /** Acts on a collision */
    public void action(Ball ball) {
        throw new RuntimeException();
    }

    /** Returns the reflection coefficient */
    public double getReflCoeff() {
        throw new RuntimeException();
    }

    /**
     * Returns a string representation of the flipper.
     */
    @Override
    public String toString() {
        throw new RuntimeException();
    }

    /** Returns the x coordinate of the gadget */
    public double getX() {
        throw new RuntimeException();
    }

    /** Returns the y coordinate of the gadget */
    public double getY() {
        throw new RuntimeException();
    }

    /** Returns the name of the flipper gadget */
    public String getName() {
        throw new RuntimeException();
    }

    /**
     * Returns a string describing the orientation of the flipper.
     * 
     * @return orientation of flipper
     */
    public FlipperOrientation getOrientation() {
        throw new RuntimeException();
    }

    /**
     * Moves the flipper for a time period time (this method is empty because it
     * is overridden by both left and right flipper)
     */
    public void move(double time) {
    }

    @Override
    public void reactBall(Ball ball) {
        throw new RuntimeException();
    }

    @Override
    public void action() {
        throw new RuntimeException();
    }

    public void hookActionToTrigger(Gadget gadget) {
        throw new RuntimeException();
    }

    @Override
    public String render(String input) {
        throw new RuntimeException();
    }
    
}
