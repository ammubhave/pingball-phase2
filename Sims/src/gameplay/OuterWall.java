package gameplay;

import java.util.ArrayList;
import java.util.HashMap;

import physics.Vect;
import utilities.Coords;

public class OuterWall implements Gadget {
    private float reflectionCoefficient = (float) 1.0;
    private final Board board;
    private boolean visible;
    private ArrayList<OuterWallPart> parts;
    private String string;

    /**
     * Creates a new outer wall that lies along the whole side of a board.
     * @param board 
     *              the board on which the wall is supposed to be created.
     * @param loc 
     *          the location of the wall. It's one of "top", "left", "right" and "bottom" 
     */
    public OuterWall(Board board, String loc) {
        // first HORIZONTAL = top
        this.board = board;
        parts = new ArrayList<OuterWallPart>();
        reset(loc);
    }

    /**
     * Resets the board's wall when the board is connected to another or disconnected.
     * 
     * @param visible the visibility of the wall, true if there is a wall, false if the ball can pass to a different board
     * @param loc the location of the wall (left, right, top, bottom)
     * @param s the string that the wall should be set to
     */
    public synchronized void reset(boolean visible, String loc, String s) {
        this.visible = visible;
        this.string = s;
        for (int i = 0; i <= 19; i++)
            if (loc.equals("LEFT"))
                parts.add(new OuterWallPart(board, visible, new Coords(-1, i),
                        s.charAt(i)));
            else if (loc.equals("RIGHT"))
                parts.add(new OuterWallPart(board, visible, new Coords(20, i),
                        s.charAt(i)));
            else if (loc.equals("TOP"))
                parts.add(new OuterWallPart(board, visible, new Coords(i, -1),
                        s.charAt(i)));
            else if (loc.equals("BOTTOM"))
                parts.add(new OuterWallPart(board, visible, new Coords(i, 20),
                        s.charAt(i)));
    }

    /**
     * Resets to the default, not connected wall
     * @param loc the location of the wall (left or right or top or bottom)
     */
    public synchronized void reset(String loc) {
        reset(true, loc, "....................");
    }

    @Override
    public synchronized Ball bounce(Ball b, String identifier) {
        throw new UnsupportedOperationException(
                "Wall's bounce should never be called");
    }

    @Override
    public float getReflectionCoefficient() {
        if (this.visible)
            return this.reflectionCoefficient;
        return 0;
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException(
                "We walls aren't allowed to be \"active\"! :(");
    }

    @Override
    public Coords getPosition() {
        throw new UnsupportedOperationException("You can't do this >:(");
    }
    
    @Override
    public String toString() {
        return string;
    }

    @Override
    public void addTrigger(Gadget g) {
        throw new UnsupportedOperationException("Can't add trigger here.");
    }

    @Override
    public void createGeoObjects() {
        throw new UnsupportedOperationException(
                "Can't call selfTrigger createGeo here.");
    }

    @Override
    public synchronized String getType() {
        return "OUTER_WALL";
    }
    
    
    public synchronized ArrayList<OuterWallPart> getParts() {
        return parts;
    }

    @Override
    public HashMap<String, Double> leastCollisionTime(Ball b, Vect v) {
        throw new UnsupportedOperationException("You can't come here!");

    }

}