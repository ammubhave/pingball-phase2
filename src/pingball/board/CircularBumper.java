package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;

public class CircularBumper implements Gadget {

    /**
     * Thread Safety Information: CircularBumper is threadsafe because it is
     * never altered after creation.
     */

    // Following are constants specified by the project
    private final double REFL_COEFF = 1.0;
    private final static double RADIUS = 0.5;
    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = 5; // Used as placeholder value

    private final String name;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private Circle circleGadget;

    /**
     * Creates a circle bumper with the user-inputted parameters. Has a radius
     * of 0.5
     * 
     * @param loc
     *            , vector of the center of the bumper
     * @param n
     *            , name
     */
    public CircularBumper(Vect loc, String n) {
        name = n;
        double centerX = loc.x() + RADIUS;
        double centerY = loc.y() + RADIUS;
        circleGadget = new Circle(centerX, centerY, RADIUS);
    }

    /**
     * Calculates time an inputted ball will take to hit this bumper. Returns a
     * very large value if not nearby (5 seconds).
     * 
     * @param ball
     *            to check if it's nearby
     * @return amount of time to take to trigger object based on inputted ball.
     */
    public void trigger() {
        for (int i = 0; i < gadgetsToBeHooked.size(); i++) {
            gadgetsToBeHooked.get(i).action();
        }
    }

    /**
     * Called when inputted ball is less than 0.001 seconds from impacting
     * gadget (as found out from the trigger function). Handles the resulting
     * physics of when given ball collides with this bumper.
     */
    public void action() {
    }

    public double leastCollisionTime(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        double time = Geometry.timeUntilCircleCollision(circleGadget, ball.getCircle(), velocity);
        if (time < TIME_TO_TRIGGER) {
            return time;
        }
        return NULL;
    }

    /**
     * @return the reflection coefficient
     */
    public double getReflCoeff() {
        return REFL_COEFF;
    }

    /** @return a vector indicating the center of the circle */
    public Vect getPos() {
        return circleGadget.getCenter();
    }

    /**
     * @return a string representation of a circular bumper as seen on the
     *         board.
     */
    @Override
    public String toString() {
        return "0";
    }

    /**
     * @return x coordinate of the center of bumper
     */
    public double getX() {
        return circleGadget.getCenter().x();
    }

    /**
     * @return y coordinate of the center of bumper
     */
    public double getY() {
        return circleGadget.getCenter().y();
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
        return "circular";
    }

    public void reactBall(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        Circle wall = null;
        if (Geometry.timeUntilCircleCollision(circleGadget, ball.getCircle(), velocity) < TIME_TO_TRIGGER) {
            wall = circleGadget;
        }
        double tx = Geometry.timeUntilCircleCollision(wall, ball.getCircle(), velocity);
        ball.move(tx);
        Vect newDir = Geometry.reflectCircle(wall.getCenter(), ball.getPos(), velocity, REFL_COEFF);
        newDir = new Vect(newDir.x(), -newDir.y());
        ball.changeVelocity(newDir);
        ball.move(TIME_TO_TRIGGER - tx);
    }

    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

}
