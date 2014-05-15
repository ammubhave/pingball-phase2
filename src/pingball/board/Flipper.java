package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import pingball.client.ClientController;
import pingball.proto.Message;

/** Contains Flipper helpers */

public abstract class Flipper implements Gadget {
    /*
     * Threads Safety: All mutators first synchronize. No new threads created
     * from this methods of Flipper.java
     */

    /**
     * Represents the orientation of the flipper arm
     */
    public enum FlipperOrientation {
        TOP, BOTTOM, LEFT, RIGHT
    }

    /**
     * Represents the pivot orientation
     * 
     */
    public enum PivotOrientation {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    protected final List<LineSegment> sides = new ArrayList<LineSegment>();
    protected final List<Circle> cornerCircles = new ArrayList<Circle>();

    protected Double flipperAngle = 0.0;

    protected FlipperOrientation orientation;
    protected PivotOrientation pivot;
    protected List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    protected final String name;
    protected final Vect position;

    public final static double REFL_COEFF = 0.95;
    public final static double EDGE_LENGTH = 2;
    public final static double CORNER_DIAMETER = 0.5;
    public final static double CORNER_RADIUS = CORNER_DIAMETER / 2;
    public final static double ANGULAR_SPEED = 18.8495559; // rad/sec

    /*
     * Rep Invariant: - attributes are non-null - orientation and pivot must be
     * consistent
     */

    protected synchronized void checkRep() {
        assert orientation != null;
        assert sides != null;
        assert cornerCircles != null;
        assert gadgetsToBeHooked != null;
        assert name != null;

        switch (pivot) {
        case TOP_LEFT:
            assert orientation == FlipperOrientation.LEFT || orientation == FlipperOrientation.TOP;
            break;
        case TOP_RIGHT:
            assert orientation == FlipperOrientation.TOP || orientation == FlipperOrientation.RIGHT;
            break;
        case BOTTOM_RIGHT:
            assert orientation == FlipperOrientation.RIGHT || orientation == FlipperOrientation.BOTTOM;
            break;
        case BOTTOM_LEFT:
            assert orientation == FlipperOrientation.BOTTOM || orientation == FlipperOrientation.LEFT;
            break;
        default:
            assert false;
        }
    }

    public Flipper(Vect position, FlipperOrientation orientation, String name) {
        this.position = position;
        this.name = name;

        this.orientation = orientation;
        // REP INVARIANT IS NOT PRESERVED UNTIL CHILD CLASSES CONSTRUCTOR EXITS
    }

    /**
     * Gets the pivot vector
     * 
     * @return the pivot vector
     */
    protected synchronized Vect getPivotVect() {
        if (pivot == PivotOrientation.TOP_LEFT)
            return new Vect(this.position.x() + CORNER_RADIUS, this.position.y() + CORNER_RADIUS);
        else if (pivot == PivotOrientation.TOP_RIGHT)
            return new Vect(this.position.x() + EDGE_LENGTH - CORNER_RADIUS, this.position.y() + CORNER_RADIUS);
        else if (pivot == PivotOrientation.BOTTOM_LEFT)
            return new Vect(this.position.x() + CORNER_RADIUS, this.position.y() + EDGE_LENGTH - CORNER_RADIUS);
        else
            // BOTTOM_RIGHT
            return new Vect(this.position.x() + EDGE_LENGTH - CORNER_RADIUS, this.position.y() + EDGE_LENGTH
                    - CORNER_RADIUS);
    }

    @Override
    public void trigger() {
        GadgetHelpers.callActionOnGadgets(gadgetsToBeHooked);
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

    /**
     * Returns all the line segments that surround the flipper
     * 
     * @return list of line segments
     */
    public synchronized List<LineSegment> getLineSegments() {
        return new ArrayList<LineSegment>(this.sides);
    }

    /**
     * Returns the two circles that surround the flipper
     * 
     * @return list of circles
     */
    public synchronized List<Circle> getCircles() {
        return new ArrayList<Circle>(this.cornerCircles);
    }

    protected abstract double getVelocity();

    @Override
    public synchronized Message reactBall(Ball ball) {

        GadgetHelpers.playBounceSound();
        List<LineSegment> lines = sides;
        List<Circle> circles = cornerCircles;
        if (lines == null)
            lines = new ArrayList<LineSegment>();
        if (circles == null)
            circles = new ArrayList<Circle>();

        double smallestTimeWall = Double.POSITIVE_INFINITY;
        LineSegment smallestWall = null;
        double timeToWall = 0;
        for (LineSegment ls : lines) {
            timeToWall = Geometry.timeUntilWallCollision(ls, ball.getCircle(), ball.getVelocity());
            if (timeToWall <= smallestTimeWall) {
                smallestTimeWall = timeToWall;
                smallestWall = ls;
            }
        }
        Circle smallestCircle = null;
        double smallestTimeCircle = Double.POSITIVE_INFINITY;
        double timeToCircle = 0;
        for (Circle circ : circles) {
            timeToCircle = Geometry.timeUntilCircleCollision(circ, ball.getCircle(), ball.getVelocity());
            if (timeToCircle <= smallestTimeCircle) {
                smallestTimeCircle = timeToCircle;
                smallestCircle = circ;
            }
        }
        if (smallestTimeWall < smallestTimeCircle) {
            ball.changeVelocity(Geometry.reflectRotatingWall(smallestWall, getPivotVect(), getVelocity(),
                    ball.getCircle(), ball.getVelocity(), REFL_COEFF));
        } else {
            ball.changeVelocity(Geometry.reflectRotatingCircle(smallestCircle, getPivotVect(), getVelocity(),
                    ball.getCircle(), ball.getVelocity(), REFL_COEFF));
        }
        this.trigger();

        return null;
    }

    @Override
    public synchronized double leastCollisionTime(Ball ball) {
        List<LineSegment> lines = sides;
        List<Circle> circles = cornerCircles;
        if (lines == null)
            lines = new ArrayList<LineSegment>();
        if (circles == null)
            circles = new ArrayList<Circle>();

        double smallestTime = Double.POSITIVE_INFINITY;
        for (LineSegment ls : lines) {
            double time = Geometry.timeUntilRotatingWallCollision(ls, getPivotVect(), getVelocity(), ball.getCircle(),
                    ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
            }
        }
        for (Circle circ : circles) {
            double time = Geometry.timeUntilRotatingCircleCollision(circ, getPivotVect(), getVelocity(),
                    ball.getCircle(), ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
            }
        }
        return Math.max(smallestTime - ClientController.DT * 3, 0);
    }

    /**
     * Moves the flipper counterclockwise or clockwise depending on its
     * orientation and whether it is a left-flipper or right flipper
     */
    public synchronized void moveFlipper() {
        if (orientation == FlipperOrientation.TOP) {
            if (pivot == PivotOrientation.TOP_LEFT) {
                orientation = FlipperOrientation.LEFT;
            } else { // TOP_RIGHT
                orientation = FlipperOrientation.RIGHT;
            }
        } else if (orientation == FlipperOrientation.BOTTOM) {
            if (pivot == PivotOrientation.BOTTOM_LEFT) {
                orientation = FlipperOrientation.LEFT;
            } else { // BOTTOM_RIGHT
                orientation = FlipperOrientation.RIGHT;
            }
        } else if (orientation == FlipperOrientation.LEFT) {
            if (pivot == PivotOrientation.TOP_LEFT) {
                orientation = FlipperOrientation.TOP;
            } else { // BOTTOM_LEFT
                orientation = FlipperOrientation.BOTTOM;
            }
        } else { // RIGHT
            if (pivot == PivotOrientation.TOP_RIGHT) {
                orientation = FlipperOrientation.TOP;
            } else { // BOTTOM_RIGHT
                orientation = FlipperOrientation.BOTTOM;
            }
        }

        checkRep();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getX() {
        return this.position.x();
    }

    @Override
    public double getY() {
        return this.position.y();
    }

    @Override
    public synchronized String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        if (this.orientation == FlipperOrientation.TOP) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '-');
            sb.setCharAt(Board.getBoardStringIndexFromVect(position) + 1, '-');
        } else if (this.orientation == FlipperOrientation.RIGHT) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(1, 0))), '|');
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(1, 1))), '|');
        } else if (this.orientation == FlipperOrientation.LEFT) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(0, 0))), '|');
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(0, 1))), '|');
        } else if (this.orientation == FlipperOrientation.BOTTOM) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(0, 1))), '-');
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(1, 1))), '-');
        }
        return sb.toString();
    }
}
