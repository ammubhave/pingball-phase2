package pingball.cli;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.swing.SwingUtilities;

import pingball.board.Board;
import pingball.parser.BoardBuilder;
import pingball.ui.MainWindow;
import pingball.client.ClientController;

public class PingballClient {
    /**
     * Loads a board file and begins playing it.
     * 
     * Usage: PingballClient [--host HOST] [--port PORT] FILE
     * 
     * HOST is an optional hostname or IP address of the server 
     * to connect to. If no HOST is provided, then the client 
     * starts in single-machine play mode, as described above.
     * 
     * PORT is an optional integer in the range 0 to 65535 inclusive, 
     * specifying the port where the server is listening for incoming connections. 
     * The default port is 10987.
     * 
     * FILE is a required argument specifying a file pathname of the Pingball board 
     * that this client should run.
     * 
     * @param args
     */
	public static void main(String[] args) {
        int port = 10987;  // default port
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
                            throw new IllegalArgumentException(
                            		"port " + port + " out of range");
                        }
                    } else if (flag.equals("--host")) {
                        host = arguments.remove();
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException(
                    		"missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(
                    		"unable to parse number for " + flag);
                }
            }
            String boardPath = "boards/sampleBoard3.pb";
            if (arguments.size() >= 1)
                boardPath = arguments.remove();
            	
            boardFile = new File(boardPath);
            if (!boardFile.isFile()) {
                throw new IllegalArgumentException(
                		"file not found: \"" + boardFile + "\"");
            }            
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballClient [--host PORT] " +
            		"[--port PORT] [--term] path/to/board_file.pb");
            return;
        }
        runClient(boardFile, host, port);
	}
	
	/**
	 * Starts a PingballClient running the specified board and connected to the
	 * specified server.
	 * 
	 * @param boardFile the client's board
	 * @param host the hostname of the server
	 * @param port the network port where the server is listening
	 */
	public static void runClient(File boardFile, String host, int port) {
		try {
			final Board board = BoardBuilder.buildBoard(boardFile);
			ClientController controller = new ClientController(board, host,
					port);
			// set up the UI (on the event-handling thread)
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                MainWindow window = new MainWindow(board);
	            }
	        });
			controller.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}