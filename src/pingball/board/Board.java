package pingball.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import physics.Geometry.VectPair;
import pingball.proto.BallMessage;
import pingball.proto.ConnectWallMessage;
import pingball.proto.DisconnectWallMessage;
import pingball.proto.Message;

/**
 * 
 */
public class Board {
    public static final int DEFAULT_SIZE = 20;
    private final int height;
    private final int width;
    private final List<Ball> balls;
    private final HashMap<String, Gadget> boardGadgets;
    private final String name;    
    private Vect g; // In L / ms^2
    private double mu; // In per s.
    private double mu2; // In per L.

    /**
     * Creates a new instance of Board.
     * 
     * @param name
     * @param balls
     * @param gadgets
     */
    public Board(String name, List<Ball> balls,
            List<Gadget> gadgets, double gravity, double friction1,
            double friction2) {
        this.name = name;
        this.g = new Vect(0, gravity / (1000 * 1000)); // L / ms^2

        this.mu = friction1;
        this.mu2 = friction2;
        this.balls = balls;
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        boardGadgets = new HashMap<String, Gadget>();
        for (Gadget gadget : gadgets) {
            Vect pos = new Vect(gadget.getX(),gadget.getY());
            boardGadgets.put(pos.toString(), gadget);
        }
        // add corner walls to gadgets
//        Gadget cornerNE = new OuterWallPart(this, true, new Vect(20, -1), '.');
//        boardGadgets.put(cornerNE.getPosition().toString(), cornerNE);
//        Gadget cornerSE = new OuterWallPart(this, true, new Vect(20, 20), '.');
//        boardGadgets.put(cornerSE.getPosition().toString(), cornerSE);
//        Gadget cornerNW = new OuterWallPart(this, true, new Vect(-1, -1), '.');
//        boardGadgets.put(cornerNW.getPosition().toString(), cornerNW);
//        Gadget cornerSW = new OuterWallPart(this, true, new Vect(-1, 20), '.');
//        boardGadgets.put(cornerSW.getPosition().toString(), cornerSW);
        
        /*walls = new OuterWalls();
        boardGadgets.put(walls.getName(), walls);
        for (OuterWalls w : walls)
            reset(w);
        */
    }

    /**
     * Adds a gadget to the board
     * 
     * @param gadget to be added to board
     */
    public void addGadget(Gadget gadget) {
        boardGadgets.put(gadget.getName(), gadget);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < 22; y++) {
            for(int x = 0; x < 22;x++)
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
/*
    public synchronized void go() {
        int j = 0;
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            this.balls.set(j, b.move());
            j++;
        }

        int k = 0;
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            this.balls.set(k, this.gravity(b));
            k++;
        }

        int m = 0;
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            this.balls.set(m, this.friction(b));
            m++;
        }

        int n = 0;
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            if (this.contactTest(b) != null) {
                this.balls.set(n, this.contactTest(b));
            } else {
                this.balls.remove(n);
            }
            n++;
        }
    }*/

   /* private synchronized Ball gravity(Ball b) {
        double t = 50d; // In milliseconds. This should be equal to the thread
                        // refresh rate.
        double u = b.getVelocity().y();
        double a = g.y();
        double v = u + a * t;
        return new Ball(b.nameb.getCenter(), new Vect(b.getVelocity().x(), v));
    }

    private synchronized Ball friction(Ball b) {
        double deltaT = 10d / 1000d; // Should be equal to the thread refresh
                                     // rate but in seconds.
        Vect oldV = b.getVelocity();
        double speed = Math.sqrt(Math.pow(oldV.x(), 2) + Math.pow(oldV.y(), 2));
        double scalingFactor = 1 - this.mu * deltaT - this.mu2 * speed * deltaT;
        Vect vNew = oldV.times(scalingFactor);
        return new Ball(b.getCenter(), vNew);
    }*/

    public synchronized boolean hasName() {
        return name.length() > 0;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized String stringify(String s) {
        if (s.length() > 20)
            return s.substring(0, 20);
        StringBuffer name = new StringBuffer();
        for (int i = 0; i < (20 - s.length()) / 2; i++)
            name = name.append(".");
        name = name.append(s);
        for (int i = 0; i < (20 - s.length() + 1) / 2; i++)
            name = name.append(".");
        return name.toString();
    }
/*
    public synchronized void remove(String direction) {
        String defolt = "....................";
        if (direction.equals("left")) {
            leftBoard = defolt;
            walls[2].reset("LEFT");
            reset(walls[2]);
        } else if (direction.equals("right")) {
            rightBoard = defolt;
            walls[3].reset("RIGHT");
            reset(walls[3]);
        } else if (direction.equals("top")) {
            topBoard = defolt;
            walls[0].reset("TOP");
            reset(walls[0]);
        } else if (direction.equals("bottom")) {
            bottomBoard = defolt;
            walls[1].reset("BOTTOM");
            reset(walls[1]);
        } else {
            throw new RuntimeException(
                    "A direction other than left, right, top, or bottom was specified... Something wrong happened!");
        }
    }

    public synchronized void add(String direction, String name) {
        if (direction.equals("left")) {
            leftBoard = name;
            walls[2].reset(false, "LEFT", stringify(name));
            reset(walls[2]);
        } else if (direction.equals("right")) {
            rightBoard = name;
            walls[3].reset(false, "RIGHT", stringify(name));
            reset(walls[3]);
        } else if (direction.equals("top")) {
            topBoard = name;
            walls[0].reset(false, "TOP", stringify(name));
            reset(walls[0]);
        } else if (direction.equals("bottom")) {
            bottomBoard = name;
            walls[1].reset(false, "BOTTOM", stringify(name));
            reset(walls[1]);
        } else {
            throw new RuntimeException(
                    "A direction other than left, right, top, or bottom was specified... Something wrong happened!");
        }
    }

    private synchronized void reset(OuterWall outerWall) {
        for (OuterWallPart p : outerWall.getParts())
            boardGadgets.put(p.getPosition().toString(), p);
    }

    public synchronized String getNeighbors() {
        return name + " left: " + leftBoard + " right: " + rightBoard
                + " top: " + topBoard + " bottom: " + bottomBoard;
    }*/

    /*private synchronized Ball contactTest(Ball b) {
        // Loop through all the gadgets and find the min time-before-collision
        // among all of them.
        HashMap<String, Gadget> allGadgets = new HashMap<String, Gadget>();
        allGadgets.putAll(this.boardGadgets);
        Gadget minTimeGadget = null;
        double finalMinTime = Double.POSITIVE_INFINITY;
        String side = "";
        Iterator itGadget = allGadgets.entrySet().iterator();
        while (itGadget.hasNext()) {
            Map.Entry pairsGadget = (Map.Entry) itGadget.next();
            String coords = (String) pairsGadget.getKey();
            Gadget g = (Gadget) pairsGadget.getValue();
            // There's just one key-value pair in the following hashmap.
            HashMap<String, Double> timeMap = g.leastCollisionTime(b,
                    b.getVelocity());
            HashMap<String, Double> allTimes = timeMap;
            Iterator itComponents = allTimes.entrySet().iterator();
            while (itComponents.hasNext()) {
                Map.Entry pairsComponents = (Map.Entry) itComponents.next();
                String identifier = (String) pairsComponents.getKey();
                double time = (double) pairsComponents.getValue();
                if (time <= finalMinTime) {
                    finalMinTime = time;
                    minTimeGadget = g;
                    side = identifier;
                }
                itComponents.remove(); // avoids a
                                       // ConcurrentModificationException
            }
            itGadget.remove(); // avoids a ConcurrentModificationException
        }
        Boolean bounceABall = false;
        Ball bounceFirst = null;
        // Do the same thing for all the balls.
        for (Ball aBall : this.balls) {
            HashMap<String, Double> times = aBall.leastCollisionTime(b);
            double ballTime = times.get("BALL");
            if (ballTime <= finalMinTime) {
                finalMinTime = ballTime;
                bounceABall = true;
                bounceFirst = aBall;
            }
        }

        double epsilon = 1.0d;
        if (bounceABall) {
            epsilon = 1.0d;
        } else {
            switch (minTimeGadget.getType()) {

            case "BUMPER":
                Bumper bump = (Bumper) minTimeGadget;
                if (bump.getBumperType() == "SQUARE") {
                    epsilon = 0.3d;
                } else if (bump.getBumperType() == "CIRCLE") {
                }
                break;

            case "OUTER_WALL_PART":
                epsilon = 0.5d;
                break;

            case "FLIPPER":
                epsilon = 1.0d;
                break;

            case "ABSORBER":
                epsilon = 0.05d;
                break;
            }
        }

        if (finalMinTime < epsilon) {
            if (bounceABall) {
                return bounceFirst.bounce(b);
            }
            return minTimeGadget.bounce(b, side);
        }
        return b;
    }*/

    /**
     * Adds a ball to the board
     * 
     * @param ball to be added to the board
     */
    public synchronized void addBall(Ball ball) {
        balls.add(ball);
    }
    
    public void removeBall(Ball ball) {
        balls.remove(ball);
    }
    
    /**
     * Returns shortest time until the next collision event will happen
     * @return the shortest time until next collision
     */
    public synchronized double timeUntilCollision() { 
        double shortestTime = Double.MAX_VALUE;
        // gadget-ball collisions
        for (Gadget gadget: boardGadgets.values()){
            for (Ball ball: balls){
                double time = gadget.leastCollisionTime(ball);
                if (time == 0) continue;
                if (time < shortestTime)
                    shortestTime = time;
            }
        }
        // ball-ball collisions
        for (Ball ball1 : balls) {
            for (Ball ball2 : balls){
                if (ball1 == ball2) continue;
                double time = Geometry.timeUntilBallBallCollision(ball1.getCircle(), ball1.getVelocity(), ball2.getCircle(), ball2.getVelocity());
                if (time < shortestTime)
                    shortestTime = time;
            }
        }
        return shortestTime;
    }
    
    /**
     * For every ball, simulate a physics time increment for every ball with no collisions.
     * @param time the time by which to increment, time should be very small
     */
    public synchronized void incrementNoCollisionTime(double time) {
        for (Ball ball : balls) {
            Vect oldV = ball.getVelocity();            
            Vect newV = oldV.times(1-this.mu*time-this.mu2*oldV.length()*time).plus(this.g.times(time));
            if (newV.length() < 0.05) // If velocity is too small, kill it
                newV = new Vect(0, 0);
            ball.changeVelocity(newV);
            ball.changePos(ball.getPos().plus(oldV.times(time)));
        }
    }
    
    /**
     * Simulates running of the given time
     * @param time the time in seconds for which to run
     */
    public synchronized void simulateTime(double timeLeft) {
        
        boolean isColliding = true;
        double originalTime = timeLeft;

        while (isColliding) {            
            
            double time = this.timeUntilCollision();
            if (time < originalTime*0.05)// If time is too small then make it bigger
                time = originalTime*0.05; 
            
            if (time < timeLeft){
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
    }
    
    /**
     * Simulates a collision with next ball and mutates ball with the new parameters
     */
     public synchronized void reflect() {
         double shortestTime = Double.MAX_VALUE;
         int shortestIndexGadget = -1;
         int shortestIndexBall = -1;
         int shortestIndexBall2 = -1;
         List<Gadget> gadgets = new ArrayList<Gadget>(boardGadgets.values());
         // gadget-ball collisions
         for (int i = 0; i < gadgets.size(); i++)
             for (int j = 0; j < balls.size(); j++){
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
                 double time = Geometry.timeUntilBallBallCollision(ball1.getCircle(), ball1.getVelocity(), ball2.getCircle(), ball2.getVelocity());
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
                 VectPair vels = Geometry.reflectBalls(ball1.getPos(), 1, ball1.getVelocity(), ball2.getPos(), 1, ball2.getVelocity());
                 ball1.changeVelocity(vels.v1);
                 ball2.changeVelocity(vels.v2);
             }
         else
             gadgets.get(shortestIndexGadget).reactBall(balls.get(shortestIndexBall));
     }
     
    
    /**
     * Handles a message received from the server.
     * 
     * @param message the message received from the server.
     */
    public synchronized void onMessage(Message message) {
        if (message instanceof ConnectWallMessage) {
            ConnectWallMessage wallMessage = (ConnectWallMessage)message;
            OuterWall wall = findWall(wallMessage.getEdge());
            wall.setNeighborName(wallMessage.getNeighborName());
        } else if (message instanceof DisconnectWallMessage) {
            DisconnectWallMessage wallMessage = (DisconnectWallMessage)message;
            OuterWall wall = findWall(wallMessage.getEdge());
            wall.setNeighborName(null);         
        } else if (message instanceof BallMessage) {
            BallMessage ballMessage = (BallMessage)message;
            Circle shape = ballMessage.getShape();
            Vect center = shape.getCenter();
            Vect velocity = ballMessage.getVelocity();
            Ball ball = new Ball("teleported-ball", center, velocity);          
            addBall(ball);
        }
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
            OuterWall wall = (OuterWall)gadget;
            if (wall.getEdge() == edge)
                return wall;
        }
        assert false;  // The board should have walls on all four sides.
        return null;
    }
    
    /**
     * This will find the balls that are outside of the bounds.
     * 
     * The balls that are out of bounds are also removed from the board.
     * 
     * @return a list of messages that should be sent to the server documenting
     *   out of bounds balls
     */
    public synchronized List<Message> getOutOfBoundBallMessages(){
        List<Message> messages = new ArrayList<Message>();
        List<Ball> removedBalls = new ArrayList<Ball>();
        for (Ball ball: balls) {
            Vect position = ball.getPos();
            double x = position.x();
            double y = position.y();
            
            Vect velocity = ball.getVelocity();
            double vx = velocity.x();
            double vy = velocity.y();
            
            // HACK: Guess the edge that the ball crossed. This should have
            //       really been handled by the walls.
            Edge edge = null;
            if (x < 0 && vx < 0)
                edge = Edge.LEFT;
            else if (x > 20 && vx > 0)
                edge = Edge.RIGHT;
            else if (y < 0 && vy < 0)
                edge = Edge.TOP;
            else if(y > 20 && vy > 0)
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
                BallMessage message = new BallMessage(edge,
                        new Circle(x,  y, r), velocity);
                messages.add(message);
                
                removedBalls.add(ball);
            }               
        }
        for (Ball ball : removedBalls) {
            balls.remove(ball);
        }
        return messages;
    }
    
    /**
     * Gets a gadget from the board with the given name.
     * If does not exists, returns null
     * @param name the name of the gadget to find.
     * @return
     */
    public Gadget getGadgetFromName(String name) {
        return boardGadgets.get(name);
    }
    
    /**
     * Gets a board string index (row major) from position.
     * @param position the position to convert to index
     * @return the index in board string
     */
    public static int getBoardStringIndexFromVect(Vect position) {
        return ((int)position.y() + 1) * (DEFAULT_SIZE + 3) + (int)position.x() + 1;
    }
    
}
