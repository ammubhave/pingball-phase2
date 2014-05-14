package pingball.board;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import pingball.board.Flipper.FlipperOrientation;
import pingball.board.Flipper.PivotOrientation;
import pingball.proto.Message;

/** Represents the LeftFlipper gadget class */

public class LeftFlipper implements Gadget {
    /*
     * Thread Safety:
     * - All public mutations happen synchronized
     * - The new thread which is spun also does all mutation synchronized on this flipper object
     */

    private FlipperOrientation orientation;
    private final PivotOrientation pivot;
    /**
     * Definition of pivot: 1: topLeft 2: topRight 3: bottomLeft 4: bottomRight
     */
    //private final int pivot;

    private Double flipperAngle = 0.0;

    private final static double REFL_COEFF = 0.95;
    private final static double EDGE_LENGTH = 2;
    private final static double CORNER_DIAMETER = 0.5;
    private final static double CORNER_RADIUS = CORNER_DIAMETER / 2;
    private final static double ANGULAR_SPEED = 18.8495559; // rad/sec

    private final List<LineSegment> sides = new ArrayList<LineSegment>();
    private final List<Circle> cornerCircles = new ArrayList<Circle>();
    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private final String name;
    private final Vect position;
    //private double x;
    //private double y;
    
    /*
     * Rep Invariant:
     * - attributes are non-null
     * - orientation and pivot must be consistent
     */
    private synchronized void checkRep() {
        assert orientation != null;
        assert sides != null;
        assert cornerCircles != null;
        assert gadgetsToBeHooked != null;
        assert name != null;
        
        switch (pivot) {
        case TOP_LEFT: assert orientation == FlipperOrientation.LEFT || orientation == FlipperOrientation.TOP; break;
        case TOP_RIGHT: assert orientation == FlipperOrientation.TOP || orientation == FlipperOrientation.RIGHT; break;
        case BOTTOM_RIGHT: assert orientation == FlipperOrientation.RIGHT || orientation == FlipperOrientation.BOTTOM; break;
        case BOTTOM_LEFT: assert orientation == FlipperOrientation.BOTTOM || orientation == FlipperOrientation.LEFT; break;
        default: assert false;
        }
    }

    public LeftFlipper(Vect position, FlipperOrientation orientation, String name) {
        this.position = position;
        this.name = name;

        this.orientation = orientation;
        
        switch (orientation) {
        case LEFT:
            pivot = PivotOrientation.TOP_LEFT; break;
        case TOP:
            pivot = PivotOrientation.TOP_RIGHT; break;
        case RIGHT:
            pivot = PivotOrientation.BOTTOM_RIGHT; break;
        case BOTTOM:
            pivot = PivotOrientation.BOTTOM_LEFT; break;
        default:
            throw new IllegalArgumentException("orientation is invalid");
        }
        
        flipperAngle = orientationToAngle(orientation);
        remakeComponents();
        
        checkRep();
    }

    public synchronized double orientationToAngle(FlipperOrientation orientation) {
        if ((orientation == FlipperOrientation.LEFT && pivot == PivotOrientation.TOP_LEFT)
                || (orientation == FlipperOrientation.TOP && pivot == PivotOrientation.TOP_RIGHT)
                || (orientation == FlipperOrientation.RIGHT && pivot == PivotOrientation.BOTTOM_RIGHT)
                || (orientation == FlipperOrientation.BOTTOM && pivot == PivotOrientation.BOTTOM_LEFT))
            return 0;
        else
            return 1.57079633;
    }

    private void remakeComponents() {
        sides.clear();
        cornerCircles.clear();

        LineSegment l1, l2;
        Circle c1, c2;
        Angle a = new Angle(-flipperAngle);
        
        double x = this.position.x();
        double y = this.position.y();
        Vect pv = this.getPivotVect();
        
        if (pivot == PivotOrientation.TOP_LEFT) {
            
            l1 = Geometry.rotateAround(new LineSegment(x, y + CORNER_RADIUS, x, y + EDGE_LENGTH
                    - CORNER_RADIUS), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(x + CORNER_DIAMETER, y + CORNER_RADIUS, x
                    + CORNER_DIAMETER, y + EDGE_LENGTH - CORNER_RADIUS), pv, a);
            c1 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + EDGE_LENGTH - CORNER_RADIUS,
                    CORNER_RADIUS), pv, a);
            
        } else if (pivot == PivotOrientation.TOP_RIGHT) {
            
            l1 = Geometry.rotateAround(new LineSegment(x + EDGE_LENGTH - CORNER_RADIUS, y + CORNER_DIAMETER, x
                    + CORNER_RADIUS, y + CORNER_DIAMETER), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(x + EDGE_LENGTH - CORNER_RADIUS, y, x + CORNER_RADIUS,
                    y), pv, a);
            c1 = Geometry.rotateAround(new Circle(x + EDGE_LENGTH - CORNER_RADIUS, y + CORNER_RADIUS,
                    CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS), pv, a);
            
        } else if (pivot == PivotOrientation.BOTTOM_RIGHT) {
            
            l1 = Geometry.rotateAround(new LineSegment(x + EDGE_LENGTH - CORNER_DIAMETER, y + EDGE_LENGTH
                    - CORNER_RADIUS, x + EDGE_LENGTH - CORNER_DIAMETER, y + CORNER_RADIUS), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(x + EDGE_LENGTH, y + EDGE_LENGTH - CORNER_RADIUS, x
                    + EDGE_LENGTH, y + CORNER_RADIUS), pv, a);
            c1 = Geometry.rotateAround(new Circle(x + EDGE_LENGTH - CORNER_RADIUS, y + CORNER_RADIUS,
                    CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(x + EDGE_LENGTH - CORNER_RADIUS, y + EDGE_LENGTH
                    - CORNER_RADIUS, CORNER_RADIUS), pv, a);
            
        } else { // BOTTOM_LEFT
            
            l1 = Geometry.rotateAround(new LineSegment(x + CORNER_RADIUS, y + EDGE_LENGTH - CORNER_RADIUS, x
                    + EDGE_LENGTH - CORNER_RADIUS, y + EDGE_LENGTH - CORNER_RADIUS), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(x + CORNER_RADIUS, y + EDGE_LENGTH, x + EDGE_LENGTH
                    - CORNER_RADIUS, y + EDGE_LENGTH), pv, a);
            c1 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + EDGE_LENGTH - CORNER_RADIUS,
                    CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(x + CORNER_RADIUS, y + CORNER_RADIUS, CORNER_RADIUS), pv, a);
        }

        sides.add(l1);
        sides.add(l2);
        cornerCircles.add(c1);
        cornerCircles.add(c2);
        
        checkRep();
    }

    @Override
    public void trigger() {
        GadgetHelpers.callActionOnGadgets(gadgetsToBeHooked);
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }
    
    /**
     * Gets the pivot vector
     * @return the pivot vector
     */
    private synchronized Vect getPivotVect() {
        if (pivot == PivotOrientation.TOP_LEFT)
            return new Vect(this.position.x() + CORNER_RADIUS, this.position.y() + CORNER_RADIUS);
        else if (pivot == PivotOrientation.TOP_RIGHT)
            return new Vect(this.position.x() + EDGE_LENGTH - CORNER_RADIUS, this.position.y() + CORNER_RADIUS);
        else if (pivot == PivotOrientation.BOTTOM_LEFT)
            return new Vect(this.position.x() + CORNER_RADIUS, this.position.y() + EDGE_LENGTH - CORNER_RADIUS);
        else // BOTTOM_RIGHT
            return new Vect(this.position.x() + EDGE_LENGTH - CORNER_RADIUS, this.position.y() + EDGE_LENGTH - CORNER_RADIUS);
    }

    /**
     * Gets the rotational velocity of the flipper
     * @return the rotational velocity of the flipper
     */
    private synchronized double getVelocity() {
        double targetAngle = orientationToAngle(orientation);

        if (flipperAngle > targetAngle && flipperAngle - targetAngle > 0.006)
            return -ANGULAR_SPEED;
        if (flipperAngle < targetAngle && targetAngle - flipperAngle > 0.006)
            return ANGULAR_SPEED;
        return 0;
    }

    @Override
    public synchronized List<Message> reactBall(Ball ball) {
        Toolkit.getDefaultToolkit().beep();
        List<LineSegment> lines = sides;
        List<Circle> circles = cornerCircles;
        if (lines == null)
            lines = new ArrayList<LineSegment>();
        if (circles == null)
            circles = new ArrayList<Circle>();

        double smallestTimeWall = Double.POSITIVE_INFINITY;
        LineSegment smallestWall = null;
        double timeToWall = 0;
        for (LineSegment ls : lines) {
            timeToWall = Geometry.timeUntilWallCollision(ls, ball.getCircle(), ball.getVelocity());
            if (timeToWall <= smallestTimeWall) {
                smallestTimeWall = timeToWall;
                smallestWall = ls;
            }
        }
        Circle smallestCircle = null;
        double smallestTimeCircle = Double.POSITIVE_INFINITY;
        double timeToCircle = 0;
        for (Circle circ : circles) {
            timeToCircle = Geometry.timeUntilCircleCollision(circ, ball.getCircle(), ball.getVelocity());
            if (timeToCircle <= smallestTimeCircle) {
                smallestTimeCircle = timeToCircle;
                smallestCircle = circ;
            }
        }
        if (smallestTimeWall < smallestTimeCircle) {
            ball.changeVelocity(Geometry.reflectRotatingWall(smallestWall, getPivotVect(), getVelocity(),
                    ball.getCircle(), ball.getVelocity(), REFL_COEFF));
        } else {
            ball.changeVelocity(Geometry.reflectRotatingCircle(smallestCircle, getPivotVect(), getVelocity(),
                    ball.getCircle(), ball.getVelocity(), REFL_COEFF));
        }
        this.trigger();

        return new ArrayList<Message>();
    }

    @Override
    public synchronized double leastCollisionTime(Ball ball) {
        List<LineSegment> lines = sides;
        List<Circle> circles = cornerCircles;
        if (lines == null) lines = new ArrayList<LineSegment>();
        if (circles == null) circles = new ArrayList<Circle>();
        
        double smallestTime = Double.POSITIVE_INFINITY;
        for (LineSegment ls : lines) {
            double time = Geometry.timeUntilRotatingWallCollision(ls, getPivotVect(), getVelocity(), ball.getCircle(), ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
            }
        }
        for (Circle circ : circles) {
            double time = Geometry.timeUntilRotatingCircleCollision(circ, getPivotVect(), getVelocity(), ball.getCircle(), ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
            }
        }
        return Math.max(smallestTime, 0);
    }
    
    private ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
    class FlipperRotator extends TimerTask {
        private LeftFlipper flipper;
        private int direction; // 1 -> positive, -1 -> negative
        
        /*
         * Rep Invariant:
         * - flipper should be non-null
         * - direction should be either 1 or -1
         */
        private void checkRep() {
            assert flipper != null;
            assert direction == 1 || direction == -1;
        }

        public FlipperRotator(LeftFlipper flipper) {
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
                double dt = 0.05 / 200.0;
                if (direction == -1) {
                    if (flipperAngle <= targetAngle) {
                        exec.shutdownNow();
                        return;
                    }
                    flipperAngle -= ANGULAR_SPEED * dt;
                    remakeComponents();
                } else {
                    if (flipperAngle >= targetAngle) {
                        exec.shutdownNow();
                        return;
                    }
                    flipperAngle += ANGULAR_SPEED * dt;
                    remakeComponents();
                }
            }
        }
    }

    public synchronized List<LineSegment> getLineSegments() {
        return new ArrayList<LineSegment>(this.sides);
    }

    public synchronized List<Circle> getCircles() {
        return new ArrayList<Circle>(this.cornerCircles);
    }

    @Override
    public synchronized void action() {
        moveFlipper();
        
        // Stop any running rotations
        if (exec.getActiveCount() >= 0) {
            exec.shutdownNow();
        }

        exec = new ScheduledThreadPoolExecutor(1);
        double BOARD_REFRESH_INTERVAL = 0.05 / 200.0;
        exec.scheduleAtFixedRate(new FlipperRotator(this), 0, (long) (BOARD_REFRESH_INTERVAL * 1000 * 1000),
                TimeUnit.MICROSECONDS);
    }

    public synchronized void moveFlipper() {
        if (orientation == FlipperOrientation.TOP) {
            if (pivot == PivotOrientation.TOP_LEFT) {
                orientation = FlipperOrientation.LEFT;
            } else { // TOP_RIGHT
                orientation = FlipperOrientation.RIGHT;
            }
        } else if (orientation == FlipperOrientation.BOTTOM) {
            if (pivot == PivotOrientation.BOTTOM_LEFT) {
                orientation = FlipperOrientation.LEFT;
            } else { // BOTTOM_RIGHT
                orientation = FlipperOrientation.RIGHT;
            }
        } else if (orientation == FlipperOrientation.LEFT) {
            if (pivot == PivotOrientation.TOP_LEFT) {
                orientation = FlipperOrientation.TOP;
            } else { // BOTTOM_LEFT
                orientation = FlipperOrientation.BOTTOM;
            }
        } else { // RIGHT
            if (pivot == PivotOrientation.TOP_RIGHT) {
                orientation = FlipperOrientation.TOP;
            } else { // BOTTOM_RIGHT
                orientation = FlipperOrientation.BOTTOM;
            }
        }
        
        checkRep();
    }

    /** Returns the name of the LeftFlipper gadget */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public synchronized String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        Vect position = new Vect(this.position.x(), this.position.y());
        if (this.orientation == FlipperOrientation.TOP) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position), '-');
            sb.setCharAt(Board.getBoardStringIndexFromVect(position) + 1, '-');
        } else if (this.orientation == FlipperOrientation.RIGHT) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(1, 0))), '|');
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(1, 1))), '|');
        } else if (this.orientation == FlipperOrientation.LEFT) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(0, 0))), '|');
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(0, 1))), '|');
        } else if (this.orientation == FlipperOrientation.BOTTOM) {
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(0, 1))), '-');
            sb.setCharAt(Board.getBoardStringIndexFromVect(position.plus(new Vect(1, 1))), '-');
        }
        return sb.toString();
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
    public boolean equals(Object obj) {
        if (!(obj instanceof LeftFlipper)) return false;
        return ((LeftFlipper)obj).getName().equals(this.getName());
    }
}