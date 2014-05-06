package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/** This class represents an Absorber Gadget */

public class Absorber implements Gadget {
    /**
     * Thread Safety Information: Absorber is threadsafe because it is only
     * modifiable by one ball at a time due to the setup of the client handler.
     * 
     */
    private final int width; // measured horizontally
    private final int height; // measured vertically
    private final double xLocation; // Starting x-coordinate
    private final double yLocation; // Starting y-coordinate

    private final Geometry.DoublePair position;
    private final Geometry.DoublePair bottomRight;
    private final Geometry.DoublePair topRight;
    private boolean isSelfTriggering;
    private List<Ball> heldBalls = new ArrayList<Ball>();

    private final static Vect SHOOT_VELOCITY = new Vect(0, 50);
    private final static double REFL_COEFF = 1;
    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = 5; // Used as placeholder value

    private final String name;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private LineSegment top;
    private LineSegment bottom;
    private LineSegment left;
    private LineSegment right;
    private final List<LineSegment> sides = new ArrayList<LineSegment>();

    /**
     * Creates an absorber gadget with the following user-inputted parameters.
     * 
     * @param loc
     *            , vector representing the starting location
     * @param width
     *            , horizontal distance of absorber
     * @param height
     *            , vertical distance of absorber
     * @param isSelfTriggerable
     *            , whether absorber shoots out a ball when it's hit by one
     * @param n
     *            , name of absorber
     */
    public Absorber(Vect loc, int width, int height, boolean isSelfTriggerable, String n) {
        name = n;
        this.width = width;
        this.height = height;

        xLocation = loc.x();
        yLocation = loc.y();

        this.position = new Geometry.DoublePair(xLocation, yLocation);
        this.topRight = new Geometry.DoublePair(xLocation + width - 1, yLocation);
        this.bottomRight = new Geometry.DoublePair(xLocation, yLocation + height - 1);

        top = new LineSegment(xLocation, yLocation, xLocation + width, yLocation);
        bottom = new LineSegment(xLocation, yLocation + height, xLocation + width, yLocation + height);
        left = new LineSegment(xLocation, yLocation, xLocation, yLocation + height);
        right = new LineSegment(xLocation + width, yLocation, xLocation + width, yLocation + height);

        sides.add(top);
        sides.add(bottom);
        sides.add(left);
        sides.add(right);

        isSelfTriggering = isSelfTriggerable;
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

    public double leastCollisionTime(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        for (LineSegment ls : sides) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), velocity);
            if (!isInside(ball)) {
                if (time < TIME_TO_TRIGGER) {
                    return time;
                }
            }

        }
        return NULL;
    }

    /**
     * Called when inputted ball is less than 0.001 seconds from impacting
     * gadget (as found out from the trigger function). Handles the resulting
     * physics of when given ball collides with this bumper.
     */
    @Override
    public void action() {
        if (!heldBalls.isEmpty()) {
            Ball shootBall = heldBalls.remove(0);
            shootBall.changeVelocity(SHOOT_VELOCITY);
        }
    }

    public void reactBall(Ball ball) {
        ball.changePos(new Vect(xLocation + width - 0.25, yLocation + height - 0.25));
        // (should be -0.25, but then absorber is stopping its own balls)
        heldBalls.add(ball);
        if (isSelfTriggering) {
            Ball shootBall = heldBalls.remove(0);
            shootBall.changeVelocity(SHOOT_VELOCITY);
        }
    }

    /**
     * @return the reflection coefficient.
     */
    @Override
    public double getReflCoeff() {
        return REFL_COEFF;
    }

    /**
     * @return absorber as seen on a board.
     */
    @Override
    public String toString() {
        return "=";
    }

    /** @return: upper left x coordinate of absorber */
    public double getX() {
        return xLocation;
    }

    /** @return: upper left y coordinate of absorber */
    public double getY() {
        return yLocation;
    }

    /** @return: width of absorber */
    public int getWidth() {
        return width;
    }

    /** @return: height of absorber */

    public int getHeight() {
        return height;
    }

    /**
     * @param ball
     *            , ball to check if it's in absorber
     * @return: true if ball is inside absorber
     */
    public boolean isInside(Ball ball) {
        double x = ball.getPos().x();
        double y = ball.getPos().y();
        return (xLocation < x && x < xLocation + width && yLocation < y && y < yLocation + height);
    }

    /** @return: name of absorber */
    public String getName() {
        return name;
    }

    /** @param: boolean to set triggerable */
    public void setTriggerable(boolean toSet) {
        isSelfTriggering = toSet;
    }

    /** @return: true if triggerable */
    public boolean isTriggerable() {
        return isSelfTriggering;
    }

    /**
     * @return string representing the type of gadget.
     */
    public String type() {
        return "absorber";
    }

    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

}
