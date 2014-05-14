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
     * gadget hooking is done only in factory 
     */
    public enum TriangularBumperOrientation {
        TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT
    }

    private final static double REFL_COEFF = 1.0;

    private final Vect position;
    private final double legLength = 1.0;

    private final TriangularBumperOrientation orientation;
    private final String name;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private final List<LineSegment> sides = new ArrayList<LineSegment>();
    private final List<Circle> corners = new ArrayList<Circle>();
    
    /*
     * Rep Invariant:
     * - all attributes should be non-null
     */
    private void checkRep() {
        assert position != null;
        assert orientation != null;
        assert name != null;
        assert gadgetsToBeHooked != null;
        assert sides != null;
        assert corners != null;
    }

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
    public TriangularBumper(Vect position, TriangularBumperOrientation orientation, String name) {
        this.position = position;
        LineSegment leg1; // horizontal leg
        LineSegment leg2; // vertical leg
        LineSegment hypotenuse;
        Circle angle1; // horizontal leg
        Circle angle2; // vertical leg
        Circle rightAngle;

        double x = position.x();
        double y = position.y();
        this.name = name;
        if (orientation == TriangularBumperOrientation.TOP_LEFT) { // Right
                                                                   // angle is
                                                                   // in
                                                                   // top-left
                                                                   // corner
            leg1 = new LineSegment(x, y, x + legLength, y);
            leg2 = new LineSegment(x, y, x, y + legLength);
            hypotenuse = new LineSegment(x + legLength, y, x, y + legLength);
            angle1 = new Circle(x + legLength, y, 0);
            angle2 = new Circle(x, y + legLength, 0);
            rightAngle = new Circle(x, y, 0);
        } else if (orientation == TriangularBumperOrientation.TOP_RIGHT) { // Right
                                                                           // angle
                                                                           // is
                                                                           // in
                                                                           // top-right
                                                                           // corner
            leg1 = new LineSegment(x, y, x + legLength, y);
            leg2 = new LineSegment(x + legLength, y, x + legLength, y + legLength);
            hypotenuse = new LineSegment(x, y, x + legLength, y + legLength);
            angle1 = new Circle(x + legLength, y + legLength, 0);
            angle2 = new Circle(x, y, 0);
            rightAngle = new Circle(x + legLength, y, 0);
        } else if (orientation == TriangularBumperOrientation.BOTTOM_RIGHT) { // Right
                                                                              // angle
                                                                              // is
                                                                              // in
                                                                              // bottom-right
                                                                              // corner
            leg1 = new LineSegment(x, y + legLength, x + legLength, y + legLength);
            leg2 = new LineSegment(x + legLength, y, x + legLength, y + legLength);
            hypotenuse = new LineSegment(x, y + legLength, x + legLength, y);
            angle1 = new Circle(x, y + legLength, 0);
            angle2 = new Circle(x + legLength, y, 0);
            rightAngle = new Circle(x + legLength, y + legLength, 0);
        } else { // Right angle is in bottom-left corner
            leg1 = new LineSegment(x, y + legLength, x + legLength, y + legLength);
            leg2 = new LineSegment(x, y, x, y + legLength);
            hypotenuse = new LineSegment(x, y, x + legLength, y + legLength);
            angle1 = new Circle(x, y, 0);
            angle2 = new Circle(x + legLength, y + legLength, 0);
            rightAngle = new Circle(x, y + legLength, 0);
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

    @Override
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

    @Override
    public double getX() {
        return this.position.x();
    }

    @Override
    public double getY() {
        return this.position.y();
    }
 
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

    @Override
    public String render(String input) {        
        StringBuilder sb = new StringBuilder(input);

        if (this.orientation == TriangularBumperOrientation.TOP_LEFT || this.orientation == TriangularBumperOrientation.BOTTOM_RIGHT) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '/');
        }
        if (this.orientation == TriangularBumperOrientation.TOP_RIGHT || this.orientation == TriangularBumperOrientation.BOTTOM_LEFT) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '\\');
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TriangularBumper)) return false;
        return ((TriangularBumper)obj).getName().equals(this.getName());
    }
}
