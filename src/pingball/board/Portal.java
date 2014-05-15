package pingball.board;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import pingball.proto.Message;
import pingball.proto.PortalMessage;

/** This class represents the portal gadget. */
public class Portal implements Gadget {
    /**
     * Thread Safety:
     * Everything is immutable.
     * -gadgetsToBeHooked modified in factory only.
     */

    // specified in spec
    private final static double RADIUS = 0.5;

    private final String name;
    private final Vect position;
    private final Circle portal;
    private List<Gadget> gadgetsToBeHooked = new ArrayList<Gadget>();
    private final String targetPortalName;
    private final String targetBoardName;

    /*
     * Rep Invariant: - Everything is non-null;
     */
    private void checkRep() {
        assert name != null;
        assert position != null;
        assert portal != null;
        assert gadgetsToBeHooked != null;
        assert targetPortalName != null;
        assert targetBoardName != null;
    }

    public Portal(Vect position, String name, String targetPortalName, String targetBoardName) {
        this.name = name;
        this.position = position;
        double centerX = position.x() + RADIUS;
        double centerY = position.y() + RADIUS;
        portal = new Circle(centerX, centerY, RADIUS);
        this.targetPortalName = targetPortalName;
        this.targetBoardName = targetBoardName;

        checkRep();
    }

    @Override
    public void trigger() {
        GadgetHelpers.callActionOnGadgets(gadgetsToBeHooked);
    }

    @Override
    public double leastCollisionTime(Ball ball) {
        Vect velocity = ball.getVelocity();
        return Geometry.timeUntilCircleCollision(new Circle(portal.getCenter().x(), portal.getCenter().y(), RADIUS),
                ball.getCircle(), velocity);
    }

    @Override
    public Message reactBall(Ball ball) {
        if (this.targetPortalName == null) {
            return null;
        }

        PortalMessage message = new PortalMessage(ball.getName(), this.targetPortalName, this.targetBoardName,
                ball.getCircle(), ball.getVelocity(), null);
        return message;
    }

    @Override
    public void action() {
    }

    @Override
    public double getX() {
        return portal.getCenter().x();
    }

    @Override
    public double getY() {
        return portal.getCenter().y();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void hookActionToTrigger(Gadget gadget) {
        gadgetsToBeHooked.add(gadget);

        checkRep();
    }

    @Override
    public String render(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(Board.getBoardStringIndexFromVect(this.position), '@');
        return sb.toString();
    }
}
