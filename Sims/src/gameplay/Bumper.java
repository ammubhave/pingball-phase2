package gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import utilities.Coords;

public class Bumper implements Gadget {
    private float reflectionCoefficient = 1.0f;
    private float size = 1.0f; // If this is a circle, size = Radius
    private int orientation; // either 0, 90, 180, 270 representing triangle
                             // orientation
    private Coords position;
    private Shape type;
    private ArrayList<Gadget> triggers;

    private HashMap<String, LineSegment> lineSegments = new HashMap<String, LineSegment>();
    private HashMap<String, Circle> cornerCircles = new HashMap<String, Circle>();
    private Circle circleObject;

    private enum Shape {
        CIRCLE, SQUARE, TRIANGLE
    }
    
    /**
     * Creates a new instance of bumper.
     * 
     * @param position
     *                  the coordinates of the top left corner of the bumper.
     * @param type
     *                  one of square, triangle or circle representing the type of the bumper we're about to create.
     */
    public Bumper(Coords position, String type) {
        this(position, type, 0);
    }

    /**
     * Creates a new instance of bumper.
     * 
     * @param position
     *                  the coordinates of the top left corner of the bumper.
     * @param type
     *                  one of square, triangle or circle representing the type of the bumper we're about to create.
     *                   
     * @param orientation
     *                  the orientation for triangular bumpers- one of 0, 90, 180, or 270.
     */
    public Bumper(Coords position, String type, int orientation) {
        this.position = position;
        this.orientation = orientation;
        this.triggers = new ArrayList<Gadget>();

        switch (type) {
            case "SQUARE":
                this.type = Shape.SQUARE;
                break;
            case "TRIANGLE":
                this.type = Shape.TRIANGLE;
                break;
            case "CIRCLE":
                this.type = Shape.CIRCLE;
                break;
            default:
                this.type = Shape.SQUARE;
                break;
        }
        this.createGeoObjects();
    }

    @Override
    public synchronized void createGeoObjects() {
        switch (this.type) {
        case SQUARE:
            // Figure out the coordinates of the four corners first.
            double nwX = this.position.getX();
            double nwY = this.position.getY();

            double neX = nwX + this.size;
            double neY = nwY;

            double swX = nwX;
            double swY = nwY + this.size;

            double seX = neX + this.size;
            double seY = neY + this.size;

            // Create line objects.
            this.lineSegments.put("top", new LineSegment(nwX, nwY, neX, neY));
            this.lineSegments
                    .put("bottom", new LineSegment(swX, swY, seX, seY));
            this.lineSegments.put("left", new LineSegment(nwX, nwY, swX, swY));
            this.lineSegments.put("right", new LineSegment(neX, neY, seX, seY));

            // Create circles with 0 radius.
            this.cornerCircles.put("nw", new Circle(nwX, nwY, 0.0d));
            this.cornerCircles.put("ne", new Circle(neX, neY, 0.0d));
            this.cornerCircles.put("sw", new Circle(swX, swY, 0.0d));
            this.cornerCircles.put("se", new Circle(seX, seY, 0.0d));

            break;

        case CIRCLE:
            this.circleObject = new Circle(position.getX(), position.getY(),
                    this.size);
            break;

        case TRIANGLE:
            switch (this.orientation) {
            case 0:
                double nwX2 = this.position.getX();
                double nwY2 = this.position.getY();

                double swX2 = nwX2;
                double swY2 = nwY2 + this.size;

                double neX2 = nwX2 + this.size;
                double neY2 = nwY2;

                this.lineSegments.put("hLeg", new LineSegment(nwX2, nwY2, neX2,
                        neY2));
                this.lineSegments.put("vLeg", new LineSegment(nwX2, nwY2, swX2,
                        swY2));
                this.lineSegments.put("hypotenuse", new LineSegment(neX2, neY2,
                        swX2, swY2));

                this.cornerCircles.put("hLegVLeg", new Circle(nwX2, nwY2, 0));
                this.cornerCircles.put("hLegHypotenuse", new Circle(neX2, neY2,
                        0));
                this.cornerCircles.put("vLegHypotenuse", new Circle(swX2, swY2,
                        0));

            case 90:
                double neX3 = this.position.getX();
                double neY3 = this.position.getY();

                double nwX3 = neX3 - this.size;
                double nwY3 = neY3;

                double seX3 = neX3;
                double seY3 = neY3 + this.size;

                this.lineSegments.put("hLeg", new LineSegment(nwX3, nwY3, neX3,
                        neY3));
                this.lineSegments.put("vLeg", new LineSegment(neX3, neY3, seX3,
                        seY3));
                this.lineSegments.put("hypotenuse", new LineSegment(nwX3, nwY3,
                        seX3, seY3));

                this.cornerCircles.put("hLegVLeg", new Circle(neX3, neY3, 0));
                this.cornerCircles.put("hLegHypotenuse", new Circle(nwX3, nwY3,
                        0));
                this.cornerCircles.put("vLegHypotenuse", new Circle(seX3, seY3,
                        0));

            case 180:
                double seX4 = this.position.getX();
                double seY4 = this.position.getY();

                double neX4 = seX4;
                double neY4 = seY4 - this.size;

                double swX4 = seX4 - this.size;
                double swY4 = seY4;

                this.lineSegments.put("hLeg", new LineSegment(swX4, swY4, seX4,
                        seY4));
                this.lineSegments.put("vLeg", new LineSegment(neX4, neY4, seX4,
                        seY4));
                this.lineSegments.put("hypotenuse", new LineSegment(neX4, neY4,
                        swX4, swY4));

                this.cornerCircles.put("hLegVLeg", new Circle(seX4, seY4, 0));
                this.cornerCircles.put("hLegHypotenuse", new Circle(swX4, swY4,
                        0));
                this.cornerCircles.put("vLegHypotenuse", new Circle(neX4, neY4,
                        0));

                break;

            case 270:
                double swX5 = this.position.getX();
                double swY5 = this.position.getY();

                double nwX5 = swX5;
                double nwY5 = swY5 - this.size;

                double seX5 = swX5 + this.size;
                double seY5 = swY5;

                this.lineSegments.put("hLeg", new LineSegment(swX5, swY5, seX5,
                        seY5));
                this.lineSegments.put("vLeg", new LineSegment(swX5, swY5, nwX5,
                        nwY5));
                this.lineSegments.put("hypotenuse", new LineSegment(nwX5, nwY5,
                        seX5, seY5));

                this.cornerCircles.put("hLegVLeg", new Circle(swX5, swY5, 0));
                this.cornerCircles.put("hLegHypotenuse", new Circle(seX5, seY5,
                        0));
                this.cornerCircles.put("vLegHypotenuse", new Circle(nwX5, nwY5,
                        0));

                break;

            }
        }
    }

    @Override
    public synchronized Ball bounce(Ball b, String identifier) {
        Coords ballCoords = b.getCenter();
        Shape shape = this.type;

        double positionX = b.getX();
        double positionY = b.getY();
        double vectX = b.getVelocity().x();
        double vectY = b.getVelocity().y();

        Vect ball = new Vect(positionX, positionY);
        Vect vel = b.getVelocity();

        switch (shape) {
        case SQUARE:
            if (identifier.equals("top") || identifier.equals("bottom")
                    || identifier.equals("right") || identifier.equals("left")) {
                Vect newVel = Geometry.reflectWall(
                        lineSegments.get(identifier), vel,
                        reflectionCoefficient);
                return new Ball(ballCoords, newVel);
            }
            if (identifier.equals("nw") || identifier.equals("sw")
                    || identifier.equals("ne") || identifier.equals("se")) {
                Vect newVel = Geometry.reflectCircle(
                        cornerCircles.get(identifier).getCenter(), ball, vel,
                        reflectionCoefficient);
                return new Ball(ballCoords, newVel);
            }

        case TRIANGLE:
            if (identifier.equals("hLeg") || identifier.equals("vLeg")
                    || identifier.equals("hypotenuse")) {
                Vect newVel = Geometry.reflectWall(
                        lineSegments.get(identifier), vel,
                        reflectionCoefficient);
                return new Ball(ballCoords, newVel);
            }

            if (identifier.equals("hLegVLeg")
                    || identifier.equals("hLegHypotenuse")
                    || identifier.equals("vLegHypotenuse")) {
                Vect newVel = Geometry.reflectCircle(
                        cornerCircles.get(identifier).getCenter(), ball, vel,
                        reflectionCoefficient);
                return new Ball(ballCoords, newVel);
            }

        case CIRCLE:
            Vect returnVel = Geometry.reflectCircle(circleObject.getCenter(),
                    ball, vel, reflectionCoefficient);
            return new Ball(new Coords(positionX, positionY), returnVel);
        }
        return new Ball(new Coords(positionX, positionY),
                new Vect(vectX, vectY));
    }

    @Override
    public float getReflectionCoefficient() {
        return this.reflectionCoefficient;
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException(
                "We bumpers aren't allowed to be \"active\"! :(");
    }

    @Override
    public synchronized Coords getPosition() {
        return position;
    }

    @Override
    public String toString() {
        switch (type) {
        case SQUARE:
            return "#";
        case TRIANGLE:
            if (orientation % 180 == 0)
                return "/";
            else if (orientation % 180 == 90)
                return "\\";
        case CIRCLE:
            return "O";
        }
        // we don't have a bumper?
        return " ";
    }

    @Override
    public synchronized void addTrigger(Gadget g) {
        triggers.add(g);
    }

    @Override
    public String getType() {
        return "BUMPER";
    }
    
    /**
     * Gets the type of bumper.
     * 
     * @return
     *          one of "SQUARE", "TRIANGLE" or "CIRCLE" representing the type of Bumper.
     */
    public String getBumperType() {
        switch (this.type) {
            case SQUARE:
                return "SQUARE";
            case TRIANGLE:
                return "TRIANGLE";
            case CIRCLE:
                return "CIRCLE";
        }
        throw new RuntimeException();
    }

    @Override
    public synchronized HashMap<String, Double> leastCollisionTime(Ball b, Vect v) {
        HashMap<String, Double> allTimes = new HashMap<String, Double>();
        double minimum = Double.POSITIVE_INFINITY;
        String identifier = "";
        switch (this.type) {
            case SQUARE:
                // First find all times for line segment.s
                HashMap<String, LineSegment> allLinesSquare = new HashMap<String, LineSegment>();
                allLinesSquare.putAll(this.lineSegments);
    
                Iterator itSectionsSquare = allLinesSquare.entrySet().iterator();
                while (itSectionsSquare.hasNext()) {
                    Map.Entry pairsSections = (Map.Entry) itSectionsSquare.next();
                    String name = (String) pairsSections.getKey();
                    LineSegment line = (LineSegment) pairsSections.getValue();
                    double time = Geometry.timeUntilWallCollision(line,
                            b.getCircleObject(), v);
                    if (time < minimum) {
                        minimum = time;
                        identifier = name;
                    }
    
                    itSectionsSquare.remove(); // avoids a
                                               // ConcurrentModificationException
                }
    
                // Do the same for all the border cases.
                HashMap<String, Circle> allCorners = new HashMap<String, Circle>();
                allCorners.putAll(this.cornerCircles);
    
                Iterator itCorners = allCorners.entrySet().iterator();
                while (itCorners.hasNext()) {
                    Map.Entry pairsCorners = (Map.Entry) itCorners.next();
                    String name = (String) pairsCorners.getKey();
                    Circle cornerCircle = (Circle) pairsCorners.getValue();
                    double time = Geometry.timeUntilCircleCollision(cornerCircle,
                            b.getCircleObject(), v);
                    if (time < minimum) {
                        minimum = time;
                        identifier = name;
                    }
    
                    itCorners.remove(); // avoids a ConcurrentModificationException
                }
    
                allTimes.put(identifier, minimum);
                break;
    
            case TRIANGLE:
                // First find all times for line segment.s
                HashMap<String, LineSegment> allLinesTriangle = new HashMap<String, LineSegment>();
                allLinesTriangle.putAll(this.lineSegments);
    
                Iterator itSectionsTriangle = allLinesTriangle.entrySet()
                        .iterator();
                while (itSectionsTriangle.hasNext()) {
                    Map.Entry pairsSectionsTriangle = (Map.Entry) itSectionsTriangle
                            .next();
                    String name = (String) pairsSectionsTriangle.getKey();
                    LineSegment line = (LineSegment) pairsSectionsTriangle
                            .getValue();
                    double time = Geometry.timeUntilWallCollision(line,
                            b.getCircleObject(), v);
                    if (time < minimum) {
                        minimum = time;
                        identifier = name;
                    }
    
                    itSectionsTriangle.remove(); // avoids a
                                                 // ConcurrentModificationException
                }
    
                // Do the same for all the border cases.
                HashMap<String, Circle> allCornersTriangle = new HashMap<String, Circle>();
                allCornersTriangle.putAll(this.cornerCircles);
    
                Iterator itCornersTriangle = allCornersTriangle.entrySet()
                        .iterator();
                while (itCornersTriangle.hasNext()) {
                    Map.Entry pairsCornersTriangle = (Map.Entry) itCornersTriangle
                            .next();
                    String name = (String) pairsCornersTriangle.getKey();
                    Circle cornerCircle = (Circle) pairsCornersTriangle.getValue();
                    double time = Geometry.timeUntilCircleCollision(cornerCircle,
                            b.getCircleObject(), v);
                    if (time < minimum) {
                        minimum = time;
                        identifier = name;
                    }
                    itCornersTriangle.remove(); // avoids a
                    // ConcurrentModificationException
                }
                
                allTimes.put(identifier, minimum);
                break;
                    
            case CIRCLE:
                minimum = Geometry.timeUntilCircleCollision(this.circleObject, b.getCircleObject(), b.getVelocity());
                allTimes.put("Hi! My name is Circle!", minimum);
                break;
        }
        return allTimes;
    }
}
