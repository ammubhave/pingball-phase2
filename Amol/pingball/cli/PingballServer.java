package pingball.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import pingball.server.BoardLinks;
import pingball.server.Dispatcher;
import pingball.server.LinksController;

public class PingballServer {
    /**
     * Joins the outer walls of clients together, so that a ball exiting one client's
     * playing area can enter another client.
     * 
     * Usage: PingballServer [--port PORT]
     * 
     * PORT is an optional integer in the range 0 to 65535 inclusive, 
     * specifying the port where the server should listen for incoming connections. 
     * The default port is 10987.
     * 
     * @param args
     */
	public static void main(String[] args) {
        int port = 10987;  // default port
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException(
                            		"port " + port + " out of range");
                        }
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException(
                    		"missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(
                    		"unable to parse number for " + flag);
                }
            }
            runServer(port);
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballServer [--port PORT]");
            return;
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
	
	/**
	 * Starts a PingballServer at the specified port.
	 * 
	 * @param port The network port on which the server should listen.
	 * @throws IOException
	 */
	public static void runServer(int port) throws IOException {
		BoardLinks boardLinks = new BoardLinks();

		// HACK: remove when done testing
		boardLinks.horizontalJoin("sampleBoard2_1", "sampleBoard2_2");
		//boardLinks.verticalJoin("warmup", "warmup");
		
		LinksController linkController = new LinksController(boardLinks);
		Thread linkThread = new Thread(linkController, "Board Links");
		linkThread.start();

		Dispatcher dispatcher = new Dispatcher(
				linkController.getRequestQueue(), port, System.in);
		dispatcher.serve();
	}
}