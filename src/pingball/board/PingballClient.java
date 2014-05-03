package pingball.board;

/*
 * Handles individual clients
 * QUESTION: how to incorporate hostname? Yeah uhh why is this a thing
 */

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import physics.*;

/**
 * Thread Safety Argument:
 *      All instance variables should final, which ensures concrete references.
 *      socket is never passed to any other threads, and is never modified by the server. 
 *      board should be thread-safe because of safety implementations in the Board class. 
 *      players is thread-safe because it is synchronized during all accesses and
 *      modifications. 
 */
public class PingballClient implements Runnable{
    private final Socket socket;
    private final Board board; 
    private final boolean singlePlayer;
    
    private final Set<Ball> balls;
    private final Timer timer = new Timer();
    private final Map<String, String> connectedSides = new HashMap<String, String>();    
    private final Map<Gadget, Set<Gadget>> fires;
    private final double m1;
    private final double m2;
    private final double g; 
    
    private final static int[][] DISPLACE = new int[][]{{-1, 0, 1, -1, 0, 1, -1, 0, 1}, {-1, -1, -1, 0, 0, 0, 1, 1, 1}};
    private final static int DISPLACE_NUM = 9;
    
    private final BufferedReader in;
    private final PrintWriter out;
    
    public PingballClient(String hostname, int port, Board board, boolean singlePlayer) throws IOException{
        this.board = board;
        this.singlePlayer = singlePlayer;
        connectedSides.put("top", "");
        connectedSides.put("bottom", "");
        connectedSides.put("left", "");
        connectedSides.put("right", "");
        
        if (singlePlayer){
            this.socket = null;
            in = null;
            out = null;
        } else {
            System.out.println("making socket");
            this.socket = new Socket(hostname, port);
            Thread listener = new Thread(new Runnable() {
                public void run(){
                    try {
                        handleServer(socket);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            listener.start();
        }
        m1 = board.getMuOne();
        m2 = board.getMuTwo();
        g = board.getGravity(); 
        balls = board.getBallsFromFile();       
        for (Ball ball: balls){
            ball.changeEnvironment(m1, m2, g);
        }
        fires = board.getFireMap();
    }

    public void handleServer(Socket socket) throws IOException{
        // also edit outer walls and edit their toString. and all of the gadget's toStrings.
        // also absorber and flipper
        
        //this needs to be here
        System.out.println("about to send boardName");
        out.println("--addBoard " + board.getName());
        
        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println("What I received: " + line);
                String[] tokens = line.split(" ");
                if (tokens.length == 0){
                    continue;
                }
                if (tokens[0].equals("--sendBall") && tokens.length == 7) {
                    String fromClient = tokens[1];
                    String toClient = tokens[2];
                    double xPos = Double.parseDouble(tokens[3]);
                    double yPos = Double.parseDouble(tokens[4]);
                    double xVel = Double.parseDouble(tokens[5]);
                    double yVel = Double.parseDouble(tokens[6]);
                    Ball newBall = new Ball(xPos, yPos, xVel, yVel);
                    newBall.changeEnvironment(m1, m2, g);
                    //check orientation and where the ball came from to determine its new position, then place it in that position 
                    //and add it to ball set 
                    synchronized(balls){
                        balls.add(newBall);
                        System.out.println(balls.size());
                    }
                } else if (tokens[0].equals("--joinBoard") || tokens[0].equals("--unjoinBoard")) {
                    System.out.println("I got a message!\n LOOK \n LOOK \n LOOK");
                    String you = board.getName();
                    String them = "";
                    String first = "";
                    if (tokens[1].equals(you)){
                        them = tokens[2];
                        first = you;
                    } else {
                        them = tokens[1];
                        first = them;
                    }
                    
                    String store;
                    if (tokens[0].equals("--joinBoard")){
                        store = them;
                    } else {
                        store = "";
                    }
                    
                    if (tokens[3].equals("v") && first == them){
                        connectedSides.put("top", store);
                        board.changeOuterVisibility("top", store);
                    } else if (tokens[3].equals("v") && first == you){
                        connectedSides.put("bottom", store);
                        board.changeOuterVisibility("bottom", store);
                    } else if (tokens[3].equals("h") && first == them){
                        connectedSides.put("left", store);
                        board.changeOuterVisibility("left", store);
                    } else if (tokens[3].equals("h") && first == you){
                        connectedSides.put("right", store);
                        board.changeOuterVisibility("right", store);
                    }
                    
                } else {
                    continue;
                }
            }
        } finally {
            out.close();
            in.close();
        }
        
    }
    
    public void run(){
        //balls.add(new Ball(4.0, 8.0, 0, 50));
        try {
            TimerTask taskMove = new TimerTask() {
                public void run(){
                    synchronized(balls){
                        for (Ball ball: balls){
                            int x = (int) (ball.getPos().x());
                            int y = (int) (ball.getPos().y());
                            Gadget closestGadget = null;
                            double smallestTrigger = 4;
                            for (int tuple = 0; tuple < DISPLACE_NUM; tuple++){
                                int newX = x + DISPLACE[0][tuple];
                                int newY = y + DISPLACE[1][tuple];
                                if (board.getGadget(newX, newY) != null){
                                    Gadget gadget = board.getGadget(newX, newY);
                                    double trigTime = gadget.trigger(ball);
                                    if (trigTime < smallestTrigger){
                                        closestGadget = gadget;
                                        smallestTrigger = trigTime;
                                    }
                                }
                            }
                            if (closestGadget != null){
                                if (!singlePlayer && closestGadget.type() == "outer"){
                                    List<String> names = ((OuterWalls) closestGadget).getVisibility(ball);
                                    String wall = names.get(0);
                                    String you = board.getName();
                                    String them = names.get(1);
                                    if (!(them == null) && !(them.equals(""))){
                                        String msg = "--sendBall " + you + " " + them + " ";
                                        if (wall.equals("top")){
                                            //change ball's y from 0 to 20
                                            msg = msg + ball.getPos().x() + " 19.9 " + ball.getVelocity().x() + " " + ball.getVelocity().y();
                                        } else if (wall.equals("bottom")){
                                            //change ball's y from 20 to 0
                                            msg = msg + ball.getPos().x() + " 0.1 " + ball.getVelocity().x() + " " + ball.getVelocity().y();
                                        } else if (wall.equals("left")){
                                            //change ball's x from 0 to 20
                                            msg = msg + "19.9 " + ball.getPos().y() + " " + ball.getVelocity().x() + " " + ball.getVelocity().y();        
                                        } else if (wall.equals("right")){
                                            //change ball's x from 20 to 0
                                            msg = msg + "0.1 " + ball.getPos().y() + " " + ball.getVelocity().x() + " " + ball.getVelocity().y();               
                                        } 
                                        balls.remove(ball);
                                        out.println(msg);
                                        break;
                                    }
                                } 
                                closestGadget.action(ball);
                                if (fires.containsKey(closestGadget)){
                                    for (Gadget otherG: fires.get(closestGadget)){
                                        otherG.action(ball);
                                    }
                                }
                            } else {
                                ball.move(0.001);
                            }
                        }
                    }
                }
            };
            TimerTask taskPrint = new TimerTask() {
                public void run(){
                    System.out.println(board.viewBoard(balls));
                }
            };
            timer.scheduleAtFixedRate(taskMove, 0L, 1L);
            timer.scheduleAtFixedRate(taskPrint, 0L, 100L);
        } /*catch (IOException e) {
            e.printStackTrace();
        }*/ finally {
        }
    }

    
    public static void main(String[] args) throws IOException{
     // Command-line argument parsing is provided. Do not change this method.
        String hostname = "";
        int port = 10987; // default port
        File file = null;
        boolean noFile = false;
        
        System.out.println("parsing");
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            // I AM ADDING AN ADDITIONAL FLAG FOR NOW, TO MAKE TESTING EASIER
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                System.out.println(flag);
                try {
                    if (flag.equals("--host")) {
                        hostname = arguments.remove();
                    } else if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else if (flag.equals("--noFile")) { // THIS IS THE EXTRA FLAG. DON'T ADD A FILENAME IN THIS CASE
                        noFile = true;
                    } else {
                        file = new File(flag);
                        if (!file.isFile()) {
                            throw new IllegalArgumentException("file not found: \"" + file + "\"");
                        }
                    } 
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("proper usage: PingballClient [--host HOST] [--port PORT] FILE");
            return;
        }
        
        Board board;
        if (noFile){
            board = new Board();
            OuterWalls walls = new OuterWalls();
            board.addGadget(walls);
            LeftFlipper sq = new LeftFlipper(4.0, 18.0, 90, "f");
            board.addGadget(sq);
        } else {
            board = new Board(file.toString());
        }
        
        System.out.println("parsed");
        PingballClient pc = new PingballClient(hostname, port, board, (hostname == ""));
        System.out.println("made client");
        Thread client = new Thread(pc);
        client.start();
    }
    
}
