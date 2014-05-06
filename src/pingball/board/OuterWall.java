package pingball.board;


import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Represents the outer walls in pingball
 */
public class OuterWall implements Gadget {
    private String neighborName;
    private int width;
    private int height;
    private Vect position;
    private OuterWallsOrientation orientation;
    private double coefficientOfReflection;
    
    /**
     * Abstraction Function:
     * The width, height, position and coefficientOfReflection is the respective property of the gadget. 
     * neighborName is the name of the neighboring board
     * hookedGadgets are all the gadgets which are hooked to this gadget.
     * 
     * Rep Invariant:
     * The width and the height should be nonnegative. 
     * The position should not be null.
     */
    
    private void checkRep(){
        assert position != null;
    }
    
    /**
     * Represents the orientation of the outer walls.
     */
    public enum OuterWallsOrientation {
        HORIZONTAL, VERTICAL
    }
    
    /**
     * Creates a new gadget to represent the outer walls with the given position, orientation and coefficient of reflection.
     * @param position the position of the top left vertex of the wall.
     * @param orientation the orientation of the wall
     * @param coefficientOfReflection the coefficient of reflection
     */
    public OuterWall(Vect position, OuterWallsOrientation orientation, double coefficientOfReflection) {
        this.height = 20;
        this.width = 20;
        this.position = position;
        this.orientation = orientation;
        this.coefficientOfReflection = coefficientOfReflection;
        this.neighborName = null;
        checkRep();
    }
    
    /**
     * Creates a new gadget to represent the outer walls with the given position, orientation. The coefficient of reflection is 1.0
     * @param position the position of the top left vertex of the wall.
     * @param orientation the orientation of the wall
     */
    public OuterWall(Vect position, OuterWallsOrientation orientation) {
        this(position, orientation, 1.0);
    }
    
    @Override
    public String getName() {
        return "";
    }
    
    @Override
    public Vect getPosition() {
        return this.position;
    }
    
    /**
     * Gets the name of the neighbor board if connected.
     * @return the name of the neighbor board
     */
    public String getNeighborName() {
        return this.neighborName;
    }
    
    /**
     * Set the name of the neighbor board.
     * @param neighborName the name of the neighbor board
     */
    public void setNeighborName(String neighborName) {
        this.neighborName = neighborName;
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
            return Geometry.timeUntilWallCollision(new LineSegment(this.position.plus(new Vect(-1, 0)).plus(delta), this.position.plus(new Vect(21, 0)).plus(delta)), ball.getCircle(), ball.getVelocity());
        case VERTICAL:
            delta = new Vect(0, 0);
            if (this.position.equals(new Vect(21, 0)))
                delta = new Vect(-1, 0);
            return Geometry.timeUntilWallCollision(new LineSegment(this.position.plus(new Vect(0, -1)).plus(delta), this.position.plus(new Vect(0, 21)).plus(delta)), ball.getCircle(), ball.getVelocity());  
        }
        throw new RuntimeException("This wall is in illegal state");
    }

    public void reflect(Ball ball) {
        if (this.neighborName != null)
            return;
        
        Vect delta;
        switch (this.orientation) {
        case HORIZONTAL:
            ball.setVelocity(Geometry.reflectWall(new LineSegment(this.position.plus(new Vect(-1, 0)), this.position.plus(new Vect(21, 0))), ball.getVelocity(), this.coefficientOfReflection));
            break;
        case VERTICAL:
            delta = new Vect(0, 0);
            if (this.position.equals(new Vect(21, 0)))
                delta = new Vect(-1, 0);
            ball.setVelocity(Geometry.reflectWall(new LineSegment(this.position.plus(new Vect(0, -1)).plus(delta), this.position.plus(new Vect(0, 21)).plus(delta)), ball.getVelocity(), this.coefficientOfReflection));
            break;
        }
        
    }

    public void doAction() {
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
    }

    @Override
    public void trigger() {
    }

    public String render(String input) {
    	String label = this.neighborName;
    	if (label == null)
    		label = "";
    	
        StringBuilder sb = new StringBuilder(input);
        int nameStartIndex = (int)(11 - (double)label.length() / 2.0);
       
        if (this.orientation == OuterWallsOrientation.HORIZONTAL) {
            for (int x = 0; x < nameStartIndex; x++)
                sb.setCharAt((int)this.position.y() * (this.width + 3) + (int)this.position.x() + x, '.');
            for (int x = nameStartIndex; x < nameStartIndex + label.length(); x++)
                sb.setCharAt((int)this.position.y() * (this.width + 3) + (int)this.position.x() + x, label.charAt(x - nameStartIndex));
            for (int x = nameStartIndex + label.length(); x < this.width + 2; x++)
                sb.setCharAt((int)this.position.y() * (this.width + 3) + (int)this.position.x() + x, '.');
        } else {
            for (int y = 0; y < nameStartIndex; y++)
                sb.setCharAt(((int)this.position.y() + y) * (this.width + 3) + (int)this.position.x(), '.');
            for (int y = nameStartIndex; y < nameStartIndex + label.length(); y++)
                sb.setCharAt(((int)this.position.y() + y) * (this.width + 3) + (int)this.position.x(), label.charAt(y - nameStartIndex));
            for (int y = nameStartIndex + label.length(); y < this.height + 2; y++)
                sb.setCharAt(((int)this.position.y() + y) * (this.width + 3) + (int)this.position.x(), '.');
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
    public void reactBall(Ball ball) {
    }

    @Override
    public void action() {
    }

    @Override
    public double getReflCoeff() {
        return 0;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public String type() {
        return null;
    }
    
}
