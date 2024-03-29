package pingball.cli;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import pingball.board.Board;
import pingball.client.ClientController;
import pingball.parser.BoardBuilder;

/*
 * Thread Safety:
 * 
 * The client spins up multiple threads:
 *  - Two threads, ClientFetcher and SocketPusher operate on thread safe datatypes
 *    and don't access any other shared objects. Hence all the objects are either
 *    confined to it or shared objects like BlockingQueue is thread safe.
 *    
 *  - One Board Printer Thread
 *    - Uses render methods of gadgets. All mutable gadgets have mutable methods synchronized and also render
 *      as synchronized so at any point of time, the mutable gadgets do not have concurrent access.
 *    - Does a repaint, repainting calls upon the GadgetPainter objects each of which holds an instance of
 *      gadget. Again all the mutable gadgets have their neccessary observers synchronized.
 *    - All other immutable gadgets like square and circle bumpers don't have anything synchronized
 *      as they dont mutate anything**.
 *      
 *  - One Heartbeat Thread
 *    This is the main thread which simulates time.
 *    - It acts upon Ball and also mutates the gadgets when required. All these mutations on the objects
 *      are done synchronized on those gadgets itself. Therefore, no concurrent modifications of
 *      states is allowed.
 *      
 *  - Flipper rotation thread
 *    This is spun up whenever a flipper starts to rotate.
 *    This thread first synchronizes on the Flipper object and then performs mutation on it. And since all
 *    other observers and mutators first synchronize on the flipper objects, concurrency is not an issue.
 *      
 *  ** -> There is mutation in these gadgets too in form of hooking gadgets to trigger, but this mutation
 *      is only performed in the factory methods. So once the board is initialized and the threads started,
 *      these gadgets are not mutated anymore.
 */

public class PingballClient {
    /**
     * Loads a board file and begins playing it.
     * 
     * New Features Added: - Sound when ball bounces off a gadget. - Ability to
     * add certain gadgets to the board by selecting them in the menu and
     * clicking on the desired location on the board.
     * 
     * Usage: PingballClient [--host HOST] [--port PORT] FILE
     * 
     * HOST is an optional hostname or IP address of the server to connect to.
     * If no HOST is provided, then the client starts in single-machine play
     * mode, as described above.
     * 
     * PORT is an optional integer in the range 0 to 65535 inclusive, specifying
     * the port where the server is listening for incoming connections. The
     * default port is 10987.
     * 
     * FILE is a required argument specifying a file pathname of the Pingball
     * board that this client should run.
     * 
     * @param args
     */
    public static void main(String[] args) {
        int port = 10987; // default port
        String host = null;
        File boardFile = null;

        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while (arguments.size() > 1) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else if (flag.equals("--host")) {
                        host = arguments.remove();
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
            String boardPath = "boards/welcomeBoard.pb";
            if (arguments.size() >= 1)
                boardPath = arguments.remove();

            boardFile = new File(boardPath);
            if (!boardFile.isFile()) {
                throw new IllegalArgumentException("file not found: \"" + boardFile + "\"");
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballClient [--host PORT] " + "[--port PORT] [--term] path/to/board_file.pb");
            return;
        }
        runClient(boardFile, host, port);
    }

    /**
     * Starts a PingballClient running the specified board and connected to the
     * specified server.
     * 
     * @param boardFile
     *            the client's board
     * @param host
     *            the hostname of the server
     * @param port
     *            the network port where the server is listening
     */
    public static void runClient(File boardFile, String host, int port) {
        try {
            final Board board = BoardBuilder.buildBoard(boardFile);
            ClientController controller = new ClientController(board, host, port, boardFile);

            controller.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}