package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/** Represents the LeftFlipper gadget class */

public class LeftFlipper extends Flipper {

    /**
     * Definition of orientation: 1: top 2: bottom 3: left 4: right
     */
    private FlipperOrientation orientation;
    /**
     * Definition of pivot: 1: topLeft 2: topRight 3: bottomLeft 4: bottomRight
     */
    private final int pivot;

    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = Double.MAX_VALUE;

    private boolean initial = true;

    private double flipperAngle = 0;

    private LineSegment topLine;
    private LineSegment leftLine;
    private LineSegment rightLine;
    private LineSegment bottomLine;

    private final List<LineSegment> sides = new ArrayList<LineSegment>();

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private LineSegment oneLineFlipper;

    private final String name;
    private double xLoc;
    private double yLoc;

    public LeftFlipper(Vect loc, FlipperOrientation orient, String n) {
        super(loc);
        xLoc = loc.x();
        yLoc = loc.y();

        name = n;

        this.orientation = orient;
        changeFlipperOrientation(orientation);

        sides.add(topLine);
        sides.add(leftLine);
        sides.add(rightLine);
        sides.add(bottomLine);

        if (orientation == FlipperOrientation.TOP) {
            pivot = 2;
        } else if (orientation == FlipperOrientation.BOTTOM) {
            pivot = 3;
        } else if (orientation == FlipperOrientation.LEFT) {
            pivot = 1;
        } else { // Right
            pivot = 4;
        }
    }

    @Override
    public void trigger() {
        for (int i = 0; i < gadgetsToBeHooked.size(); i++) {
            gadgetsToBeHooked.get(i).action();
        }
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

    public void reactBall(Ball ball) {
        Vect velocity = ball.getVelocity();
        LineSegment smallestTimeWall = oneLineFlipper;
        double smallestTime = Geometry.timeUntilWallCollision(smallestTimeWall, ball.getCircle(), velocity);
        ball.changeVelocity(Geometry.reflectWall(smallestTimeWall, velocity));

        this.trigger();
        // final double FLIPPER_SPEED = 18.8495559;
        // Vect newDir = Geometry.reflectRotatingWall(oneLineFlipper,
        // ball.getPos(), -FLIPPER_SPEED, ball.getCircle(),
        // velocity, 0.95);
        // newDir = new Vect(newDir.x(), -newDir.y());
        // ball.changeVelocity(newDir);
        // ball.move(TIME_TO_TRIGGER - tx);
    }

    @Override
    public double leastCollisionTime(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        double time = Geometry.timeUntilWallCollision(oneLineFlipper, ball.getCircle(), velocity);
        if (time < TIME_TO_TRIGGER) {
            return time;
        }
        return NULL;
    }

    /** @return the orientation of the left flipper: top, bottom, left, right */
    private int findFlipperOrientation() {
        if (orientation == FlipperOrientation.TOP) {
            if (pivot == 1) {
                return 3;
            } else {
                return 4;
            }
        } else if (orientation == FlipperOrientation.BOTTOM) {
            if (pivot == 1) {
                return 3;
            } else {
                return 4;
            }
        } else if (orientation == FlipperOrientation.LEFT) {
            if (pivot == 1) {
                return 1;
            } else {
                return 2;
            }
        } else { // RIGHT
            if (pivot == 2) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    /**
     * Changes the orientation of the flipper
     * 
     * @param orientation
     *            of the flipper before
     */
    private void changeFlipperOrientation(FlipperOrientation orientation) {
        if (orientation == FlipperOrientation.TOP) {
            oneLineFlipper = new LineSegment(xLoc, yLoc, xLoc + 2, yLoc);
            topLine = new LineSegment(xLoc, yLoc, xLoc + 2, yLoc);
            leftLine = new LineSegment(xLoc, yLoc, xLoc, yLoc + 0.5);
            rightLine = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 0.5);
            bottomLine = new LineSegment(xLoc, yLoc + 0.5, xLoc + 2, yLoc + 0.5);
        } else if (orientation == FlipperOrientation.BOTTOM) {
            oneLineFlipper = new LineSegment(xLoc, yLoc + 2, xLoc + 2, yLoc + 2);
            topLine = new LineSegment(xLoc, yLoc + 1.5, xLoc + 2, yLoc + 1.5);
            leftLine = new LineSegment(xLoc, yLoc + 1.5, xLoc, yLoc + 2);
            rightLine = new LineSegment(xLoc + 2, yLoc + 1.5, xLoc + 2, yLoc + 2);
            bottomLine = new LineSegment(xLoc, yLoc + 2, xLoc + 2, yLoc + 2);
        } else if (orientation == FlipperOrientation.LEFT) {
            oneLineFlipper = new LineSegment(xLoc, yLoc, xLoc, yLoc + 2);
            topLine = new LineSegment(xLoc, yLoc, xLoc + 0.5, yLoc);
            leftLine = new LineSegment(xLoc, yLoc, xLoc, yLoc + 2);
            rightLine = new LineSegment(xLoc + 0.5, yLoc, xLoc + 0.5, yLoc + 2);
            bottomLine = new LineSegment(xLoc, yLoc + 2, xLoc + 0.5, yLoc + 2);
        } else { // RIGHT
            oneLineFlipper = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 2);
            topLine = new LineSegment(xLoc + 1.5, yLoc, xLoc + 2, yLoc);
            leftLine = new LineSegment(xLoc + 1.5, yLoc, xLoc + 1.5, yLoc + 2);
            rightLine = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 2);
            bottomLine = new LineSegment(xLoc + 1.5, yLoc + 2, xLoc + 2, yLoc + 2);
        }
    }

    @Override
    public void action() {

        if (initial == true) {
            initial = false;
        } else {
            initial = true;
        }
        moveFlipper();
        // changeFlipperOrientation(findFlipperOrientation());
    }

    /** Moves the left flipper for a certain number of time */
    @Override
    public void move(double time) {
        boolean change = false;
        double FLIPPER_ANGULAR_VELOCITY = 1080;
        double angleToBeRotated = time * FLIPPER_ANGULAR_VELOCITY;
        while (angleToBeRotated > 360) {
            angleToBeRotated = angleToBeRotated - 360;
        }
        if (initial == true) {
            if (flipperAngle + angleToBeRotated < 90) {
                flipperAngle = flipperAngle + angleToBeRotated;
            } else {
                angleToBeRotated = 90 - flipperAngle;
                flipperAngle = 90;
                change = true;
                // initial = false;
            }
        } else {
            if (flipperAngle - angleToBeRotated > 0) {
                flipperAngle = flipperAngle - angleToBeRotated;
            } else {
                angleToBeRotated = flipperAngle;
                flipperAngle = 0;
                change = true;
                // initial = true;
            }
        }
        Vect pivotPoint;
        if (pivot == 1) {
            pivotPoint = new Vect(xLoc, yLoc);
        } else if (pivot == 2) {
            pivotPoint = new Vect(xLoc + 2, yLoc);
        } else if (pivot == 3) {
            pivotPoint = new Vect(xLoc, yLoc + 2);
        } else {
            pivotPoint = new Vect(xLoc + 2, yLoc + 2);
        }
        // System.out.println(getState());
        if (initial == true) {
            oneLineFlipper = Geometry.rotateAround(oneLineFlipper, pivotPoint, new Angle(0 - angleToBeRotated * Math.PI
                    / 180.0));
        } else {
            oneLineFlipper = Geometry.rotateAround(oneLineFlipper, pivotPoint, new Angle(angleToBeRotated * Math.PI
                    / 180.0));
        }
        if (change) {
            initial = !initial;
        }
        // System.out.println(getState());
    }

    public void moveFlipper() {
        if (orientation == FlipperOrientation.TOP) {
            if (pivot == 1) {
                orientation = FlipperOrientation.LEFT;
            } else {
                orientation = FlipperOrientation.RIGHT;
            }
        } else if (orientation == FlipperOrientation.BOTTOM) {
            if (pivot == 3) {
                orientation = FlipperOrientation.LEFT;
            } else {
                orientation = FlipperOrientation.RIGHT;
            }
        } else if (orientation == FlipperOrientation.LEFT) {
            if (pivot == 1) {
                orientation = FlipperOrientation.TOP;
            } else {
                orientation = FlipperOrientation.BOTTOM;
            }
        } else { // RIGHT
            if (pivot == 2) {
                orientation = FlipperOrientation.TOP;
            } else {
                orientation = FlipperOrientation.BOTTOM;
            }
        }
        changeFlipperOrientation(orientation);
    }

    /** Returns the current state of the left flipper while is is being rotated */
    public String getState() {
        String state = "";
        state = "Flipper Angle: " + flipperAngle + "\n";
        state = state + "X1: " + oneLineFlipper.p1().x() + "Y1: " + oneLineFlipper.p1().y() + "X2: "
                + oneLineFlipper.p2().x() + " Y2: " + oneLineFlipper.p2().y();
        return state;
    }

    /**
     * Returns String representation of the board depending on whether it's
     * vertical or not. Does not support diagonal flippers
     * 
     * @return string representation of a flipper
     */
    @Override
    public String toString() {
        if (orientation == FlipperOrientation.TOP || orientation == FlipperOrientation.BOTTOM) {
            return "_";
        } else {
            return "|";
        }
    }

    /** Returns the name of the LeftFlipper gadget */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns a string describing the orientation of the flipper.
     * 
     * @return orientation of flipper
     */
    @Override
    public FlipperOrientation getOrientation() {
        return this.orientation;
    }

    @Override
    public String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        Vect position = new Vect(this.xLoc, this.yLoc);
        // I am assuming NW=TOP, NE=RIGHT, SE=LEFT, SW=BOTTOM
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