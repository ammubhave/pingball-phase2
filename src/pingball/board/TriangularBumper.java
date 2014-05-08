package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class TriangularBumper implements Gadget {
    /**
     * Thread Safety Information: TriangularBumper is threadsafe because it is
     * never altered after creation.
     */
    public enum TriangularBumperOrientation {
        TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT
    }

    private final static double REFL_COEFF = 1.0;
    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = 5; // Used as placeholder value

    private final double xCoord;
    private final double yCoord;
    private final double legLength = 1.0;
    private final double hypotenuseLength = Math.sqrt(2);
    private final TriangularBumperOrientation orientation; // in terms of degrees
    private final String name;

    private final LineSegment leg1; // horizontal leg
    private final LineSegment leg2; // vertical leg
    private final LineSegment hypotenuse;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private final List<LineSegment> sides = new ArrayList<LineSegment>();

    /**
     * Creates a 45-45-90 triangle bumper with the user-inputted parameters
     * 
     * @param loc
     *            , Vect location of top-left point of square which contains the
     *            triangle
     * @param orientation
     *            , angle triangle bumper is rotated
     * @param name
     */
    public TriangularBumper(Vect loc, TriangularBumperOrientation orientation, String name) {

        this.xCoord = loc.x();
        this.yCoord = loc.y();
        this.name = name;
        if (orientation == TriangularBumperOrientation.TOP_LEFT) { // Right angle is in top-left corner
            leg1 = new LineSegment(xCoord, yCoord, xCoord + legLength, yCoord);
            leg2 = new LineSegment(xCoord, yCoord, xCoord, yCoord + legLength);
            hypotenuse = new LineSegment(xCoord + legLength, yCoord, xCoord, yCoord + legLength);
        } else if (orientation == TriangularBumperOrientation.TOP_RIGHT) { // Right angle is in top-right corner
            leg1 = new LineSegment(xCoord, yCoord, xCoord + legLength, yCoord);
            leg2 = new LineSegment(xCoord + legLength, yCoord, xCoord + legLength, yCoord + legLength);
            hypotenuse = new LineSegment(xCoord, yCoord, xCoord + legLength, yCoord + legLength);
        } else if (orientation == TriangularBumperOrientation.BOTTOM_RIGHT) { // Right angle is in bottom-right
                                         // corner
            leg1 = new LineSegment(xCoord, yCoord + legLength, xCoord + legLength, yCoord + legLength);
            leg2 = new LineSegment(xCoord + legLength, yCoord, xCoord + legLength, yCoord + legLength);
            hypotenuse = new LineSegment(xCoord, yCoord + legLength, xCoord + legLength, yCoord);
        } else { // Right angle is in bottom-left corner
            leg1 = new LineSegment(xCoord, yCoord + legLength, xCoord + legLength, yCoord + legLength);
            leg2 = new LineSegment(xCoord, yCoord, xCoord, yCoord + legLength);
            hypotenuse = new LineSegment(xCoord, yCoord, xCoord + legLength, yCoord + legLength);
        }

        this.orientation = orientation;

        sides.add(leg1);
        sides.add(leg2);
        sides.add(hypotenuse);
    }

    public void trigger() {
        for (int i = 0; i < gadgetsToBeHooked.size(); i++) {
            gadgetsToBeHooked.get(i).action();
        }
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
    public double leastCollisionTime(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        double smallestTime = Double.MAX_VALUE;
        for (LineSegment ls : sides) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), velocity);
            if (time < smallestTime) {
                smallestTime = time;
            }
        }
        return smallestTime;
    }

    /**
     * Called when inputted ball is less than 0.001 seconds from impacting
     * gadget (as found out from the trigger function). Handles the resulting
     * physics of when given ball colldes with this bumper.
     */
    @Override
    public void action() {

    }

    public void reactBall(Ball ball) {
        Vect velocity = ball.getVelocity();
        double smallestTime = Double.MAX_VALUE;
        LineSegment smallestTimeWall = null;
        for (LineSegment ls : sides) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), velocity);
            if (time < smallestTime) {
                smallestTime = time;
                smallestTimeWall = ls;
            }
        }

        ball.changeVelocity(Geometry.reflectWall(smallestTimeWall, velocity));
        this.trigger();
    }

    /**
     * @return the reflection coefficient
     */
    @Override
    public double getReflCoeff() {
        return REFL_COEFF;
    }

    /**
     * Returns a list of the three line segments used to make this bumper: leg1
     * (horizontal), leg2 (vertical), and the hypotenuse
     * 
     * @return list of all the line segments that make up the bumper
     */
    public List<LineSegment> getLineSegments() {
        List<LineSegment> ans = new ArrayList<LineSegment>();
        ans.add(leg1);
        ans.add(leg2);
        ans.add(hypotenuse);
        return ans;
    }

    /**
     * @return a string representation of a triangular bumper as seen on a
     *         board. Changes depending on orientation
     */
    @Override
    public String toString() {
        if (orientation == TriangularBumperOrientation.TOP_LEFT || orientation == TriangularBumperOrientation.BOTTOM_RIGHT) {
            return "/";
        } else {
            return "\\";
        }
    }

    /**
     * @return x coordinate of the top-left corner of bumper.
     */
    public double getX() {
        return xCoord;
    }

    /**
     * @return y coordinate of the top-left corner of bumper.
     */
    public double getY() {
        return yCoord;
    }

    /**
     * @return name of the bumper
     */
    public String getName() {
        return name;
    }

    /**
     * @return string representing the type of gadget.
     */
    public String type() {
        return "triangle";
    }

    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

    @Override
    public String render(String input) {
        Vect position = new Vect(this.xCoord, this.yCoord);
        StringBuilder sb = new StringBuilder(input);
        
        if (this.orientation == orientation.TOP_LEFT || this.orientation == orientation.BOTTOM_RIGHT){
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '/');
        }
        if (this.orientation == orientation.TOP_RIGHT || this.orientation == orientation.BOTTOM_LEFT){
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '\\');
        }
        return sb.toString();
    }

}
