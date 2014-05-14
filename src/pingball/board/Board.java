package pingball.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.Vect;
import pingball.proto.BallMessage;
import pingball.proto.ConnectWallMessage;
import pingball.proto.DisconnectWallMessage;
import pingball.proto.Message;
import pingball.proto.PortalMessage;
import pingball.ui.board.BallPainter;
import pingball.ui.board.GadgetPainter;

/**
 * 
 */
public class Board {
    /*
     * Thread Safety:
     * All public methods are either on immutable objects or synchronized, does not create new threads
     */
    
    public static final int DEFAULT_SIZE = 20;

    private final List<Ball> balls = new ArrayList<Ball>();
    private final HashMap<String, Gadget> boardGadgets = new HashMap<String, Gadget>();
    private final List<GadgetPainter> boardGadgetPainters  = new ArrayList<GadgetPainter>();
    
    private final String name;
    private final Vect g; // In L / s^2
    private final double mu; // In per s.
    private final double mu2; // In per L.
    
    private HashMap<String, ArrayList<Gadget>> keyUpForGadgets = new HashMap<String, ArrayList<Gadget>>();
    private HashMap<String, ArrayList<Gadget>> keyDownForGadgets = new HashMap<String, ArrayList<Gadget>>();
    
    private List<Message> sendMessages = new ArrayList<Message>();
    
    /*
     * Rep Invariant:
     * - everything should be non-null
     */
    public void checkRep() {
        assert balls != null;
        assert boardGadgets != null;
        assert boardGadgetPainters != null;
        assert name != null;
        assert keyUpForGadgets != null;
        assert keyDownForGadgets != null;
        assert sendMessages != null;
    }

    /**
     * Creates a new instance of Board.
     * 
     * @param name the name of the board
     * @param gravity the gravity in the board
     * @param friction1 the friction coeff 1 in the board
     * @param friction2 the friction coeff 2 in the board
     */
    public Board(String name, double gravity, double friction1, double friction2) {
        this.name = name;
        this.g = new Vect(0, gravity); // L / s^2

        this.mu = friction1;
        this.mu2 = friction2;
        
        checkRep();
    }

    /**
     * Adds a gadget to the board
     * @param gadget to be added to board
     */
    public synchronized void addGadget(Gadget gadget) {
        boardGadgets.put(gadget.getName(), gadget);
        checkRep();
    }

    /**
     * Adds a gadget painter to the board
     * @param gadget painter to be added to board
     */
    public synchronized void addGadgetPainter(GadgetPainter gadgetPainter) {
        boardGadgetPainters.add(gadgetPainter);
        checkRep();
    }

    /**
     * Gets all the painters associated with this board
     * @return all gadgets painters
     */
    public synchronized List<GadgetPainter> getGadgetPainters() {
        return new ArrayList<GadgetPainter>(this.boardGadgetPainters);
    }

    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 22; y++) {
            for (int x = 0; x < 22; x++)
                sb.append(' ');
            sb.append('\n');
        }
        String boardString = sb.toString();
        for (Ball ball : this.balls) {
            boardString = ball.render(boardString);
        }
        for (Gadget gadget : this.boardGadgets.values()) {
            boardString = gadget.render(boardString);
        }
        return boardString;
    }

    /**
     * Gets the name of the board
     * @return the name of the board
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a ball to the board
     * @param ball  to be added to the board
     */
    public synchronized void addBall(Ball ball) {
        balls.add(ball);
        checkRep();
    }

    /**
     * Removes ball from board
     * @param ball the ball to remove
     */
    public synchronized void removeBall(Ball ball) {
        balls.remove(ball);
        checkRep();
    }

    /**
     * Returns shortest time until the next collision event will happen
     * @return the shortest time until next collision
     */
    public synchronized double timeUntilCollision() {
        double shortestTime = Double.MAX_VALUE;
        // gadget-ball collisions
        for (Gadget gadget : boardGadgets.values()) {
            for (Ball ball : balls) {
                double time = gadget.leastCollisionTime(ball);
                if (time == 0)
                    continue;
                if (time < shortestTime)
                    shortestTime = time;
            }
        }
        // ball-ball collisions
        for (Ball ball1 : balls) {
            for (Ball ball2 : balls) {
                if (ball1 == ball2)
                    continue;
                double time = Geometry.timeUntilBallBallCollision(ball1.getCircle(), ball1.getVelocity(),
                        ball2.getCircle(), ball2.getVelocity());
                if (time < shortestTime)
                    shortestTime = time;
            }
        }
        return shortestTime;
    }

    /**
     * For every ball, simulate a physics time increment for every ball with no
     * collisions.
     * @param time the time by which to increment, time should be very small
     */
    public synchronized void incrementNoCollisionTime(double time) {
        for (Ball ball : balls) {
            Vect oldV = ball.getVelocity();
            Vect newV = oldV.times(1 - this.mu * time - this.mu2 * oldV.length() * time).plus(this.g.times(time));
           // if (newV.length() < 0.05) // If velocity is too small, kill it
           //     newV = new Vect(0, 0);
            ball.changeVelocity(newV);
            ball.changePos(ball.getPos().plus(oldV.times(time)));
        }
        checkRep();
    }

    /**
     * Simulates running of the given time
     * 
     * @param time
     *            the time in seconds for which to run
     */
    public synchronized void simulateTime(double timeLeft) {
        boolean isColliding = true;
        double originalTime = timeLeft;

        while (isColliding) {
            double time = this.timeUntilCollision();
            if (time < originalTime * 0.05)// If time is too small then make it
                                           // bigger
                time = originalTime * 0.05;

            if (time < timeLeft) {
                isColliding = true;
            } else {
                isColliding = false;
            }

            if (isColliding) {
                this.reflect();
                timeLeft = timeLeft - time;
            } else {
                this.incrementNoCollisionTime(timeLeft);
            }
        }
        checkRep();
    }

    /**
     * Simulates a collision with next ball and mutates ball with the new
     * parameters
     */
    public synchronized void reflect() {
        double shortestTime = Double.MAX_VALUE;
        int shortestIndexGadget = -1;
        int shortestIndexBall = -1;
        int shortestIndexBall2 = -1;
        List<Gadget> gadgets = new ArrayList<Gadget>(boardGadgets.values());
        // gadget-ball collisions
        for (int i = 0; i < gadgets.size(); i++)
            for (int j = 0; j < balls.size(); j++) {
                double time = gadgets.get(i).leastCollisionTime(balls.get(j));
                if (time < shortestTime) {
                    shortestTime = time;
                    shortestIndexGadget = i;
                    shortestIndexBall = j;
                }
            }
        // ball-ball collisions
        for (int i = 0; i < balls.size(); i++) {
            Ball ball1 = balls.get(i);
            for (int j = 0; j < balls.size(); j++) {
                Ball ball2 = balls.get(j);
                if (ball1 == ball2)
                    continue;
                double time = Geometry.timeUntilBallBallCollision(ball1.getCircle(), ball1.getVelocity(),
                        ball2.getCircle(), ball2.getVelocity());
                if (time < shortestTime) {
                    shortestIndexGadget = -1;
                    shortestIndexBall = i;
                    shortestIndexBall2 = j;
                }
            }
        }

        // perform collision
        if (shortestIndexGadget == -1)
            if (shortestIndexBall == -1)
                return;
            else {
                Ball ball1 = balls.get(shortestIndexBall);
                Ball ball2 = balls.get(shortestIndexBall2);
                VectPair vels = Geometry.reflectBalls(ball1.getPos(), 1, ball1.getVelocity(), ball2.getPos(), 1,
                        ball2.getVelocity());
                ball1.changeVelocity(vels.v1);
                ball2.changeVelocity(vels.v2);
            }
        else {
            List<Message> msgs = gadgets.get(shortestIndexGadget).reactBall(balls.get(shortestIndexBall));
            for (Message msg : msgs) {
                if (msg instanceof PortalMessage) {
                    boolean toadd = true;
                    for (Message sm : sendMessages) {
                        if (sm instanceof PortalMessage) {
                            if (((PortalMessage) sm).getName().equals(((PortalMessage) msg).getName()))
                               toadd = false;
                        }
                    }
                    if (toadd && (portalEnabled || ((PortalMessage) msg).getTargetBoard().equals(getName()))) {
                        sendMessages.add(msg);
                    }
                } else {
                    sendMessages.add(msg);
                }
            }
        }
        checkRep();
    }
    
    private boolean portalEnabled = false;
    /**
     * Enables all inter-boards portals on this board
     */
    public synchronized void enablePortal() {
        portalEnabled = true;
    }
    /**
     * Disables all inter-boards portals on this board
     */
    public synchronized void disablePortal() {
        portalEnabled = false;
    }


    /**
     * Handles a message received from the server.
     * 
     * @param message the message received from the server.
     */
    public synchronized void onMessage(Message message) {
        if (message instanceof ConnectWallMessage) {
            ConnectWallMessage wallMessage = (ConnectWallMessage) message;
            OuterWall wall = findWall(wallMessage.getEdge());
            wall.setNeighborName(wallMessage.getNeighborName());
        } else if (message instanceof DisconnectWallMessage) {
            DisconnectWallMessage wallMessage = (DisconnectWallMessage) message;
            OuterWall wall = findWall(wallMessage.getEdge());
            wall.setNeighborName(null);
        } else if (message instanceof BallMessage) {
            BallMessage ballMessage = (BallMessage) message;
            Circle shape = ballMessage.getShape();
            Vect center = shape.getCenter();
            Vect velocity = ballMessage.getVelocity();
            Ball ball = new Ball(ballMessage.getName(), center, velocity);
            addBall(ball);
            this.boardGadgetPainters.add(new BallPainter(ball));
        } else if (message instanceof PortalMessage) {
            PortalMessage portalMessage = (PortalMessage) message;
            Vect position;
            if (portalMessage.getTargetBoard().equals(getName()))
                position = new Vect(((Portal)getGadgetFromName(portalMessage.getTargetPortal())).getX(), ((Portal)getGadgetFromName(portalMessage.getTargetPortal())).getY());
            else
                position = portalMessage.getBallShape().getCenter().plus(portalMessage.getVelocity().times(0.05/200.));
            Ball ball = new Ball(portalMessage.getName(), position,
                    portalMessage.getVelocity());
            addBall(ball);
            this.boardGadgetPainters.add(new BallPainter(ball));
        }
        checkRep();
    }

    /**
     * Returns the wall next to a board edge.
     * 
     * @param edge the board edge
     * @return the wall next to the given board edge
     */
    private synchronized OuterWall findWall(Edge edge) {
        for (Gadget gadget : boardGadgets.values()) {
            if (!(gadget instanceof OuterWall))
                continue;
            OuterWall wall = (OuterWall) gadget;
            if (wall.getEdge() == edge)
                return wall;
        }
        assert false; // The board should have walls on all four sides.
        return null;
    }

    /**
     * This will find the balls that are outside of the bounds.
     * 
     * The balls that are out of bounds are also removed from the board.
     * 
     * @return a list of messages that should be sent to the server documenting
     *         out of bounds balls
     */
    public synchronized List<Message> getOutOfBoundBallMessages() {
        List<Message> messages = new ArrayList<Message>();
        List<Ball> removedBalls = new ArrayList<Ball>();
        for (Ball ball : balls) {
            Vect position = ball.getPos();
            double x = position.x();
            double y = position.y();

            Vect velocity = ball.getVelocity();
            double vx = velocity.x();
            double vy = velocity.y();

            // HACK: Guess the edge that the ball crossed. This should have
            // really been handled by the walls.
            Edge edge = null;
            if (x < 0 && vx < 0)
                edge = Edge.LEFT;
            else if (x > 20 && vx > 0)
                edge = Edge.RIGHT;
            else if (y < 0 && vy < 0)
                edge = Edge.TOP;
            else if (y > 20 && vy > 0)
                edge = Edge.BOTTOM;

            if (edge != null) {
                // HACK: get the ball back on the board for teleporting.
                double r = ball.getCircle().getRadius();
                if (x < 0)
                    x = 0;
                if (y < 0)
                    y = 0;
                if (x > 20)
                    x = 20;
                if (y > 20)
                    y = 20;
                BallMessage message = new BallMessage(ball.getName(), edge, new Circle(x, y, r), velocity);
                messages.add(message);

                removedBalls.add(ball);
            }
        }
        for (Message msg : sendMessages) {
            if (msg instanceof PortalMessage) {
                removedBalls.add(getBallFromName(((PortalMessage)msg).getName()));
                messages.add(new PortalMessage(((PortalMessage) msg).getName(), ((PortalMessage) msg).getTargetPortal(), ((PortalMessage) msg).getTargetBoard(), ((PortalMessage) msg).getBallShape(), ((PortalMessage) msg).getVelocity(), getName()));
            } else {
                messages.add(msg);
            }
        }
        for (Ball ball : removedBalls) {
            balls.remove(ball);
            int i = 0;
            for (i = 0; i < this.boardGadgetPainters.size(); i++)
                if (this.boardGadgetPainters.get(i) instanceof BallPainter)
                    if (((BallPainter) this.boardGadgetPainters.get(i)).getBall().getName() == ball.getName())
                        break;
            this.boardGadgetPainters.remove(i);
        }
        for (Message msg : sendMessages) {
            if (msg instanceof PortalMessage) {
                if (((PortalMessage) msg).getTargetBoard().equals(getName()) && !portalEnabled) {
                    onMessage(msg);
                }
            }
        }
        sendMessages.clear();
        return messages;
    }

    /**
     * Gets a gadget from the board with the given name. If does not exists,
     * returns null
     * 
     * @param name
     *            the name of the gadget to find.
     * @return
     */
    public synchronized Gadget getGadgetFromName(String name) {
        return boardGadgets.get(name);
    }
    
    public synchronized Ball getBallFromName(String name) {
        for (Ball ball : balls) {
            if (ball.getName().equals(name))
                return ball;
        }
        throw new RuntimeException("ball not found");
    }

    public void addKeyUpBinding(String keyName, Gadget gadget) {
        if (!keyUpForGadgets.containsKey(keyName))
            keyUpForGadgets.put(keyName, new ArrayList<Gadget>());
        
        keyUpForGadgets.get(keyName).add(gadget);
        checkRep();
    }

    public void addKeyDownBinding(String keyName, Gadget gadget) {
        if (!keyDownForGadgets.containsKey(keyName))
            keyDownForGadgets.put(keyName, new ArrayList<Gadget>());
        
        keyDownForGadgets.get(keyName).add(gadget);
        checkRep();
    }

    public synchronized void handleKeyUp(String keyName) {
        if (!keyUpForGadgets.containsKey(keyName)) 
            return;
        for (Gadget gadget : keyUpForGadgets.get(keyName)) {
            gadget.action();
        }
    }

    public synchronized void handleKeyDown(String keyName) {
        if (!keyDownForGadgets.containsKey(keyName)) 
            return;
        for (Gadget gadget : keyDownForGadgets.get(keyName)) {
            gadget.action();
        }
    }

    /**
     * Gets a board string index (row major) from position.
     * 
     * @param position
     *            the position to convert to index
     * @return the index in board string
     */
    public static int getBoardStringIndexFromVect(Vect position) {
        return ((int) position.y() + 1) * (DEFAULT_SIZE + 3) + (int) position.x() + 1;
    }

}
