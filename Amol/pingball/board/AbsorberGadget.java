package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Represents an absorber gadget in pingball.
 */
public class AbsorberGadget implements Gadget {
    private String name;
    private double width;
    private double height;
    private Vect position;
    private Ball lastStoredBall;
    private boolean ejected;
    private List<Gadget> hookedGadgets;
    
   
    /**
     * Abstraction Function:
     * name, width, height and position is the respective properties of this gadget.
     * lastStoredBall is the ball which was stored most recently. Can be null if
     * no ball was stored before this.
     * ejected is a flag which holds if the ball in this absorber has been ejected or not.
     * hookedGadgets are all the gadgets which are hooked to this gadget.
     * 
     * Rep Invariant:
     * position and hookedGadgets shouldn't be null.
     */
    
    private void checkRep(){
        assert position != null && hookedGadgets != null;
    }
    /**
     * Creates an absorber gadget of pingball
     * @param position the position of the top left corner of the bounding box
     * @param width the width if the absorber
     * @param height the height of the absorber
     */
    public AbsorberGadget(String name, Vect position, double width, double height) {
        this.name = name;
        this.position = position;
        this.width = width;
        this.height = height;
        this.hookedGadgets = new ArrayList<Gadget>();
        this.ejected = true;
        checkRep();
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Vect getPosition() {
        return this.position;
    }

    @Override
    public double timeUntilCollision(Ball ball) {
        if (lastStoredBall == ball && !ejected) {
            ball.setPosition(this.position.plus(new Vect(this.width - 0.25, this.height - 0.25)));
            ball.setVelocity(new Vect(0, 0));
            return Double.MAX_VALUE;
        }
        
        LineSegment[] borders = new LineSegment[] {
                new LineSegment(this.position, this.position.plus(new Vect(width, 0))),
                new LineSegment(this.position.plus(new Vect(width,0)), this.position.plus(new Vect(width,height))),
                new LineSegment(this.position.plus(new Vect(width,height)), this.position.plus(new Vect(0,height))),
                new LineSegment(this.position.plus(new Vect(0,height)), this.position),
        };
        
        double shortestTime = Double.MAX_VALUE;
        for (LineSegment segment : borders) {
            double time = Geometry.timeUntilWallCollision(segment, ball.getCircle(), ball.getVelocity());
            if (time < shortestTime)
                shortestTime = time;
        }
        
        return shortestTime;
    }

    @Override
    public void reflect(Ball ball) {
        if (!ejected)
            doAction(); // eject last ball first
        ball.setVelocity(new Vect(0, 0));
        ball.setPosition(this.position.plus(new Vect(this.width - 0.25, this.height - 0.25)));
        lastStoredBall = ball;
        ejected = false;

        this.trigger();
    }

    @Override
    public void doAction() {
        if (lastStoredBall == null)
            return;
        lastStoredBall.setVelocity(new Vect(0, -50));
        lastStoredBall.setPosition(this.position.plus(new Vect(this.width - 0.25, -0.25)));
        ejected = true;
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        this.hookedGadgets.add(gadget);
    }

    @Override
    public void trigger() {
        for (Gadget gadget : this.hookedGadgets) {
            gadget.doAction();
        }
    }
    @Override
    public String render(String input) {
        StringBuilder sb = new StringBuilder(input);

        for (int y = (int)position.y(); y < (int)position.y() + height; y++)
            for (int x = (int)position.x(); x < (int)position.x() + width; x++)
                sb.setCharAt(Board.getBoardStringIndexFromVect(new Vect(x, y)), '=');
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AbsorberGadget)) return false;
        return ((AbsorberGadget)obj).name.equals(this.name);
    }
}
