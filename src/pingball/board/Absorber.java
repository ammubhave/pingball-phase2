package pingball.board;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import pingball.proto.Message;

/** This class represents an Absorber Gadget */

public class Absorber implements Gadget {
    /*
     * Thread Safety Information: Absorber is threadsafe because - The only
     * mutable attribute is 'heldBall' and it is accessed in synchronized
     * manner. - gadgetsToBeHooked is only modified in factory. - All other
     * attributes are immutable
     */
    private final int width; // measured horizontally
    private final int height; // measured vertically
    private final Vect position;

    private Ball heldBall = null;

    private final static Vect SHOOT_VELOCITY = new Vect(0, -50);

    private final String name;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();
    private final List<LineSegment> sides = new ArrayList<LineSegment>();
    private final List<Circle> cornerCircles = new ArrayList<Circle>();

    /*
     * Rep Invariant: - heldBall, if non-null, should be within the bounds of
     * the absorber - width, height must be positive - all others must be
     * non-null
     */
    private void checkRep() {
        assert position != null;
        assert sides != null;
        assert cornerCircles != null;
        assert gadgetsToBeHooked != null;
        assert name != null;

        if (heldBall != null) {
            assert heldBall.getCircle().getCenter().x() > position.x();
            assert heldBall.getCircle().getCenter().x() < position.x() + width;
            assert heldBall.getCircle().getCenter().y() > position.y();
            assert heldBall.getCircle().getCenter().y() < position.y() + height;
        }

        assert width > 0;
        assert height > 0;
    }

    /**
     * Constructor: creates an absorber gadget with the following user-inputted
     * parameters.
     * 
     * @param position
     *            vector representing the starting location
     * @param width
     *            horizontal distance of absorber
     * @param height
     *            vertical distance of absorber
     * @param name
     *            name of absorber
     */
    public Absorber(Vect position, int width, int height, String name) {
        this.position = position;
        this.name = name;
        this.width = width;
        this.height = height;

        double x = this.position.x();
        double y = this.position.y();

        sides.add(new LineSegment(x, y, x + width, y)); // Top Line
        sides.add(new LineSegment(x, y + height, x + width, y + height)); // Bottom Line
        sides.add(new LineSegment(x, y, x, y + height)); // Left Line
        sides.add(new LineSegment(x + width, y, x + width, y + height)); // Right Line

        cornerCircles.add(new Circle(x, y, 0)); // Top Left Circle
        cornerCircles.add(new Circle(x + width, y, 0)); // Top Right Circle
        cornerCircles.add(new Circle(x, y + height, 0)); // Bottom Left Circle
        cornerCircles.add(new Circle(x + width, y + height, 0)); // Bottom Right Circle

        checkRep();
    }

    @Override
    public void trigger() {
        GadgetHelpers.callActionOnGadgets(gadgetsToBeHooked);
    }

    @Override
    public double leastCollisionTime(Ball ball) {
        return GadgetHelpers.leastCollisionTime(sides, cornerCircles, ball);
    }

    @Override
    public synchronized void action() {
        // Only shoot is there a ball to shoot
        if (heldBall != null) {
            heldBall.changeVelocity(SHOOT_VELOCITY);
            heldBall.changePos(new Vect(this.position.x() + width - 0.25,
                    this.position.y() - 0.25));
            heldBall = null;
        }

        checkRep();
    }

    @Override
    public synchronized Message reactBall(Ball ball) {
        Toolkit.getDefaultToolkit().beep();
        if (heldBall != null && !isInside(ball)) {
            GadgetHelpers.reflectBall(sides, cornerCircles, ball);
        } else if (heldBall == null) {
            ball.changePos(new Vect(this.position.x() + width - 0.25,
                    this.position.y() + height - 0.25));
            ball.changeVelocity(new Vect(0, 0));
            heldBall = ball;
        } else {
            heldBall.changePos(new Vect(this.position.x() + width - 0.25,
                    this.position.y() + height - 0.25));
            heldBall.changeVelocity(new Vect(0, 0));
        }

        this.trigger();

        return null;
    }

    @Override
    public double getX() {
        return this.position.x();
    }

    @Override
    public double getY() {
        return this.position.y();
    }

    /**
     * Returns LineSegments representing the absorber.
     * 
     * @return List of line segments representing the absorber
     */
    public List<LineSegment> getLineSegments() {
        return new ArrayList<LineSegment>(this.sides);
    }

    /**
     * Checks if a ball is inside the absorber
     * 
     * @param ball
     *            ball to check if it's in absorber
     * @return true if ball is inside absorber
     */
    public boolean isInside(Ball ball) {
        double x = ball.getPos().x();
        double y = ball.getPos().y();
        return (x < x && x < x + width && y < y && y < y + height);
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

        for (int y = (int) position.y(); y < (int) position.y() + height; y++)
            for (int x = (int) position.x(); x < (int) position.x() + width; x++)
                sb.setCharAt(Board.getBoardStringIndexFromVect(new Vect(x, y)),
                        '=');

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Absorber))
            return false;
        return ((Absorber) obj).getName().equals(this.getName());
    }
}