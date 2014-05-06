package gameplay;

import java.util.HashMap;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import utilities.Coords;

public class OuterWallPart implements Gadget {
    private float reflectionCoefficient = (float) 1.0;
    private final Board board;
    private boolean visibile;
    private Coords location;
    private char representation;
    private LineSegment lineObject;
    private HashMap<String, Circle> cornerCircles = new HashMap<String, Circle>();

    /**
     * Creates a new outer wall.
     * @param board the board where the current wall is to be created on
     * @param visible whether the wall is invisible or not. the ball: passes through
     *            the wall into a new board if visibility = False, bounces if
     *            visibility = True
     * @param location the coordinates of the top left corner of the wall.
     * @param representation Character that represents a wall in the board's toString() method.
     */
    public OuterWallPart(Board board, boolean visible, Coords location,
            char representation) {
        this.board = board;
        this.visibile = visible;
        this.location = location;
        this.representation = representation;
        this.createGeoObjects();
    }

    @Override
    public synchronized Ball bounce(Ball b, String identifier) {
        if (this.visibile) {
            Vect newVel = b.getVelocity();
            if (identifier == "line") {
                newVel = Geometry.reflectWall(lineObject, b.getVelocity(),
                    reflectionCoefficient);
            }
            else if (identifier == "a") {
                newVel = Geometry.reflectCircle(this.cornerCircles.get("a").getCenter(), b.getCircleObject().getCenter(), b.getVelocity(), this.reflectionCoefficient);
            }
            else if (identifier == "b") {
                newVel = Geometry.reflectCircle(this.cornerCircles.get("b").getCenter(), b.getCircleObject().getCenter(), b.getVelocity(), this.reflectionCoefficient);
            }
            
            return new Ball(b.getCenter(), newVel);
            
        } else {
            //System.out.println("Passing through the ball!");
            int first = -1;
            int last = 20;
            if (location.getX() == first) {
                Coords newCenter = new Coords(19 - b.getX(), b.getY());
                board.sendBall(new Ball(newCenter, b.getVelocity()), "left");
            } else if (location.getX() == last) {
                Coords newCenter = new Coords(19 - b.getX(), b.getY());
                board.sendBall(new Ball(newCenter, b.getVelocity()), "right");
            } else if (location.getY() == first) {
                Coords newCenter = new Coords(b.getY(), 19 - b.getY());
                board.sendBall(new Ball(newCenter, b.getVelocity()), "top");
            } else if (location.getY() == last) {
                Coords newCenter = new Coords(b.getY(), 19 - b.getY());
                board.sendBall(new Ball(newCenter, b.getVelocity()), "bottom");
            }
            return null;
        }
    }

    @Override
    public float getReflectionCoefficient() {
        if (this.visibile)
            return this.reflectionCoefficient;
        return 0;
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException(
                "We walls aren't allowed to be \"active\"! :(");
    }

    @Override
    public synchronized Coords getPosition() {
        return location;
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }

    @Override
    public void addTrigger(Gadget g) {
        throw new UnsupportedOperationException("Can't add a trigger here.");
    }

    @Override
    public synchronized void createGeoObjects() {
        String type = this.getOrientation();

        if (type.equals("TOP")) {
            this.lineObject = new LineSegment(this.location.getX(),
                    this.location.getY() + 1, this.location.getX() + 1,
                    this.location.getY() + 1);
            Circle a = new Circle(this.location.getX(), this.location.getY()+1,
                    0.0d);
            Circle b = new Circle(this.location.getX() + 1,
                    this.location.getY() + 1, 0.0d);
            this.cornerCircles.put("a", a);
            this.cornerCircles.put("b", b);
        } else if (type.equals("BOTTOM")) {
            this.lineObject = new LineSegment(this.location.getX(),
                    this.location.getY(), this.location.getX() + 1,
                    this.location.getY());
            Circle a = new Circle(this.location.getX(), this.location.getY(),
                    0.0d);
            Circle b = new Circle(this.location.getX() + 1,
                    this.location.getY(), 0.0d);
            this.cornerCircles.put("a", a);
            this.cornerCircles.put("b", b);
            
        } else if (type.equals("LEFT")) {
            this.lineObject = new LineSegment(this.location.getX() + 1,
                    this.location.getY(), this.location.getX() + 1,
                    this.location.getY() + 1);
            Circle a = new Circle(this.location.getX() + 1, this.location.getY(),
                    0.0d);
            Circle b = new Circle(this.location.getX() + 1,
                    this.location.getY() + 1, 0.0d);
            this.cornerCircles.put("a", a);
            this.cornerCircles.put("b", b);
        } else if (type.equals("RIGHT")) {
            this.lineObject = new LineSegment(this.location.getX(),
                    this.location.getY(), this.location.getX(),
                    this.location.getY() + 1);
            Circle a = new Circle(this.location.getX(), this.location.getY(),
                    0.0d);
            Circle b = new Circle(this.location.getX(),
                    this.location.getY() + 1, 0.0d);
            this.cornerCircles.put("a", a);
            this.cornerCircles.put("b", b);
        }
    }

    @Override
    public synchronized String getType() {
        return "OUTER_WALL_PART";
    }

    @Override
    public synchronized HashMap<String, Double> leastCollisionTime(Ball b, Vect v) {
        HashMap<String, Double> allTimes = new HashMap<String, Double>();
        double minimum = Double.POSITIVE_INFINITY;
        String identifier = "";

        // Find the time for collision with the line.
        double lineTime = Geometry.timeUntilWallCollision(this.lineObject,
                b.getCircleObject(), v);

        if (lineTime < minimum) {
            minimum = lineTime;
            identifier = "line";
        }

        // Find the time for collision with the corner circles.
        double cornerATime = Geometry.timeUntilCircleCollision(
                this.cornerCircles.get("a"), b.getCircleObject(), v);

        if (cornerATime < minimum) {
            minimum = cornerATime;
            identifier = "a";
        }

        double cornerBTime = Geometry.timeUntilCircleCollision(
                this.cornerCircles.get("b"), b.getCircleObject(), v);

        if (cornerBTime < minimum) {
            minimum = cornerATime;
            identifier = "b";
        }

        allTimes.put(identifier, minimum);
        
        //System.out.println(identifier);
        //System.out.println(this.getOrientation());
        return allTimes;
    }
    
    /**
     * Returns the orientation of the wall.
     * Orientation means which side of the board the wall is in.
     * 
     * @return  
     *          The orientation of the current wall- one of LEFT, RIGHT, TOP or BOTTOM.
     */
    public synchronized String getOrientation() {
        if (location.getX() == -1)
            return "LEFT";
        else if (location.getX() == 20)
            return "RIGHT";
        else if (location.getY() == -1)
            return "TOP";
        else if (location.getY() == 20)
            return "BOTTOM";
        throw new RuntimeException("this is not a possible wall location");
    }

}