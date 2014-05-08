package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class SquareBumper implements Gadget {

    /**
     * Thread Safety Information: SquareBumper is threadsafe because it is never
     * altered after creation.
     */
    private final static double REFL_COEFF = 1.0;

    private final double xCoord;
    private final double yCoord;

    // Following are constants specified by the project
    private final static double EDGE_LENGTH = 1.0;
    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = 5; // Used as placeholder value

    private final LineSegment topLine;
    private final LineSegment bottomLine;
    private final LineSegment leftLine;
    private final LineSegment rightLine;

    private final Circle topLeft;
    private final Circle topRight;
    private final Circle bottomLeft;
    private final Circle bottomRight;

    private final String name;
    private Vect position;
    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private final List<LineSegment> sides = new ArrayList<LineSegment>();
    private final List<Circle> cornerCircles = new ArrayList<Circle>();

    /**
     * Creates a square bumper with the user-inputted parameters. Has a
     * side-length of 1.0
     * 
     * @param loc
     *            , a vector representing the top left point of the square
     * @param n
     *            , name
     */
    public SquareBumper(Vect loc, String n) {
        name = n;
        position = loc;
        xCoord = loc.x();
        yCoord = loc.y();
        topLine = new LineSegment(xCoord, yCoord, xCoord + EDGE_LENGTH, yCoord);
        bottomLine = new LineSegment(xCoord, yCoord + EDGE_LENGTH, xCoord + EDGE_LENGTH, yCoord + EDGE_LENGTH);
        leftLine = new LineSegment(xCoord, yCoord, xCoord, yCoord + EDGE_LENGTH);
        rightLine = new LineSegment(xCoord + EDGE_LENGTH, yCoord, xCoord + EDGE_LENGTH, yCoord + EDGE_LENGTH);

        sides.add(topLine);
        sides.add(bottomLine);
        sides.add(leftLine);
        sides.add(rightLine);

        topLeft = new Circle(xCoord, yCoord, 0);
        topRight = new Circle(xCoord + 1, yCoord, 0);
        bottomLeft = new Circle(xCoord, yCoord + 1, 0);
        bottomRight = new Circle(xCoord + 1, yCoord + 1, 0);

        cornerCircles.add(topLeft);
        cornerCircles.add(topRight);
        cornerCircles.add(bottomLeft);
        cornerCircles.add(bottomRight);
    }

    /**
     * Calculates time an inputted ball will take to hit this bumper. Returns a
     * very large value if not nearby (5 seconds).
     * 
     * @param ball
     *            to check if it's nearby
     * @return amount of time to take to trigger object based on inputted ball.
     */
    @Override
    public void trigger() {
        for (int i = 0; i < gadgetsToBeHooked.size(); i++) {
            gadgetsToBeHooked.get(i).action();
        }
    }

    @Override
    public double leastCollisionTime(Ball ball) {
        Vect velocity = ball.getVelocity();
        double smallestTime = Double.MAX_VALUE;
        for (LineSegment ls : sides) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), velocity);
            if (time < smallestTime) {
                smallestTime = time;
            }
        }
        for (Circle circ : cornerCircles) {
            double time = Geometry.timeUntilCircleCollision(circ, ball.getCircle(), velocity);
            if (time < smallestTime) {
                smallestTime = time;
            }
        }
        return smallestTime;
    }

    /**
     * Called when inputted ball is less than 0.001 seconds from impacting
     * gadget (as found out from the trigger function). Handles the resulting
     * physics of when given ball collides with this bumper.
     */
    @Override
    public void action() {

    }

    public void reactBall(Ball ball) {
        Vect velocity = ball.getVelocity();
        double smallestTimeWall = Double.MAX_VALUE;
        LineSegment smallestWall = null;
        double timeToWall = 0;
        for (LineSegment ls : sides) {
            timeToWall = Geometry.timeUntilWallCollision(ls, ball.getCircle(), velocity);
            if (timeToWall < smallestTimeWall) {
                smallestTimeWall = timeToWall;
                smallestWall = ls;
            }
        }
<<<<<<< HEAD
        Circle smallestCircle = null;
        double smallestTimeCircle = Double.MAX_VALUE;
        double timeToCircle = 0;
        for (Circle circ : cornerCircles) {
            timeToCircle = Geometry.timeUntilCircleCollision(circ, ball.getCircle(), velocity);
            if (timeToCircle < smallestTimeCircle) {
                smallestTimeCircle = timeToCircle;
                smallestCircle = circ;
            }
        }
        if (smallestTimeWall < smallestTimeCircle) {
            ball.changeVelocity(Geometry.reflectWall(smallestWall, velocity));
        } else {
            ball.changeVelocity(Geometry.reflectCircle(smallestCircle.getCenter(), ball.getPos(), ball.getVelocity()));
        }

=======
        ball.changeVelocity(Geometry.reflectWall(smallestTimeWall, velocity));
        this.trigger();
>>>>>>> 9d2fa19cb0f63cbf3d4c6861bb07490c9d6566f1
    }

    /**
     * @return the reflection coefficient
     */
    @Override
    public double getReflCoeff() {
        return REFL_COEFF;
    }

    /**
     * Returns a list of the lines used to make the square: top line, bottom
     * line, left line, right line
     * 
     * @return list of lines that make up this bumper
     */
    public List<LineSegment> getLineSegments() {
        List<LineSegment> ans = new ArrayList<LineSegment>();
        ans.add(topLine);
        ans.add(bottomLine);
        ans.add(leftLine);
        ans.add(rightLine);
        return ans;
    }

    /**
     * Returns a string representation of a square bumper as seen on the board.
     */
    @Override
    public String toString() {
        return "#";
    }

    /**
     * @return x coordinate of top-left corner of bumper.
     */
    public double getX() {
        return this.xCoord;
    }

    /**
     * @return y coordinate of top-left corner of bumper.
     */
    public double getY() {
        return this.yCoord;
    }

    /**
     * @return name of the bumper
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representing the type of gadget.
     */
    public String type() {
        return "square";
    }

    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

    @Override
    public String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(Board.getBoardStringIndexFromVect(position), '#');
        return sb.toString();
    }

}
