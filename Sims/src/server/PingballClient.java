package server;

import gameplay.Ball;
import gameplay.Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import parser.BoardFactory;
import physics.Vect;
import utilities.Coords;

public class PingballClient {

    private final Board board;
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    /**
     * Make a PingballClient that doesn't connect to the port.
     * 
     * @param file
     *            the file to be parsed containing the details of the contents
     *            of the board
     * 
     * @throws IOException
     */
    public PingballClient(File f) throws IOException {
        board = BoardFactory.parse(this, f);
        socket = null;
        in = null;
        out = null;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        // sending the actual Thread of execution to sleep X
                        // milliseconds
                        Thread.sleep(50);
                    } catch (InterruptedException ie) {
                    }
                    System.out.println(board);
                    synchronized (board) {
                        board.go();
                    }
                }
            }
        });
        t.start();
    }

    /**
     * Make a PingballClient that connects to the host via the port, creating
     * thread that prints out the board continuously, and creating a thread that
     * checks for information from the server via the socket.
     * 
     * @param host
     *            the name of the host, or the IP address
     * @param port
     *            port number, requires 0 <= port <= 65535
     * @param file
     *            the file to be parsed containing the contents of the board
     * @throws IOException
     */
    public PingballClient(String host, int port, File f) throws IOException {
        board = BoardFactory.parse(this, f);
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // pass the board's name through the socket, only thing written to the
        // socket
        out.println("board " + board.getName());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // sending the actual Thread of execution to sleep X
                        // milliseconds
                        Thread.sleep(50);
                    } catch (InterruptedException ie) {
                    }
                    
                    System.out.println(board);
                    synchronized (board) {
                        board.go();
                    }
                    
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Welcome to Pingball!");
                    for (String line = in.readLine(); line != null; line = in
                            .readLine()) {
                        handleRequest(line);
                    }
                    socket.close();
                    out.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }

    /**
     * Creates a PingballClient using the given arguments.
     * 
     * Usage: PingballClient [--port PORT]
     * 
     * PORT is an optional integer in the range 0 to 65535 inclusive, specifying
     * the port the client should be listening on for incoming connections. E.g.
     * "PingballClient --port 1234" starts the client listening on port 10987.
     * 
     */
    public static void main(String[] args) {
        // Command-line argument parsing is provided. Do not change this method.
        boolean alone = true;
        String host = "";
        int port = 10987; // default port
        String fileName = "";

        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--host")) {
                        host = arguments.remove();
                        alone = false;
                    } else if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port
                                    + " out of range");
                        }
                    } else {
                        fileName = flag;
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
            System.err
                    .println("usage: PingballClient [--host HOST] [--port PORT] FILE");
            return;
        }

        try {
            runPingballClient(alone, host, port, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a PingballClient running on the specified port, connecting a
     * client to it.
     * 
     * @param port
     *            The network port on which the client should listen.
     */
    public static void runPingballClient(boolean alone, String name, int port,
            File f) throws IOException {
        if (alone)
            new PingballClient(f);
        else
            new PingballClient(name, port, f);

    }

    /**
     * Handler for client input, performing requested operations and returning
     * an output message.
     * 
     * @param input
     *            message from client
     * @return message to client
     * @throws IOException
     */
    private String handleRequest(String input) throws IOException {

        String name = "[A-Za-z_][A-Za-z_0-9]*";
        String num = "(-)?([0-9]+.[0-9]*|.?[0-9]+)";
        String dir = "((left)|(right)|(top)|(bottom))";
        String merge = "merge " + dir + " " + name;
        String unmerge = "unmerge " + dir;
        String ball = "ball " + dir + " " + num + " " + num + " " + num + " "
                + num;
        String regex = "(" + merge + ")|(" + unmerge + ")|(" + ball + ")";

        if (!input.matches(regex)) {
            // invalid input
            System.out.println("request input invalid! it is: \"" + input
                    + "\"");
            return null;
        }
        String[] tokens = input.split(" ");

        if (tokens[0].equals("merge")) {
            // add the board name and corresponding socket to the server
            synchronized (board) {
                board.add(tokens[1], tokens[2]);
            }
            return "successful merge";
        } else if (tokens[0].equals("unmerge")) {
            // add the board name and corresponding socket to the server
            synchronized (board) {
                board.remove(tokens[1]);
            }
            return "successful unmerge";
        } else if (tokens[0].equals("ball")) {
            // add the board name and corresponding socket to the server
            double x = Double.parseDouble(tokens[2]);
            double y = Double.parseDouble(tokens[3]);
            double vx = Double.parseDouble(tokens[4]);
            double vy = Double.parseDouble(tokens[5]);
            board.addBall(new Ball(new Coords(x, y), new Vect(vx, vy)));
            return "successful ball";
        }

        // Should never get here--make sure to return in each of the valid
        // cases above.
        throw new UnsupportedOperationException();
    }

    /**
     * Sends a ball to the server via the socket so that it can travel to a
     * different board.
     * 
     * @param b
     *            the ball to be passed to the server
     * @param s
     *            the name of the board to be passed to
     */
    public void sendBall(Ball b, String s) {
        out.println("ball " + s + " " + b.getX() + " " + b.getY() + " "
                + b.getVelocity().x() + " " + b.getVelocity().y());
    }
}