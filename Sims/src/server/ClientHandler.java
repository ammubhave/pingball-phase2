package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final PingballServer server;
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    /**
     * Constructs a thread that allows the server to handle multiple clients.
     * 
     * @param socket
     *            to connect to the server
     * @param board
     *            to play on
     * @throws IOException
     */
    public ClientHandler(PingballServer server, Socket socket)
            throws IOException {
        this.server = server;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    /**
     * Tries to handle the connection.
     */
    public void run() {
        try {
            handleConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle a connection with the server. Returns when client disconnects.
     * 
     * @param socket
     *            socket where the client is connected
     * @throws IOException
     *             if connection has an error or terminates unexpectedly
     */
    private void handleConnection() throws IOException {
        try {
            for (String line = in.readLine(); line != null; line = in
                    .readLine()) {
                String output = handleRequest(line);
                if (output == null)
                    socket.close();

            }
        } finally {
            out.close();
            in.close();
        }
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
        String board = "board " + name;
        String ball = "ball " + name + " " + num + " " + num + " " + num + " "
                + num;
        String regex = "(" + board + ")|(" + ball + ")";

        if (!input.matches(regex)) {
            System.out.println("ClientHandler input is baddd; it is\"" + input
                    + "\"");
            // invalid input
            return null;
        }
        String[] tokens = input.split(" ");

        if (tokens[0].equals("board")) {
            // add the board name and corresponding socket to the server
            server.addBoardToServer(tokens[1], this);
            return "Board added";
        } else if (tokens[0].equals("ball")) {
            String toBoard = tokens[1];
            // Server tells which client to receive a ball
            server.passBall(toBoard, input);
            return "Ball passed";
        }
        // Should never get here--make sure to return in each of the valid
        // cases above.
        throw new UnsupportedOperationException();
    }

    /**
     * Sends information to the client on which ball to add.
     * 
     * @param input
     *            the description of the ball
     */
    public void addBallToClient(String input) {
        out.println(input);
    }

    /**
     * Sends information to the client about their disappearing neighbor
     * 
     * @param dir
     *            the direction that the neighbor is.
     */
    public void removeBoardNeighbor(String dir) {
        out.println("unmerge " + dir);
    }

    /**
     * Sends information to the client about their new neighbor
     * 
     * @param dir
     *            the direction that the neighbor is.
     * @param name
     *            the neighbor's name
     */
    public void addBoardNeighbor(String dir, String name) {
        out.println("merge " + dir + " " + name);
    }

    public boolean isDead() {
        return socket.isClosed();
    }
}
