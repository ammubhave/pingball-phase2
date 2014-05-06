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

    private LineSegment boundingBoxTop;
    private LineSegment boundingBoxBottom;
    private LineSegment boundingBoxLeft;
    private LineSegment boundingBoxRight;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private double xCoord;
    private double yCoord;

    public Flipper(Vect vect) {
        xCoord = vect.x();
        yCoord = vect.y();
        boundingBoxTop = new LineSegment(xCoord, yCoord, xCoord + boundingBoxLength, yCoord);
        boundingBoxBottom = new LineSegment(xCoord, yCoord + boundingBoxLength, xCoord + boundingBoxLength, yCoord
                + boundingBoxLength);
        boundingBoxLeft = new LineSegment(xCoord, yCoord, xCoord, yCoord + boundingBoxLength);
        boundingBoxRight = new LineSegment(xCoord + boundingBoxLength, yCoord, xCoord + boundingBoxLength, yCoord
                + boundingBoxLength);
    }

    /** Returns the top line segment of the bounding box */
    public LineSegment getTop() {
        return new LineSegment(xCoord, yCoord, xCoord + boundingBoxLength, yCoord);
    }

    /** Returns the bottom line segment of the bounding box */
    public LineSegment getBottom() {
        return new LineSegment(xCoord, yCoord + boundingBoxLength, xCoord + boundingBoxLength, yCoord
                + boundingBoxLength);
    }

    /** Returns the left line segment of the bounding box */
    public LineSegment getLeft() {
        return new LineSegment(xCoord, yCoord, xCoord, yCoord + boundingBoxLength);
    }

    /** Returns the right line segment of the bounding box */
    public LineSegment getRight() {
        return new LineSegment(xCoord + boundingBoxLength, yCoord, xCoord + boundingBoxLength, yCoord
                + boundingBoxLength);
    }

    /** Returns the time before collision */
    public double leastCollisionTime(Ball ball) {
        return NULL;
    }

    @Override
    public void trigger() {

    }

    /** Acts on a collision */
    public void action(Ball ball) {
    }

    /** Returns the reflection coefficient */
    public double getReflCoeff() {
        return coefficientOfReflection;
    }

    /**
     * Returns a string representation of the flipper.
     */
    @Override
    public String toString() {
        // TODO: Figure out how to represent vertical vs horizontal
        return "";
    }

    /** Returns the x coordinate of the gadget */
    public double getX() {
        return xCoord;
    }

    /** Returns the y coordinate of the gadget */
    public double getY() {
        return yCoord;
    }

    /** Returns the name of the flipper gadget */
    public String getName() {
        return "";
    }

    /** Returns the state of the gadget */
    public double getState(double angle) {
        return 0;
    }

    /**
     * Returns a string representing the type of gadget.
     */
    public String type() {
        return "flipper";
    }

    /**
     * Returns a string describing the orientation of the flipper.
     * 
     * @return orientation of flipper
     */
    public FlipperOrientation getOrientation() {
        return FlipperOrientation.TOP;
    }

    /**
     * Moves the flipper for a time period time (this method is empty because it
     * is overridden by both left and right flipper)
     */
    public void move(double time) {
    }

    @Override
    public void reactBall(Ball ball) {
        // TODO Auto-generated method stub

    }

    @Override
    public void action() {
        // TODO Auto-generated method stub

    }

    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }
}
