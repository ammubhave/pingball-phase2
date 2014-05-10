package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;

public class Portal implements Gadget {
 
    //specified in spec
    private final static double RADIUS = 0.5;
    
    private final String name;
    private Vect position;
    private Circle portal;
    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();
    private String targetPortalName;
    private String targetBoardName;

    public Portal(Vect loc, String n) {
        name = n;
        position = loc;
        double centerX = loc.x() + RADIUS;
        double centerY = loc.y() + RADIUS;
        portal = new Circle(centerX, centerY, RADIUS);
        targetPortalName=null;
        targetBoardName=null;
    }

    @Override
    public void trigger() {
        for (int i = 0; i < gadgetsToBeHooked.size(); i++) {
            gadgetsToBeHooked.get(i).action();
        }
    }

    @Override
    public double leastCollisionTime(Ball ball) {
        Vect velocity = ball.getVelocity();
        return Geometry.timeUntilCircleCollision(portal, ball.getCircle(), velocity);
    }

    @Override
    public void reactBall(Ball ball) {
        if (targetPortalName==null){
            return;
        }
        else{
            
        }
    }

    @Override
    public void action() {
    }

    public double getX() {
        return portal.getCenter().x();
    }

    @Override
    public double getY() {
        return portal.getCenter().x();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);
    }

    @Override
    public String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(Board.getBoardStringIndexFromVect(this.position), 'o');
        return sb.toString();
    }
    
    /**
     * Set the name of the target portal.
     * @param otherPortal the name of the target portal name
     */
    public void setTargetPortal(String otherPortal) {
        targetPortalName = otherPortal;
    }
    
    /**
     * Set the name of the target board.
     * @param otherBoard the name of the target board name
     */
    public void setTargetBoard(String otherBoard) {
        targetBoardName=otherBoard;
    }

}
