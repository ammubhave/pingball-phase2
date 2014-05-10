package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import pingball.Tuple;
import static pingball.MapFilterReduce.*;

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

    private final Vect position;

    private Ball heldBall = null;

    private final static Vect SHOOT_VELOCITY = new Vect(0, -50);

    private final String name;

    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();

    private final List<LineSegment> sides = new ArrayList<LineSegment>();
    private final List<Circle> cornerCircles = new ArrayList<Circle>();
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
    public Absorber(Vect loc, int width, int height, String n) {
        name = n;
        this.width = width;
        this.height = height;

        xLocation = loc.x();
        yLocation = loc.y();

        this.position = new Vect(xLocation, yLocation);

        sides.add(new LineSegment(xLocation, yLocation, xLocation + width, yLocation));
        sides.add(new LineSegment(xLocation, yLocation + height, xLocation + width, yLocation + height));
        sides.add(new LineSegment(xLocation, yLocation, xLocation, yLocation + height));
        sides.add(new LineSegment(xLocation + width, yLocation, xLocation + width, yLocation + height));

        cornerCircles.add(new Circle(xLocation, yLocation, 0));
        cornerCircles.add(new Circle(xLocation + width, yLocation, 0));
        cornerCircles.add(new Circle(xLocation, yLocation + height, 0));
        cornerCircles.add(new Circle(xLocation + width, yLocation + height, 0));
    }

    @Override
    public void trigger() {
        for (int i = 0; i < gadgetsToBeHooked.size(); i++) {
            gadgetsToBeHooked.get(i).action();
        }
    }
    
    /*
     * Returns the smallest collision time and the colliding wall for that ball
     * @param ball the ball for which info is to be taken
     * @return tuple of collision info
     
    private Tuple<Double, LineSegment> getCollisionInfo(Ball ball) {
        /*
        class TupleDLs extends Tuple<Double, LineSegment> {
            public TupleDLs(Double x, LineSegment y) {
                super(x, y);
            }
        }
        
        Function<LineSegment, TupleDLs> mapper = new Function<LineSegment, TupleDLs>() {
            @Override
            public TupleDLs apply(LineSegment t) {
                return new TupleDLs(
                        Geometry.timeUntilWallCollision(t, ball.getCircle(), ball.getVelocity()),
                        t
                );
            }
        };
        List<TupleDLs> mapped = map(mapper, sides);
        
        BinOp<TupleDLs, TupleDLs, TupleDLs> reducer = new BinOp<TupleDLs, TupleDLs, TupleDLs>() {
            @Override
            public TupleDLs apply(TupleDLs t, TupleDLs u) {
                return t.x < u.x ? t : u;
            }
        };
        return reduce(mapped, reducer, new TupleDLs(Double.POSITIVE_INFINITY, null));
        double smallestTime = Double.POSITIVE_INFINITY;
        LineSegment smallestTimeWall = null;
        for (LineSegment ls : sides) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
                smallestTimeWall = ls;
            }
        }    
        return new Tuple<Double, LineSegment>(smallestTime, smallestTimeWall);
    }*/

    /**
     * Calculates time on ball will take to hit this bumper if it travelled in straight line.
     * 
     * @param ball the ball to check
     * @return amount of time to take to trigger object based on inputted ball.
     */
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
        if (heldBall != null) {            
            heldBall.changeVelocity(SHOOT_VELOCITY);
            heldBall.changePos(new Vect(xLocation + width - 0.25, yLocation - 0.25));
            System.out.println(heldBall.getVelocity());
            heldBall = null;
        }
    }
 
    public void reactBall(Ball ball) {
        if (heldBall != null && !isInside(ball)) {           
            GadgetHelpers.reflectBall(sides, cornerCircles, ball);
        } else if (heldBall == null) {
            ball.changePos(new Vect(xLocation + width - 0.25, yLocation + height - 0.25));
            ball.changeVelocity(new Vect(0, 0));
            heldBall = ball;
        } else {
            heldBall.changePos(new Vect(xLocation + width - 0.25, yLocation + height - 0.25));
            heldBall.changeVelocity(new Vect(0, 0));
        }

        this.trigger();
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
    
    public List<LineSegment> getLineSegments() {
        return this.sides;
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

    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }
    
    @Override
    public String render(String input) {
        StringBuilder sb = new StringBuilder(input);

        for (int y = (int)position.y(); y < (int)position.y() + height; y++)
            for (int x = (int)position.x(); x < (int)position.x() + width; x++)
                sb.setCharAt(Board.getBoardStringIndexFromVect(new Vect(x, y)), '=');
        
        return sb.toString();
    }

}