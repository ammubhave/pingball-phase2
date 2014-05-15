package pingball.board;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import pingball.proto.Message;

public class CircularBumper implements Gadget {

    /**
     * Thread Safety Information: CircularBumper is threadsafe because it is
     * never altered after creation. (immutable)
     * - gadgetsToBeHooked is only modified in factory.
     */

    private final double REFL_COEFF = 1.0;
    private final static double RADIUS = 0.5;

    private final String name;
    private final Vect position;
    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();
    private final Circle circleGadget;

    /**
     * Creates a circle bumper with the user-inputted parameters. Has a radius 0.5 L
     * @param loc vector of the center of the bumper
     * @param n name
     */
    public CircularBumper(Vect position, String name) {
        this.name = name;
        this.position = position;
        double centerX = position.x() + RADIUS;
        double centerY = position.y() + RADIUS;
        circleGadget = new Circle(centerX, centerY, RADIUS);
    }

    @Override
    public void trigger() {
        GadgetHelpers.callActionOnGadgets(gadgetsToBeHooked);
    }

    @Override
    public void action() {
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
        Vect velocity = ball.getVelocity();
        return Geometry.timeUntilCircleCollision(circleGadget, ball.getCircle(), velocity);
    }

    @Override
    public double getX() {
        return circleGadget.getCenter().x() - RADIUS;
    }

    @Override
    public double getY() {
        return circleGadget.getCenter().y() - RADIUS;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Message reactBall(Ball ball) {

        GadgetHelpers.playBounceSound();
        ball.changeVelocity(Geometry.reflectCircle(circleGadget.getCenter(), ball.getPos(), ball.getVelocity(),
                REFL_COEFF));

        this.trigger();

        return null;
    }

    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

    @Override
    public synchronized String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(Board.getBoardStringIndexFromVect(this.position), 'O');
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CircularBumper)) return false;
        return ((CircularBumper)obj).getName().equals(this.getName());
    }
}
