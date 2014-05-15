package pingball.board;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import pingball.client.ClientController;

/** This class represents a right flipper gadget */

public class RightFlipper extends Flipper {

    /**
     * Thread Safety: - All public mutations happen synchronized - The new
     * thread which is spun also does all mutation synchronized on this flipper
     * object gadget hooking is done only in factory
     */

    public RightFlipper(Vect position, FlipperOrientation orientation, String name) {
        super(position, orientation, name);

        switch (orientation) {
        case RIGHT:
            pivot = PivotOrientation.TOP_RIGHT;
            break;
        case BOTTOM:
            pivot = PivotOrientation.BOTTOM_RIGHT;
            break;
        case LEFT:
            pivot = PivotOrientation.BOTTOM_LEFT;
            break;
        case TOP:
            pivot = PivotOrientation.TOP_LEFT;
            break;
        default:
            throw new IllegalArgumentException("orientation is invalid");
        }

        flipperAngle = orientationToAngle(orientation);
        remakeComponents();

        checkRep();
    }

    /**
     * Returns the angle of the flipper with respect to its orientation
     * 
     * @param orientation
     *            of Flipper
     * @return double angle
     */
    public synchronized double orientationToAngle(FlipperOrientation orientation) {
        if ((orientation == FlipperOrientation.RIGHT && pivot == PivotOrientation.TOP_RIGHT)
                || (orientation == FlipperOrientation.BOTTOM && pivot == PivotOrientation.BOTTOM_RIGHT)
                || (orientation == FlipperOrientation.LEFT && pivot == PivotOrientation.BOTTOM_LEFT)
                || (orientation == FlipperOrientation.TOP && pivot == PivotOrientation.TOP_LEFT))
            return 0;
        else
            return 1.57079633; // 90 degrees
    }

    /**
     * Changes the orientation of the flippers appropriately when a ball hits
     * them
     */
    public synchronized void remakeComponents() {
        sides.clear();
        cornerCircles.clear();

        LineSegment l1, l2;
        Circle c1, c2;
        Angle a = new Angle(flipperAngle);
        double x = this.position.x();
        double y = this.position.y();
        Vect pv = this.getPivotVect();

        if (pivot == PivotOrientation.TOP_LEFT) {

            l1 = Geometry
                    .rotateAround(new LineSegment(x + CORNER_RADIUS, y, x + EDGE_LENGTH - CORNER_RADIUS, y), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(x + CORNER_RADIUS, y + CORNER_DIAMETER, x + EDGE_LENGTH
                    - CORNER_RADIUS, y + CORNER_DIAMETER), pv, a);
            c1 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(x + EDGE_LENGTH - CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS),
                    pv, a);

        } else if (pivot == PivotOrientation.TOP_RIGHT) {

            l1 = Geometry.rotateAround(new LineSegment(x + EDGE_LENGTH - CORNER_DIAMETER, y + EDGE_LENGTH
                    - CORNER_RADIUS, x + EDGE_LENGTH - CORNER_DIAMETER, y + CORNER_RADIUS), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(x + EDGE_LENGTH, y + EDGE_LENGTH - CORNER_RADIUS, x
                    + EDGE_LENGTH, y + CORNER_RADIUS), pv, a);
            c1 = Geometry.rotateAround(new Circle(x + EDGE_LENGTH - CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS),
                    pv, a);
            c2 = Geometry.rotateAround(new Circle(x + EDGE_LENGTH - CORNER_RADIUS, y + EDGE_LENGTH - CORNER_RADIUS,
                    CORNER_RADIUS), pv, a);

        } else if (pivot == PivotOrientation.BOTTOM_LEFT) {

            l1 = Geometry
                    .rotateAround(new LineSegment(x, y + CORNER_RADIUS, x, y + EDGE_LENGTH - CORNER_RADIUS), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(x + CORNER_DIAMETER, y + CORNER_RADIUS, x + CORNER_DIAMETER, y
                    + EDGE_LENGTH - CORNER_RADIUS), pv, a);
            c1 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + EDGE_LENGTH - CORNER_RADIUS, CORNER_RADIUS),
                    pv, a);
            c2 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS), pv, a);

        } else { // BOTTOM_RIGHT

            l1 = Geometry.rotateAround(new LineSegment(x + CORNER_RADIUS, y + EDGE_LENGTH - CORNER_DIAMETER, x
                    + EDGE_LENGTH - CORNER_RADIUS, y + EDGE_LENGTH - CORNER_DIAMETER), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(x + CORNER_RADIUS, y + EDGE_LENGTH, x + EDGE_LENGTH
                    - CORNER_RADIUS, y + EDGE_LENGTH), pv, a);
            c1 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + EDGE_LENGTH - CORNER_RADIUS, CORNER_RADIUS),
                    pv, a);
            c2 = Geometry.rotateAround(new Circle(x + EDGE_LENGTH - CORNER_RADIUS, y + EDGE_LENGTH - CORNER_RADIUS,
                    CORNER_RADIUS), pv, a);
        }

        sides.add(l1);
        sides.add(l2);
        cornerCircles.add(c1);
        cornerCircles.add(c2);
    }

    /**
     * Returns the velocity of the flipper
     * 
     * @return double velocity
     */
    protected synchronized double getVelocity() {
        double targetAngle = orientationToAngle(orientation);

        if (flipperAngle > targetAngle && flipperAngle - targetAngle > 0.006)
            return -Flipper.ANGULAR_SPEED;
        if (flipperAngle < targetAngle && targetAngle - flipperAngle > 0.006)
            return Flipper.ANGULAR_SPEED;
        return 0;
    }

    private ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

    class FlipperRotator extends TimerTask {
        RightFlipper flipper;
        int direction; // 1 -> positive, -1 -> negative

        /*
         * Rep Invariant: - flipper should be non-null - direction should be
         * either 1 or -1
         */
        private void checkRep() {
            assert flipper != null;
            assert direction == 1 || direction == -1;
        }

        public FlipperRotator(RightFlipper flipper) {
            this.flipper = flipper;
            double targetAngle = orientationToAngle(orientation);
            if (flipperAngle > targetAngle) {
                direction = -1;
            } else {
                direction = 1;
            }

            checkRep();
        }

        @Override
        public void run() {
            synchronized (flipper) {
                double targetAngle = orientationToAngle(orientation);
                if (direction == -1) {
                    if (flipperAngle <= targetAngle) {
                        exec.shutdownNow();
                        return;
                    }
                    flipperAngle -= 18.8495559 * ClientController.DT;
                    remakeComponents();
                } else {
                    if (flipperAngle >= targetAngle) {
                        exec.shutdownNow();
                        return;
                    }
                    flipperAngle += 18.8495559 * ClientController.DT;
                    remakeComponents();
                }
            }
        }
    }

    @Override
    public synchronized void action() {
        moveFlipper();

        if (exec.getActiveCount() >= 0) {
            exec.shutdownNow();
        }
        exec = new ScheduledThreadPoolExecutor(1);
        double BOARD_REFRESH_INTERVAL = 0.05 / 200.0;
        exec.scheduleAtFixedRate(new FlipperRotator(this), 0, (long) (BOARD_REFRESH_INTERVAL * 1000 * 1000),
                TimeUnit.MICROSECONDS);

    }

    /**
     * Returns a string representing the type of gadget.
     */
    public String type() {
        return "flipper";
    }
}
