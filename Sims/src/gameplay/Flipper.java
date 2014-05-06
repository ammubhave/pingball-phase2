package gameplay;

import java.util.ArrayList;
import java.util.HashMap;

import physics.Angle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import utilities.Coords;

public class Flipper implements Gadget {
    private int direction;

    private enum FlipperType {
        LEFT, RIGHT
    }

    private Vect pos;
    private float reflectionCoefficient = (float) 0.95;
    private LineSegment flipper;
    private float size = 1.0f;
    private int angle;
    private FlipperType type;
    private ArrayList<Gadget> triggers;

    /**
     * Creates a new instance of Flipper.
     * 
     * @param position
     *            the coordinates of the pivot point of the flipper that we're
     *            about to create.
     * @param type
     *            either left or right. Represents which way the flipper
     *            rotates.
     */
    public Flipper(Coords position, String type, int orientation) {
        this.type = type.equals("LEFT") ? FlipperType.LEFT : FlipperType.RIGHT;
        if (type.equals("LEFT")) {
            direction = 1; // +1 if counterclockwise
        } else {
            direction = -1; // -1 if clockwise
        }
        angle = (orientation + 180 ) % 180;
        pos = new Vect(position.getX(), position.getY());
        Vect pos2 = new Vect(pos.x() + size * Math.sin(angle), pos.y() - Math.cos(angle));
        flipper =  new LineSegment(pos, pos2);
        triggers = new ArrayList<Gadget>();
    }

    @Override
    public synchronized Ball bounce(Ball b, String identifier) {
        Vect newVel = Geometry.reflectRotatingWall(flipper, pos, 6 * Math.PI
                * direction, b.getCircleObject(), b.getVelocity(),
                reflectionCoefficient);
        direction = -1 * direction;
        return new Ball(b.getCenter(), newVel);
    }

    /**
     * Rotates the flipper by pi/2 radians.
     */
    public synchronized void rotate() {
        Geometry.rotateAround(flipper, pos, new Angle(90*direction));
        direction = -1*direction;
    }

    @Override
    public float getReflectionCoefficient() {
        return this.reflectionCoefficient;
    }

    @Override
    public synchronized void action() {
        rotate();
    }

    @Override
    public synchronized Coords getPosition() {
        Coords posit = new Coords(pos.x(), pos.y());
        return posit;
    }

    @Override
    public String toString() {
        if ((type.equals("LEFT") && direction == -1 && angle % 180 == 0)
                || (type.equals("RIGHT") && direction == 1) && angle % 180 == 90) {
            return "-";
        }
        return "|";
    }

    @Override
    public synchronized void addTrigger(Gadget g) {
        triggers.add(g);
    }

    @Override
    public void createGeoObjects() {
        throw new UnsupportedOperationException(
                "We don't create geo objects for flippers.");
    }

    @Override
    public String getType() {
        return "FLIPPER";
    }

    @Override
    public synchronized HashMap<String, Double> leastCollisionTime(Ball b, Vect v) {
        HashMap<String, Double> allTimes = new HashMap<String, Double>();
        String identifier = "";
        double minimum = Geometry.timeUntilRotatingWallCollision(this.flipper,
                this.pos, 6 * Math.PI, b.getCircleObject(), v);

        allTimes.put(identifier, minimum);
        return allTimes;
    }
}
