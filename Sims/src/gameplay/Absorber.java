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

public class Absorber implements Gadget {
    private float reflectionCoefficient = 1.0f;
    private int height;
    private int width;
    private Coords position;
    private Ball prevBall;
    private boolean selfTriggering = true;
    private ArrayList<Gadget> triggers;

    private HashMap<String, LineSegment> lineSegments = new HashMap<String, LineSegment>();
    private HashMap<String, Circle> cornerCircles = new HashMap<String, Circle>();

    /**
     * Creates a new instance of an absorber.
     * @param k the height of the absorber.
     * @param m the width of the absorber.
     * @param coords the coordinates of the top left corner of the absorber.
     */
    public Absorber(int k, int m, Coords coords) {
        this.position = coords;
        this.height = k - 1;
        this.width = m - 1;
        triggers = new ArrayList<Gadget>();
        this.createGeoObjects();
    }

    @Override
    public synchronized void createGeoObjects() {
        double nwX = this.position.getX();
        double nwY = this.position.getY();

        double neX = this.position.getX() + this.width;
        double neY = nwY;

        double swX = nwX;
        double swY = nwY + this.height;

        double seX = neX;
        double seY = swY;

        // Create line objects.
        this.lineSegments.put("top", new LineSegment(nwX, nwY, neX, neY));
        this.lineSegments.put("bottom", new LineSegment(swX, swY, seX, seY));
        this.lineSegments.put("left", new LineSegment(nwX, nwY, swX, swY));
        this.lineSegments.put("right", new LineSegment(neX, neY, seX, seY));

        // Create circles with 0 radius.
        this.cornerCircles.put("nw", new Circle(nwX, nwY, 0.0d));
        this.cornerCircles.put("ne", new Circle(neX, neY, 0.0d));
        this.cornerCircles.put("sw", new Circle(swX, swY, 0.0d));
        this.cornerCircles.put("se", new Circle(seX, seY, 0.0d));
    }

    @Override
    public synchronized Ball bounce(Ball b, String identifier) {

        double nwX = this.position.getX();
        double nwY = this.position.getY();
        double seX = nwX + this.width;
        double seY = nwY + this.height;
        Coords location = new Coords(seX, seY);

        if (!this.selfTriggering) {
            // Store the ball in the bottom-right corner of the absorber.
            Ball toStore = new Ball(location, new Vect(0, 0));
            this.prevBall = toStore;
        }

        // Trigger the action. Throw whatever ball it's already holding.
        if (this.prevBall != null) {
            // There's a ball stored there.
            Ball ballToThrow = new Ball(location, new Vect(0, -200));
            return ballToThrow;
        } else {
            // There is NO any ball stored in the absorber.
            if (this.selfTriggering) {
                // But we throw whatever ball it just absorbed.
                Ball ballToThrow = new Ball(location, new Vect(0, -200));
                return ballToThrow;
            }
        }

        return null;
    }

    @Override
    public float getReflectionCoefficient() {
        return this.reflectionCoefficient;
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException("No Action");
    }

    @Override
    public synchronized Coords getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "=";
    }

    @Override
    public synchronized void addTrigger(Gadget g) {
        triggers.add(g);
    }

    @Override
    public String getType() {
        return "ABSORBER";
    }
    
    /**
     * Returns the width of the current absorber.
     * @return width of the current absorber.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the current absorber.
     * @return height of the current absorber.
     */
    public int getHeight() {
        return this.height;
    }

    @Override
    public synchronized HashMap<String, Double> leastCollisionTime(Ball b, Vect v) {
        HashMap<String, Double> allTimes = new HashMap<String, Double>();
        double minimum = Double.POSITIVE_INFINITY;
        String identifier = "";
        // First find all times for line segment.
        HashMap<String, LineSegment> allLinesSquare = new HashMap<String, LineSegment>();
        allLinesSquare.putAll(this.lineSegments);

        Iterator itSectionsSquare = allLinesSquare.entrySet().iterator();
        while (itSectionsSquare.hasNext()) {
            Map.Entry pairsSections = (Map.Entry) itSectionsSquare.next();
            String name = (String) pairsSections.getKey();
            LineSegment line = (LineSegment) pairsSections.getValue();
            double time = Geometry.timeUntilWallCollision(line,
                    b.getCircleObject(), v);
            if (time <= minimum) {
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

        return allTimes;
    }


}
