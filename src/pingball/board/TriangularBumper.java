package pingball.board;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import pingball.proto.Message;

public class TriangularBumper implements Gadget {
    /**
     * Thread Safety Information: TriangularBumper is threadsafe because it is
     * never altered after creation.
     */
    public enum TriangularBumperOrientation {
        TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT
    }

    private final static double REFL_COEFF = 1.0;

    private final double xCoord;
    private final double yCoord;
    private final double legLength = 1.0;

    private final TriangularBumperOrientation orientation; // in terms of
                                                           // degrees
    private final String name;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private final List<LineSegment> sides = new ArrayList<LineSegment>();
    private final List<Circle> corners = new ArrayList<Circle>();

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

        LineSegment leg1; // horizontal leg
        LineSegment leg2; // vertical leg
        LineSegment hypotenuse;
        Circle angle1; // horizontal leg
        Circle angle2; // vertical leg
        Circle rightAngle;

        this.xCoord = loc.x();
        this.yCoord = loc.y();
        this.name = name;
        if (orientation == TriangularBumperOrientation.TOP_LEFT) { // Right
                                                                   // angle is
                                                                   // in
                                                                   // top-left
                                                                   // corner
            leg1 = new LineSegment(xCoord, yCoord, xCoord + legLength, yCoord);
            leg2 = new LineSegment(xCoord, yCoord, xCoord, yCoord + legLength);
            hypotenuse = new LineSegment(xCoord + legLength, yCoord, xCoord, yCoord + legLength);
            angle1 = new Circle(xCoord + legLength, yCoord, 0);
            angle2 = new Circle(xCoord, yCoord + legLength, 0);
            rightAngle = new Circle(xCoord, yCoord, 0);
        } else if (orientation == TriangularBumperOrientation.TOP_RIGHT) { // Right
                                                                           // angle
                                                                           // is
                                                                           // in
                                                                           // top-right
                                                                           // corner
            leg1 = new LineSegment(xCoord, yCoord, xCoord + legLength, yCoord);
            leg2 = new LineSegment(xCoord + legLength, yCoord, xCoord + legLength, yCoord + legLength);
            hypotenuse = new LineSegment(xCoord, yCoord, xCoord + legLength, yCoord + legLength);
            angle1 = new Circle(xCoord + legLength, yCoord + legLength, 0);
            angle2 = new Circle(xCoord, yCoord, 0);
            rightAngle = new Circle(xCoord + legLength, yCoord, 0);
        } else if (orientation == TriangularBumperOrientation.BOTTOM_RIGHT) { // Right
                                                                              // angle
                                                                              // is
                                                                              // in
                                                                              // bottom-right
                                                                              // corner
            leg1 = new LineSegment(xCoord, yCoord + legLength, xCoord + legLength, yCoord + legLength);
            leg2 = new LineSegment(xCoord + legLength, yCoord, xCoord + legLength, yCoord + legLength);
            hypotenuse = new LineSegment(xCoord, yCoord + legLength, xCoord + legLength, yCoord);
            angle1 = new Circle(xCoord, yCoord + legLength, 0);
            angle2 = new Circle(xCoord + legLength, yCoord, 0);
            rightAngle = new Circle(xCoord + legLength, yCoord + legLength, 0);
        } else { // Right angle is in bottom-left corner
            leg1 = new LineSegment(xCoord, yCoord + legLength, xCoord + legLength, yCoord + legLength);
            leg2 = new LineSegment(xCoord, yCoord, xCoord, yCoord + legLength);
            hypotenuse = new LineSegment(xCoord, yCoord, xCoord + legLength, yCoord + legLength);
            angle1 = new Circle(xCoord, yCoord, 0);
            angle2 = new Circle(xCoord + legLength, yCoord + legLength, 0);
            rightAngle = new Circle(xCoord, yCoord + legLength, 0);
        }

        this.orientation = orientation;

        sides.add(leg1);
        sides.add(leg2);
        sides.add(hypotenuse);
        corners.add(angle1);
        corners.add(angle2);
        corners.add(rightAngle);
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
        return GadgetHelpers.leastCollisionTime(sides, corners, ball);
    }

    /**
     * Called when inputted ball is less than 0.001 seconds from impacting
     * gadget (as found out from the trigger function). Handles the resulting
     * physics of when given ball colldes with this bumper.
     */
    @Override
    public void action() {

    }

    public List<Message> reactBall(Ball ball) {
        Toolkit.getDefaultToolkit().beep();
        GadgetHelpers.reflectBall(sides, corners, ball, REFL_COEFF);
        this.trigger();

        return new ArrayList<Message>();
    }

    /**
     * Returns a list of the three line segments used to make this bumper: leg1
     * (horizontal), leg2 (vertical), and the hypotenuse
     * 
     * @return list of all the line segments that make up the bumper
     */
    public List<LineSegment> getLineSegments() {
        return new ArrayList<LineSegment>(this.sides);
    }

    /**
     * @return a string representation of a triangular bumper as seen on a
     *         board. Changes depending on orientation
     */
    @Override
    public String toString() {
        if (orientation == TriangularBumperOrientation.TOP_LEFT
                || orientation == TriangularBumperOrientation.BOTTOM_RIGHT) {
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

        if (this.orientation == orientation.TOP_LEFT || this.orientation == orientation.BOTTOM_RIGHT) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '/');
        }
        if (this.orientation == orientation.TOP_RIGHT || this.orientation == orientation.BOTTOM_LEFT) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '\\');
        }
        return sb.toString();
    }

}
