package pingball.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.TimerTask;

import org.antlr.v4.parse.ANTLRParser.throwsSpec_return;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import pingball.board.Flipper.FlipperOrientation;

/** Represents the LeftFlipper gadget class */

public class LeftFlipper implements Gadget {

    /**
     * Definition of orientation: 1: top 2: bottom 3: left 4: right
     */
    private FlipperOrientation orientation;
    /**
     * Definition of pivot: 1: topLeft 2: topRight 3: bottomLeft 4: bottomRight
     */
    private final int pivot;

    private Double flipperAngle = 0.0;
    
    private final static double REFL_COEFF = 0.95;
    private final static double EDGE_LENGTH = 2;
    private final static double CORNER_DIAMETER = 0.5;
    private final static double CORNER_RADIUS = CORNER_DIAMETER/2;

    private final List<LineSegment> sides = new ArrayList<LineSegment>();
    private final List<Circle> cornerCircles = new ArrayList<Circle>();

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();


    private final String name;
    private double xLoc;
    private double yLoc;

    public LeftFlipper(Vect loc, FlipperOrientation orient, String n) {
        xLoc = loc.x();
        yLoc = loc.y();

        name = n;

        this.orientation = orient;

        if (orientation == FlipperOrientation.TOP) {
            pivot = 2;
        } else if (orientation == FlipperOrientation.BOTTOM) {
            pivot = 3;
        } else if (orientation == FlipperOrientation.LEFT) {
            pivot = 1;
        } else { // Right
            pivot = 4;
        }
        flipperAngle = orientationToAngle(orientation);
        remakeComponents();
    }
    
    public synchronized double orientationToAngle(FlipperOrientation orientation) {
        if ((orientation == FlipperOrientation.TOP && pivot == 2) ||
            (orientation == FlipperOrientation.RIGHT && pivot == 4) ||
            (orientation == FlipperOrientation.BOTTOM && pivot == 3) ||
            (orientation == FlipperOrientation.LEFT && pivot == 1))
            return 0;
        else 
            return 1.57079633;
    }
    
    public synchronized void remakeComponents() {
        sides.clear();
        cornerCircles.clear();
        
        LineSegment l1, l2;
        Circle c1, c2;
        Angle a = new Angle(-flipperAngle);
        
        if (pivot == 1) {
            Vect pv = new Vect(xLoc + CORNER_RADIUS, yLoc + CORNER_RADIUS);
           /* l1 = new LineSegment(xLoc, yLoc + CORNER_RADIUS, xLoc, yLoc + EDGE_LENGTH - CORNER_RADIUS);
            l2 = new LineSegment(xLoc + CORNER_DIAMETER, yLoc + CORNER_RADIUS, xLoc + CORNER_DIAMETER, yLoc + EDGE_LENGTH - CORNER_RADIUS);
            c1 = new Circle(xLoc + CORNER_RADIUS, yLoc + CORNER_RADIUS, CORNER_RADIUS);
            c2 = new Circle(xLoc + CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS, CORNER_RADIUS);
           */
            l1 = Geometry.rotateAround(new LineSegment(xLoc, yLoc + CORNER_RADIUS, xLoc, yLoc + EDGE_LENGTH - CORNER_RADIUS), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(xLoc + CORNER_DIAMETER, yLoc + CORNER_RADIUS, xLoc + CORNER_DIAMETER, yLoc + EDGE_LENGTH - CORNER_RADIUS), pv, a);
            c1 = Geometry.rotateAround(new Circle(xLoc + CORNER_RADIUS, yLoc + CORNER_RADIUS, CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(xLoc + CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS, CORNER_RADIUS), pv, a);
        } else if (pivot == 2) {
            Vect pv = new Vect(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + CORNER_RADIUS);
            l1 = Geometry.rotateAround(new LineSegment(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + CORNER_DIAMETER, xLoc + CORNER_RADIUS, yLoc + CORNER_DIAMETER), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc, xLoc + CORNER_RADIUS, yLoc), pv, a);
            c1 = Geometry.rotateAround(new Circle(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + CORNER_RADIUS, CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(xLoc + CORNER_RADIUS, yLoc + CORNER_RADIUS, CORNER_RADIUS), pv, a);
        } else if (pivot == 4) {
            Vect pv = new Vect(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS);
            l1 = Geometry.rotateAround(new LineSegment(xLoc + EDGE_LENGTH - CORNER_DIAMETER, yLoc + EDGE_LENGTH - CORNER_RADIUS, xLoc + EDGE_LENGTH - CORNER_DIAMETER, yLoc + CORNER_RADIUS), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(xLoc + EDGE_LENGTH,  yLoc + EDGE_LENGTH - CORNER_RADIUS, xLoc + EDGE_LENGTH, yLoc + CORNER_RADIUS), pv, a);
            c1 = Geometry.rotateAround(new Circle(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + CORNER_RADIUS, CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS, CORNER_RADIUS), pv, a);
        } else {
            Vect pv = new Vect(xLoc + CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS);            
            l1 = Geometry.rotateAround(new LineSegment(xLoc + CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS, xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS), pv, a);
            l2 = Geometry.rotateAround(new LineSegment(xLoc + CORNER_RADIUS, yLoc + EDGE_LENGTH, xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + EDGE_LENGTH), pv, a);
            c1 = Geometry.rotateAround(new Circle(xLoc + CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS, CORNER_RADIUS), pv, a);
            c2 = Geometry.rotateAround(new Circle(xLoc + CORNER_RADIUS, yLoc + CORNER_RADIUS, CORNER_RADIUS), pv, a);
        }
        
        sides.add(l1);
        sides.add(l2);
        cornerCircles.add(c1);
        cornerCircles.add(c2);
    }

    @Override
    public void trigger() {
        for (int i = 0; i < gadgetsToBeHooked.size(); i++) {
            gadgetsToBeHooked.get(i).action();
        }
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }
    
    private Vect getPivotVect() {
        if (pivot == 1)
            return new Vect(xLoc + CORNER_RADIUS, yLoc + CORNER_RADIUS);
        else if (pivot == 2)
            return new Vect(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + CORNER_RADIUS);
        else if (pivot == 3)
            return new Vect(xLoc + CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS);
        else
            return new Vect(xLoc + EDGE_LENGTH - CORNER_RADIUS, yLoc + EDGE_LENGTH - CORNER_RADIUS);
    }
    
    private synchronized double getVelocity() {
        double targetAngle = orientationToAngle(orientation);

        if (flipperAngle > targetAngle && flipperAngle - targetAngle > 0.006) return -18.8495559;
        if(flipperAngle < targetAngle && targetAngle - flipperAngle > 0.006) return 18.8495559;
        return 0;
    }

    public synchronized void reactBall(Ball ball) {
        List<LineSegment> lines = sides;
        List<Circle> circles = cornerCircles;
        if (lines == null) lines = new ArrayList<LineSegment>();
        if (circles == null) circles = new ArrayList<Circle>();
        
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
            ball.changeVelocity(Geometry.reflectRotatingWall(smallestWall, getPivotVect(), getVelocity() , ball.getCircle(), ball.getVelocity(), REFL_COEFF));
        } else {
            ball.changeVelocity(Geometry.reflectRotatingCircle(smallestCircle, getPivotVect(), getVelocity() , ball.getCircle(), ball.getVelocity(), REFL_COEFF));
        }
        this.trigger();
    }

    @Override
    public synchronized double leastCollisionTime(Ball ball) {
        return GadgetHelpers.leastCollisionTime(sides, cornerCircles, ball);
    }
    
    /*
    /**
     * Changes the orientation of the flipper
     * 
     * @param orientation
     *            of the flipper before
     
    private void changeFlipperOrientation(FlipperOrientation orientation) {
        if (orientation == FlipperOrientation.TOP) {
            oneLineFlipper = new LineSegment(xLoc, yLoc, xLoc + 2, yLoc);
            topLine = new LineSegment(xLoc, yLoc, xLoc + 2, yLoc);
            leftLine = new LineSegment(xLoc, yLoc, xLoc, yLoc + 0.5);
            rightLine = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 0.5);
            bottomLine = new LineSegment(xLoc, yLoc + 0.5, xLoc + 2, yLoc + 0.5);
        } else if (orientation == FlipperOrientation.BOTTOM) {
            oneLineFlipper = new LineSegment(xLoc, yLoc + 2, xLoc + 2, yLoc + 2);
            topLine = new LineSegment(xLoc, yLoc + 1.5, xLoc + 2, yLoc + 1.5);
            leftLine = new LineSegment(xLoc, yLoc + 1.5, xLoc, yLoc + 2);
            rightLine = new LineSegment(xLoc + 2, yLoc + 1.5, xLoc + 2, yLoc + 2);
            bottomLine = new LineSegment(xLoc, yLoc + 2, xLoc + 2, yLoc + 2);
        } else if (orientation == FlipperOrientation.LEFT) {
            oneLineFlipper = new LineSegment(xLoc, yLoc, xLoc, yLoc + 2);
            topLine = new LineSegment(xLoc, yLoc, xLoc + 0.5, yLoc);
            leftLine = new LineSegment(xLoc, yLoc, xLoc, yLoc + 2);
            rightLine = new LineSegment(xLoc + 0.5, yLoc, xLoc + 0.5, yLoc + 2);
            bottomLine = new LineSegment(xLoc, yLoc + 2, xLoc + 0.5, yLoc + 2);
        } else { // RIGHT
            oneLineFlipper = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 2);
            topLine = new LineSegment(xLoc + 1.5, yLoc, xLoc + 2, yLoc);
            leftLine = new LineSegment(xLoc + 1.5, yLoc, xLoc + 1.5, yLoc + 2);
            rightLine = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 2);
            bottomLine = new LineSegment(xLoc + 1.5, yLoc + 2, xLoc + 2, yLoc + 2);
        }
    }*/

    private ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
    class FlipperRotator extends TimerTask {
        LeftFlipper flipper;
        int direction; // 1 -> positive, -1 -> negative
        
        public FlipperRotator(LeftFlipper flipper) {
            this.flipper = flipper;
            double targetAngle = orientationToAngle(orientation);
            if (flipperAngle > targetAngle) {
                direction = -1;
            } else {
                direction = 1;
            }
        }
        
        @Override
        public void run() { 
            synchronized (flipper) {        
                double targetAngle = orientationToAngle(orientation);
                double dt = 0.05/200.0;
                if (direction == -1) {
                    if (flipperAngle <= targetAngle) {
                        exec.shutdownNow();
                        return;
                    }
                    flipperAngle -= 18.8495559 * dt;
                    remakeComponents();
                } else {
                    if (flipperAngle >= targetAngle) {
                        exec.shutdownNow();
                        return;
                    }
                    flipperAngle += 18.8495559 * dt;
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
    public void action() {
        moveFlipper();
        /*if (rotatorThread != null && rotatorThread.isAlive()) {
            rotatorThread.interrupt();
            try {
                rotatorThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        if (exec.getActiveCount() >= 0) {
            exec.shutdownNow();
        }
        exec = new ScheduledThreadPoolExecutor(1);
        double BOARD_REFRESH_INTERVAL = 0.05/200.0;
        exec.scheduleAtFixedRate(new FlipperRotator(this), 0, (long)(BOARD_REFRESH_INTERVAL*1000*1000), TimeUnit.MICROSECONDS);
        
     //   exec.scheduleAtFixedRate(new BoardPrinterTask(), 0, (long)(BOARD_REFRESH_INTERVAL*1000*1000), TimeUnit.MICROSECONDS);
        
      //  rotatorThread = new Thread(new FlipperRotator());
      //  rotatorThread.start();
        
    }

    public synchronized void moveFlipper() {
        if (orientation == FlipperOrientation.TOP) {
            if (pivot == 1) {
                orientation = FlipperOrientation.LEFT;
            } else {
                orientation = FlipperOrientation.RIGHT;
            }
        } else if (orientation == FlipperOrientation.BOTTOM) {
            if (pivot == 3) {
                orientation = FlipperOrientation.LEFT;
            } else {
                orientation = FlipperOrientation.RIGHT;
            }
        } else if (orientation == FlipperOrientation.LEFT) {
            if (pivot == 1) {
                orientation = FlipperOrientation.TOP;
            } else {
                orientation = FlipperOrientation.BOTTOM;
            }
        } else { // RIGHT
            if (pivot == 2) {
                orientation = FlipperOrientation.TOP;
            } else {
                orientation = FlipperOrientation.BOTTOM;
            }
        }
    }

    /** Returns the current state of the left flipper while is is being rotated */
    public String getState() {
        String state = "";
        state = "Flipper Angle: " + flipperAngle + "\n";
        //state = state + "X1: " + oneLineFlipper.p1().x() + "Y1: " + oneLineFlipper.p1().y() + "X2: "
        //        + oneLineFlipper.p2().x() + " Y2: " + oneLineFlipper.p2().y();
        return state;
    }

    /**
     * Returns String representation of the board depending on whether it's
     * vertical or not. Does not support diagonal flippers
     * 
     * @return string representation of a flipper
     */
    @Override
    public String toString() {
        if (orientation == FlipperOrientation.TOP || orientation == FlipperOrientation.BOTTOM) {
            return "_";
        } else {
            return "|";
        }
    }

    /** Returns the name of the LeftFlipper gadget */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns a string describing the orientation of the flipper.
     * 
     * @return orientation of flipper
     */
    public FlipperOrientation getOrientation() {
        return this.orientation;
    }

    @Override
    public synchronized String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        Vect position = new Vect(this.xLoc, this.yLoc);
        // I am assuming NW=TOP, NE=RIGHT, SE=LEFT, SW=BOTTOM
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
        return this.xLoc;
    }

    @Override
    public double getY() {
        return this.yLoc;
    }
}