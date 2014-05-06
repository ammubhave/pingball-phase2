package server;

import gameplay.Absorber;
import gameplay.Ball;
import gameplay.Board;
import gameplay.Bumper;
import gameplay.Gadget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import physics.Vect;
import utilities.Coords;

public class PingballServer {
    private final BufferedReader systemIn;
    private final ServerSocket serverSocket;
    private final HashMap<String, ClientHandler> boards;
    private final HashMap<ClientHandler, HashMap<String, ClientHandler>> neighbors;

    /**
     * Make a PingballServer that listens for connections on port.
     * 
     * @param port
     *            port number, requires 0 <= port <= 65535
     * @throws IOException
     */
    public PingballServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        boards = new HashMap<String, ClientHandler>();
        neighbors = new HashMap<ClientHandler, HashMap<String, ClientHandler>>();
        systemIn = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Run the server, listening for input from System.in to connect boards,
     * checking to remove clients that are no longer active, and listening for
     * client connections and handling them. Never returns unless an exception
     * is thrown.
     * 
     * @throws IOException
     *             if the main server socket is broken (IOExceptions from
     *             individual clients do *not* terminate serve())
     */
    public void serve() throws IOException {

        // Create a thread to read from System.in
        Thread inReader = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String line = systemIn.readLine(); line != null; line = systemIn
                            .readLine()) {
                        String[] tokens = line.split(" ");
                        if (tokens[0].equals("h")) {
                            ClientHandler clientLeft = boards.get(tokens[1]);
                            ClientHandler clientRight = boards.get(tokens[2]);

                            // remove current neighbors
                            removeNeighbor(clientLeft, "right");
                            removeNeighbor(clientRight, "left");

                            // add current neighbors
                            clientLeft.addBoardNeighbor("right", tokens[2]);
                            clientRight.addBoardNeighbor("left", tokens[1]);

                            // update neighbors
                            neighbors.get(clientLeft).put("right", clientRight);
                            neighbors.get(clientRight).put("left", clientLeft);

                        } else if (tokens[0].equals("v")) {
                            ClientHandler clientTop = boards.get(tokens[1]);
                            ClientHandler clientBottom = boards.get(tokens[2]);

                            // reomve current neighbors
                            removeNeighbor(clientTop, "bottom");
                            removeNeighbor(clientBottom, "top");

                            // add current neighbors
                            clientTop.addBoardNeighbor("bottom", tokens[2]);
                            clientBottom.addBoardNeighbor("top", tokens[1]);

                            // update neighbors
                            neighbors.get(clientTop)
                                    .put("bottom", clientBottom);
                            neighbors.get(clientBottom).put("top", clientTop);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        inReader.start();

        // Check to make sure none of the client sockets have closed
        Thread clientChecker = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    ArrayList<String> died = new ArrayList<String>();
                    for (String s : boards.keySet())
                        if (boards.get(s).isDead()) {
                            removeNeighbor(boards.get(s), "left");
                            removeNeighbor(boards.get(s), "right");
                            removeNeighbor(boards.get(s), "top");
                            removeNeighbor(boards.get(s), "bottom");
                            died.add(s);
                        }
                    for (String dead : died)
                        boards.remove(dead);
                }
            }

        });
        clientChecker.start();

        while (true) {
            // block until a client connects
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(this, socket);
            neighbors.put(handler, new HashMap<String, ClientHandler>());
            Thread t = new Thread(handler);
            t.start();
        }
    }

    /**
     * Start a PingballServer using the given arguments.
     * 
     * Usage: PingballServer [--port PORT]
     * 
     * PORT is an optional integer in the range 0 to 65535 inclusive, specifying
     * the port the server should be listening on for incoming connections. E.g.
     * "PingballServer --port 1234" starts the server listening on port 10987.
     * 
     */
    public static void main(String[] args) {
        // Command-line argument parsing is provided. Do not change this method.
        int port = 10987; // default port

        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port
                                    + " out of range");
                        }
                    } else {
                        throw new IllegalArgumentException("unknown option: \""
                                + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for "
                            + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(
                            "unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballServer [--port PORT]");
            return;
        }

        try {
            runPingballServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start a PingballServer running on the specified port, connecting a client
     * to it.
     * 
     * @param port
     *            The network port on which the server should listen.
     */
    public static void runPingballServer(int port) throws IOException {
        PingballServer server = new PingballServer(port);
        server.serve();
    }

    /**
     * Adds a board to the server, whose name is passed through via the client
     * handler
     * 
     * @param string
     *            the name of the board to be connected
     * @param clientHandler
     *            the client handler corresponding to the board
     */
    public void addBoardToServer(String string, ClientHandler clientHandler) {
        boards.put(string, clientHandler);
    }

    /**
     * Remove the client from the client's neighbor in the direction dir
     * 
     * @param client
     *            the client to be removed from the neighbor
     * @param dir
     *            the direction of the neighbor for which the client is removed
     */
    public void removeNeighbor(ClientHandler client, String dir) {
        String oppositeDirection = "";
        if (dir.equals("top"))
            oppositeDirection = "bottom";
        else if (dir.equals("bottom"))
            oppositeDirection = "top";
        else if (dir.equals("left"))
            oppositeDirection = "right";
        else if (dir.equals("right"))
            oppositeDirection = "left";

        // first remove current neighbors
        ClientHandler currentNeighbor = neighbors.get(client).get(dir);
        if (currentNeighbor != null) {
            currentNeighbor.removeBoardNeighbor(oppositeDirection);
            neighbors.get(currentNeighbor).put(oppositeDirection, null);
        }
    }

    /**
     * Passes a ball to the toBoard with the parameters specified from the given
     * input.
     * 
     * @param toBoard
     *            name of the board the ball is passed to
     * @param input
     *            String that includes the parameters of the ball
     */
    public void passBall(String toBoard, String input) {
        System.out.println(toBoard + "is being added to");
        ClientHandler clientHandler = boards.get(toBoard);
        clientHandler.addBallToClient(input);
    }

    public static void test() {

        Ball b1 = new Ball(new Coords(5, 2), new Vect(0, 1));
        Ball b2 = new Ball(new Coords(5, 15), new Vect(0, -1));
        Ball b3 = new Ball(new Coords(2, 5), new Vect(1, 0.0));
        Ball b4 = new Ball(new Coords(10, 5), new Vect(-1.0, 0.0));

        Gadget s1 = new Bumper(new Coords(5, 7), "SQUARE");
        Gadget t1 = new Bumper(new Coords(5, 5), "TRIANGLE", 270);
        Gadget t2 = new Bumper(new Coords(8, 7), "TRIANGLE", 0);
        Gadget t3 = new Bumper(new Coords(6, 7), "TRIANGLE", 90);

        Gadget c1 = new Bumper(new Coords(7, 7), "CIRCLE");
        Gadget a1 = new Absorber(1, 5, new Coords(1, 18));

        List<Ball> balls = Arrays.asList(b1, b2);
        List<Gadget> gadgets = Arrays.asList();

        Board board = new Board(null, "SOME BOARD", balls, gadgets, 25, 0.25,
                0.25);

        while (true) {
            try {
                // sending the actual Thread of execution to sleep X
                // milliseconds
                Thread.sleep(10);
            } catch (InterruptedException ie) {
            }
            board.go();
            System.out.println(board);
        }
    }
}
