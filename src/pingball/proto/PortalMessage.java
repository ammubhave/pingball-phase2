package pingball.proto;

import java.awt.geom.Point2D;

import physics.Circle;
import physics.Vect;

public class PortalMessage extends Message {

    /** The board edge neighboring the wall that was connected. */
    private final String targetPortal;
    /** The center and radius of the ball that is teleported. */
    private final String targetBoard;
    /** The velocity of the ball that is teleported. */
    private final Vect velocity;

    private final Circle ballShape;
    private final String name;
    private final String source;

    public PortalMessage(String name, String targetPortal, String targetBoard,
            Circle ballShape, Vect velocity, String source) {
        this.name = name;
        this.targetPortal = targetPortal;
        this.targetBoard = targetBoard;
        this.ballShape = ballShape;
        this.velocity = velocity;
        this.source = source;
    }

    public String getName() {
        return name;
    }
    
    public String getSource() {
        return this.source;
    }

    public String getTargetPortal() {
        return targetPortal;
    }

    public String getTargetBoard() {
        return targetBoard;
    }

    public Circle getBallShape() {
        return ballShape;
    }

    public Vect getVelocity() {
        return velocity;
    }

    @Override
    protected String name() {
        return NAME;
    }

    @Override
    public String toLine() {
        return NAME + " " + targetPortal + " " + targetBoard + " "+ ballShape.getCenter().x() + " " + ballShape.getCenter().y() + " " +
                ballShape.getRadius() + " " + velocity.x() + " " + velocity.y()
                + " " + name + " " + source;
    }

    static final String NAME = "portal";
    
    /**
     * Creates a message from a line of text received from a socket.
     * @param tokens strings that were separated by spaces on the line
     */
    PortalMessage(String[] tokens) {
        try{
            this.targetPortal=tokens[1];
            this.targetBoard=tokens[2];
            this.name=tokens[6];
            this.velocity= new Vect(Double.parseDouble(tokens[4]),Double.parseDouble(tokens[5]));
            this.source = tokens[7];
            double cx = Double.parseDouble(tokens[3]);
            double cy = Double.parseDouble(tokens[4]);
            double radius = Double.parseDouble(tokens[5]);
            this.ballShape = new Circle(new Point2D.Double(cx, cy), radius);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid version number", e);
        }
        
    }

}
