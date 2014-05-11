package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import pingball.board.Flipper.FlipperOrientation;

/** Represents the RightFlipper gadget class */

public class RightFlipper implements Gadget {

    /**
     * Definition of orientation: 1: top 2: bottom 3: left 4: right
     */
    private FlipperOrientation orientation;
    /**
     * Definition of pivot: 1: topLeft 2: topRight 3: bottomLeft 4: bottomRight
     */
    private final int pivot;

    private boolean initial = true;
    private double flipperAngle;
    private LineSegment topLine;
    private LineSegment leftLine;
    private LineSegment rightLine;
    private LineSegment bottomLine;

    private final List<LineSegment> sides = new ArrayList<LineSegment>();

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private LineSegment oneLineFlipper;

    private final double xLoc;
    private final double yLoc;
    private final String name;

    public RightFlipper(Vect loc, FlipperOrientation orient, String n) {
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
            pivot = 1;
        } else if (orientation == FlipperOrientation.BOTTOM) {
            pivot = 4;
        } else if (orientation == FlipperOrientation.LEFT) {
            pivot = 3;
        } else {
            pivot = 2;
        }
    }

    @Override
    public void trigger() {
        for (int i = 0; i < gadgetsToBeHooked.size(); i++) {
            gadgetsToBeHooked.get(i).action();
        }
    }

    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

    public double leastCollisionTime(Ball ball) {
        Vect velocity = ball.getVelocity();
        LineSegment smallestTimeWall = oneLineFlipper;
        double smallestTime = Geometry.timeUntilWallCollision(smallestTimeWall, ball.getCircle(), velocity);
        return smallestTime;
    }

    public void reactBall(Ball ball) {
        Vect velocity = ball.getVelocity();
        LineSegment smallestTimeWall = oneLineFlipper;
        double smallestTime = Geometry.timeUntilWallCollision(smallestTimeWall, ball.getCircle(), velocity);
        ball.changeVelocity(Geometry.reflectWall(smallestTimeWall, velocity));

        this.trigger();
        // final double FLIPPER_SPEED = 18.8495559;
        // Vect newDir = Geometry.reflectWall(wall, velocity, 0.95);
        // Vect newDir = Geometry.reflectRotatingWall(wall, ball.getPos(),
        // FLIPPER_SPEED, ball.getCircle(), velocity, 0.95);
        // newDir = new Vect(newDir.x(), -newDir.y());
        // ball.changeVelocity(newDir);
        // ball.move(TIME_TO_TRIGGER - tx);
    }

    /**
     * Returns the orientation of the flipper; whether it is up, down, left, or
     * right
     */
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
        } else {
            if (pivot == 2) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    /**
     * Changes the orientation of the flipper to up, down, left, or right
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
        } else {
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
        } else {
            if (pivot == 2) {
                orientation = FlipperOrientation.TOP;
            } else {
                orientation = FlipperOrientation.BOTTOM;
            }
        }
        changeFlipperOrientation(orientation);
    }

    /** Returns the angle of the current flipper */
    public double getState() {
        return flipperAngle;
    }

    @Override
    public String toString() {
        if (orientation == FlipperOrientation.TOP || orientation == FlipperOrientation.BOTTOM) {
            return "_";
        } else {
            return "|";
        }
    }

    /**
     * Returns a string describing the orientation of the flipper.
     * 
     * @return orientation of flipper
     */
    public FlipperOrientation getOrientation() {
        return this.orientation;
    }

    /** Returns the name of the gadget */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representing the type of gadget.
     */
    public String type() {
        return "flipper";
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

    @Override
    public double getX() {
        return this.xLoc;
    }

    @Override
    public double getY() {
        return this.yLoc;
    }
}
