package pingball.board;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import pingball.proto.Message;

/**
 * Represents the outer walls in pingball
 */
public class OuterWall implements Gadget {
    /**
     * Thread Safety:
     * - the mutable neighborName is modified synchronized, render which accesses neighborName is synchronized
     * - all others are final and not mutated
     */
    
    private String neighborName;
    
    private final int width;
    private final int height;
    private final Vect position;
    private final OuterWallsOrientation orientation;
    private final String name;    
    private final static double REFL_COEFF = 1;

    /**
     * Rep Invariant:
     * - everything should be non-null (except neighborName)
     */

    private void checkRep() {
        assert position != null;
        assert orientation != null;
        assert name != null;
        
        switch (orientation) {
        case HORIZONTAL:
        case VERTICAL:
            break;
        default:
            assert false;
        }
    }

    /**
     * Represents the orientation of the outer walls.
     */
    public enum OuterWallsOrientation {
        HORIZONTAL, VERTICAL
    }

    /**
     * Creates a new gadget to represent the outer walls with the given
     * position, orientation and coefficient of reflection.
     * 
     * @param position
     *            the position of the top left vertex of the wall.
     * @param orientation
     *            the orientation of the wall
     * @param coefficientOfReflection
     *            the coefficient of reflection
     */
    public OuterWall(Vect position, OuterWallsOrientation orientation, String name) {
        this.height = Board.DEFAULT_SIZE;
        this.width = Board.DEFAULT_SIZE;
        this.position = position;
        this.orientation = orientation;
        this.neighborName = null;
        this.name = name;
        
        checkRep();
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the name of the neighbor board if connected. Otherwise null.
     * @return the name of the neighbor board
     */
    public synchronized String getNeighborName() {
        return this.neighborName;
    }

    /**
     * Set the name of the neighbor board.
     * A value of null means that no board is connected
     * @param neighborName the name of the neighbor board
     */
    public synchronized void setNeighborName(String neighborName) {
        this.neighborName = neighborName;
        
        checkRep();
    }

    @Override
    public double leastCollisionTime(Ball ball) {
        if (this.neighborName != null)
            return Double.MAX_VALUE;

        Vect delta;
        switch (this.orientation) {
        case HORIZONTAL:
            delta = new Vect(0, 0);
            if (this.position.equals(new Vect(0, 21)))
                delta = new Vect(0, -1);
            return Geometry.timeUntilWallCollision(new LineSegment(this.position.plus(new Vect(-1, 0)).plus(delta),
                    this.position.plus(new Vect(21, 0)).plus(delta)), ball.getCircle(), ball.getVelocity());
        case VERTICAL:
            delta = new Vect(0, 0);
            if (this.position.equals(new Vect(21, 0)))
                delta = new Vect(-1, 0);
            return Geometry.timeUntilWallCollision(new LineSegment(this.position.plus(new Vect(0, -1)).plus(delta),
                    this.position.plus(new Vect(0, 21)).plus(delta)), ball.getCircle(), ball.getVelocity());
        }
        throw new RuntimeException("This wall is in illegal state");
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
    }

    @Override
    public void trigger() {
    }

    public synchronized String render(String input) {
        String label = this.neighborName;
        if (label == null)
            label = "";

        StringBuilder sb = new StringBuilder(input);
        int nameStartIndex = (int) (11 - (double) label.length() / 2.0);

        if (this.orientation == OuterWallsOrientation.HORIZONTAL) {
            for (int x = 0; x < nameStartIndex; x++)
                sb.setCharAt((int) this.position.y() * (this.width + 3) + (int) this.position.x() + x, '.');
            for (int x = nameStartIndex; x < nameStartIndex + label.length(); x++)
                sb.setCharAt((int) this.position.y() * (this.width + 3) + (int) this.position.x() + x,
                        label.charAt(x - nameStartIndex));
            for (int x = nameStartIndex + label.length(); x < this.width + 2; x++)
                sb.setCharAt((int) this.position.y() * (this.width + 3) + (int) this.position.x() + x, '.');
        } else {
            for (int y = 0; y < nameStartIndex; y++)
                sb.setCharAt(((int) this.position.y() + y) * (this.width + 3) + (int) this.position.x(), '.');
            for (int y = nameStartIndex; y < nameStartIndex + label.length(); y++)
                sb.setCharAt(((int) this.position.y() + y) * (this.width + 3) + (int) this.position.x(),
                        label.charAt(y - nameStartIndex));
            for (int y = nameStartIndex + label.length(); y < this.height + 2; y++)
                sb.setCharAt(((int) this.position.y() + y) * (this.width + 3) + (int) this.position.x(), '.');
        }
        return sb.toString();
    }

    /** The board edge near this wall. */
    public Edge getEdge() {
        if (this.orientation == OuterWallsOrientation.HORIZONTAL)
            return (this.position.y() == 0) ? Edge.TOP : Edge.BOTTOM;
        else
            return (this.position.x() == 0) ? Edge.LEFT : Edge.RIGHT;
    }

    @Override
    public Message reactBall(Ball ball) {
        if (this.neighborName != null)
            return null;

        GadgetHelpers.playBounceSound();
        Vect delta;
        switch (this.orientation) {
        case HORIZONTAL:
            ball.changeVelocity(Geometry.reflectWall(
                    new LineSegment(this.position.plus(new Vect(-1, 0)), this.position.plus(new Vect(21, 0))),
                    ball.getVelocity(), REFL_COEFF));
            break;
        case VERTICAL:
            delta = new Vect(0, 0);
            if (this.position.equals(new Vect(21, 0)))
                delta = new Vect(-1, 0);
            ball.changeVelocity(Geometry.reflectWall(new LineSegment(this.position.plus(new Vect(0, -1)).plus(delta),
                    this.position.plus(new Vect(0, 21)).plus(delta)), ball.getVelocity(), REFL_COEFF));
            break;
        }

        return null;
    }
    
    /**
     * Returns the orientaton of this wall
     * @return the orientaton of the wall
     */
    public OuterWallsOrientation getOrientation() {
        return this.orientation;
    }

    @Override
    public void action() {
    }

    @Override
    public double getX() {
        return this.position.x();
    }

    @Override
    public double getY() {
        return this.position.y();
    }
}
