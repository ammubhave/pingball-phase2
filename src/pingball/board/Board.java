package pingball.board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import physics.Vect;
import server.PingballClient;
import utilities.Coords;

/**
 * @author Julia
 * 
 */
public class Board {
    private static final int DEFAULT_SIZE = 20;
    private final PingballClient client;
    private final int height;
    private final int width;
    private final List<Ball> balls;
    private final HashMap<String, Gadget> boardGadgets;
    private final String name;
    private OuterWall[] walls;
    private Vect g; // In L / ms^2
    private double mu; // In per s.
    private double mu2; // In per L.
    private String leftBoard, rightBoard, topBoard, bottomBoard;

    /**
     * Creates a new instance of Board.
     * 
     * @param client
     * @param name
     * @param balls
     * @param gadgets
     */
    public Board(PingballClient client, String name, List<Ball> balls,
            List<Gadget> gadgets, double gravity, double friction1,
            double friction2) {
        this.client = client;
        this.name = name;
        this.g = new Vect(0, gravity / (1000 * 1000)); // L / ms^2

        this.mu = friction1;
        this.mu2 = friction2;
        this.balls = balls;
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        boardGadgets = new HashMap<String, Gadget>();
        for (Gadget gadget : gadgets) {
            // //System.out.println("Adding");
            boardGadgets.put(gadget.getPosition().toString(), gadget);
        }
        // add corner walls to gadgets
        Gadget cornerNE = new OuterWallPart(this, true, new Coords(20, -1), '.');
        boardGadgets.put(cornerNE.getPosition().toString(), cornerNE);
        Gadget cornerSE = new OuterWallPart(this, true, new Coords(20, 20), '.');
        boardGadgets.put(cornerSE.getPosition().toString(), cornerSE);
        Gadget cornerNW = new OuterWallPart(this, true, new Coords(-1, -1), '.');
        boardGadgets.put(cornerNW.getPosition().toString(), cornerNW);
        Gadget cornerSW = new OuterWallPart(this, true, new Coords(-1, 20), '.');
        boardGadgets.put(cornerSW.getPosition().toString(), cornerSW);
        OuterWall topWall = new OuterWall(this, "TOP");
        OuterWall bottomWall = new OuterWall(this, "BOTTOM");
        OuterWall leftWall = new OuterWall(this, "LEFT");
        OuterWall rightWall = new OuterWall(this, "RIGHT");
        walls = new OuterWall[] { topWall, bottomWall, leftWall, rightWall };
        for (OuterWall w : walls)
            reset(w);
        leftBoard = null;
        rightBoard = null;
        topBoard = null;
        bottomBoard = null;
    }
    
    public void addGadget(Gadget g){
        boardGadgets.put(gadget.getPosition().toString(), gadget);
}

    @Override
    public String toString() {
        String[][] board = new String[this.height + 2][this.width + 2]; // Array
                                                                        // of
                                                                        // rows.
        for (int i = 0; i <= height + 1; i++) { // i is y coordinates
            for (int j = 0; j <= width + 1; j++) { // j is x coordinates
                Coords coords = new Coords(j - 1, i - 1);
                String currentCoords = coords.toString();
                boolean isABall = false;
                for (int k = 0; k < balls.size(); k++) {
                    if (Math.round(balls.get(k).getX()) == j - 1
                            && Math.round(balls.get(k).getY()) == i - 1) {
                        board[i][j] = balls.get(k).toString();
                        isABall = true;
                        break; // TODO Should this really be here? What if there
                               // are multiple balls?
                    }
                }
                if ((!isABall) && (board[i][j] != "=")) {
                    if (boardGadgets.get(currentCoords) != null) {
                        if (boardGadgets.get(currentCoords).getType() == "ABSORBER") {
                            // This is an absorber. This encompasses multiple
                            // levels.
                            Absorber absorb = (Absorber) boardGadgets
                                    .get(currentCoords);
                            for (int n = j; n <= j + absorb.getWidth(); n++) {
                                for (int p = i; p <= i + absorb.getHeight(); p++) {
                                    /*
                                     * n => x-coordinate p => y-coordinate
                                     */
                                    board[p][n] = absorb.toString();
                                }
                            }
                        } else {
                            board[i][j] = boardGadgets.get(currentCoords)
                                    .toString();
                        }
                    } else {
                        board[i][j] = " ";
                    }
                }
            }
        }
        String boardString = "";
        for (int k = 0; k <= height + 1; k++) {
            for (int m = 0; m <= width + 1; m++) {
                boardString += board[k][m];
            }
            boardString += "\r\n";
        }
        return boardString;
    }

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
    }

    private synchronized Ball gravity(Ball b) {
        double t = 50d; // In milliseconds. This should be equal to the thread
                        // refresh rate.
        double u = b.getVelocity().y();
        double a = g.y();
        double v = u + a * t;
        return new Ball(b.getCenter(), new Vect(b.getVelocity().x(), v));
    }

    private synchronized Ball friction(Ball b) {
        double deltaT = 10d / 1000d; // Should be equal to the thread refresh
                                     // rate but in seconds.
        Vect oldV = b.getVelocity();
        double speed = Math.sqrt(Math.pow(oldV.x(), 2) + Math.pow(oldV.y(), 2));
        double scalingFactor = 1 - this.mu * deltaT - this.mu2 * speed * deltaT;
        Vect vNew = oldV.times(scalingFactor);
        return new Ball(b.getCenter(), vNew);
    }

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
    }

    private synchronized Ball contactTest(Ball b) {
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
            HashMap<String, Double> times = aBall.leastCollisionTime(b,
                    b.getVelocity());
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
    }

    public synchronized void addBall(Ball ball) {
        balls.add(ball);
    }

    public void sendBall(Ball b, String s) {
        if (s.equals("left"))
            client.sendBall(b, leftBoard);
        else if (s.equals("right"))
            client.sendBall(b, rightBoard);
        else if (s.equals("top"))
            client.sendBall(b, topBoard);
        else if (s.equals("bottom"))
            client.sendBall(b, bottomBoard);
    }
}
