package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import pingball.proto.Message;

/** This class represents a square bumper gadget */

public class SquareBumper implements Gadget {

    /**
     * Thread Safety Information: SquareBumper is threadsafe because it is never
     * altered after creation. gadget hooking is done only in factory
     */

    private final static double EDGE_LENGTH = 1.0;
    private final static double REFL_COEFF = 1.0;

    private final String name;
    private final Vect position;
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
    public SquareBumper(Vect position, String name) {
        this.name = name;
        this.position = position;
        double x = position.x();
        double y = position.y();

        sides.add(new LineSegment(x, y, x + EDGE_LENGTH, y));
        sides.add(new LineSegment(x, y + EDGE_LENGTH, x + EDGE_LENGTH, y + EDGE_LENGTH));
        sides.add(new LineSegment(x, y, x, y + EDGE_LENGTH));
        sides.add(new LineSegment(x + EDGE_LENGTH, y, x + EDGE_LENGTH, y + EDGE_LENGTH));

        cornerCircles.add(new Circle(x, y, 0));
        cornerCircles.add(new Circle(x + 1, y, 0));
        cornerCircles.add(new Circle(x, y + 1, 0));
        cornerCircles.add(new Circle(x + 1, y + 1, 0));
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
        GadgetHelpers.callActionOnGadgets(gadgetsToBeHooked);
    }

    @Override
    public double leastCollisionTime(Ball ball) {
        return GadgetHelpers.leastCollisionTime(sides, cornerCircles, ball);
    }

    /**
     * Called when inputted ball is less than 0.001 seconds from impacting
     * gadget (as found out from the trigger function). Handles the resulting
     * physics of when given ball collides with this bumper.
     */
    @Override
    public void action() {

    }

    public Message reactBall(Ball ball) {
        GadgetHelpers.playBounceSound();
        GadgetHelpers.reflectBall(sides, cornerCircles, ball, REFL_COEFF);
        this.trigger();
        return null;
    }

    /**
     * Returns a list of the lines used to make the gadget
     * 
     * @return list of lines that make up this bumper
     */
    public List<LineSegment> getLineSegments() {
        return new ArrayList<LineSegment>(sides);
    }

    /**
     * @return x coordinate of top-left corner of bumper.
     */
    public double getX() {
        return this.position.x();
    }

    /**
     * @return y coordinate of top-left corner of bumper.
     */
    public double getY() {
        return this.position.y();
    }

    /**
     * @return name of the bumper
     */
    public String getName() {
        return name;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SquareBumper))
            return false;
        return ((SquareBumper) obj).getName().equals(this.getName());
    }
}
