package pingball.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pingball.client.SocketPusher;
import pingball.proto.ConsoleMessage;
import pingball.proto.Message;


/**
 * Manages the server's threads.
 * 
 * The server has one thread that accepts connections on the listening socket,
 * and spawns two new threads when a new client connection is established. Each
 * client has a {@link ServerFetcher} thread that reads lines from the socket
 * and converts them into {@link Request} instances that end up in the
 * request queue. Each client also has a {@link SocketPusher} thread that takes
 * messages from the client's send queue and writes them as lines to the
 * client's socket.
 */
public class Dispatcher {
	/** Interface to the code running on the server thread. */
	private final ServerController serverController;
	/** The thread running the server code. */
	private final Thread serverThread;
	/** The thread running the server code. */
	private final Thread consoleThread;
	
	// Rep invariant:
	//   serverController, serverThread and consoleThread are not null
	// Thread safety argument:
	//   all member variables are immutable references (final)
	//   serverController is used in a thread-safe manner, according to its spec
	//   serverThread and consoleThread are only accessed in serve(), which is
	//		 documented as not being thread-safe

	/**
	 * Sets up a dispatcher.
	 * 
	 * @param requestQueue queue that accepts requests for the board links
	 *   thread
	 * @param port the port that the server will listen to
	 * @param consoleStream the console's input stream
	 * @throws IOException
	 */
	public Dispatcher(BlockingQueue<Request> requestQueue, int port,
			InputStream consoleStream) throws IOException {
		assert requestQueue != null;

		serverController = new ServerController(requestQueue, port);
		serverThread = new Thread(serverController, "Server");
		ConsoleController consoleController = new ConsoleController(
				requestQueue, consoleStream);
		consoleThread = new Thread(consoleController, "Console Reader");
	}
	
	/**
	 * Sets up the server to listen for connections.
	 * 
	 * This method should be called at most once. It returns when the server is
	 * no longer listening for connections, either because
	 * {@link #stopServing()} was called, or because 
	 */
	public void serve() {
		serverThread.start();
		consoleThread.start();
		try {
			serverThread.join();
			consoleThread.join();
		} catch(InterruptedException e) {
			// Someone else wants us to exit.
		}
	}
	
	/**
	 * Shuts down the server.
	 * 
	 * This method can be called on any thread.
	 */
	public void stopServing() {
		serverController.stopListening();
	}
	
	/** The code that runs on the server thread. */
	private static class ServerController implements Runnable {
		/** Accepts requests for the board links thread. */
		private final BlockingQueue<Request> requestQueue;
		/** The socket that accepts incoming client connections. */
	    private final ServerSocket socket;
	    /** Lock that must be acquired to mutate {@link #listening}. */
	    private final Object listeningChangeLock;
	    /** False when the thread should stop */
	    private boolean listening;
	    
	    // Rep invariant:
	    //   requestQueue, socket and listeningChangeLock are not null
	    // Abstraction function:
	    //	 requestQueue is the queue that receives board links requests
	    //   socket is the socket listening for connections on the server port
	    // Thread-safety argument 1:
	    //   (outside tests, #stopListening is never called)
	    //	 assuming #stopListening is never called, all the fields are
	    //   initialized in the constructor and contained to the server
	    //   thread afterwards
	    // Thread-safety argument 2:
	    //   (in tests, when #stopListening can be called)
	    //   socket is final, so it always points to the same ServerSocket; the
	    //       server thread only calls ServerSocket#accept and any other
	    //       thread can only call ServerSocket#close; the docs for
	    //       ServerSocket#close specify the behavior in this case, implying
	    //		 that our use is thread-safe
	    //   listeningChangeLock is immutable, and is only used as a lock around
	    //        listening
	    //   accesses to listening are always synchronized by
	    //		  listeningChangeLock; no methods are called in the synchronized
	    //        sections, so no deadlock can occur
	    
	    /**
	     * Make a MinesweeperServer that listens for connections on port.
	     * 
	     * @param port port number, requires 0 <= port <= 65535
	     */
	    public ServerController(BlockingQueue<Request> requestQueue, int port)
	    		throws IOException {
	    	assert requestQueue != null;
	    	
	    	this.requestQueue = requestQueue;
	        socket = new ServerSocket(port);
	        listeningChangeLock = new Object();
	        listening = true;
	    }

		@Override
		public void run() {
	        while (true) {
	        	synchronized (listeningChangeLock) {
	        		if (!listening)
	        			break;
	        	}
	        	
	        	try {
	        		Socket clientSocket = socket.accept();
	        		
	        		BlockingQueue<Message> clientQueue =
	        				new LinkedBlockingQueue<Message>();
	        		SocketPusher pusher = new SocketPusher(
	        				clientQueue, clientSocket);
	        		Thread pusherThread = new Thread(pusher, "Server Pusher");
		            pusherThread.start();
		            
		            ServerFetcher clientFetcher = new ServerFetcher(
		            		requestQueue, clientSocket, clientQueue);
		            Thread fetcherThread = new Thread(clientFetcher,
		            		"Server Fetcher");
		            fetcherThread.start();
	        	} catch (IOException e) {
	        		// This can happen if #stopListening() is called, or if
	        		// the OS errors in the system call, for some reason.
	        		continue;
	        	}
	        }
	        
	        try {
	        	socket.close();
	        } catch (IOException e) {
        		// Shutting down, nothing we can do about this.
	        }
		}
		
		/**
		 * Stops the thread running this controller's code.
		 * 
		 * This method is thread-safe and idempotent.
		 */
		public void stopListening() {
			synchronized (listeningChangeLock) {
				if (!listening)
					return;
				listening = false;
			}
			try {
				// Causes ServerSocket#accept to throw a SocketException in
				// the server thread.
				socket.close();
	        } catch (IOException e) {
        		// Shutting down, nothing we can do about this.
	        }
		}
	}
	
	/** The code that runs on the console thread. */
	private class ConsoleController implements Runnable {
		/** Accepts requests for the board links thread. */
		private final BlockingQueue<Request> requestQueue;
	    /** Wrapper around the console stream. */
	    private final BufferedReader consoleReader;

	    public ConsoleController(BlockingQueue<Request> requestQueue,
	    		InputStream consoleStream) {
	    	assert requestQueue != null;
	    	assert consoleStream != null;
	    	
	    	this.requestQueue = requestQueue;
	    	this.consoleReader = new BufferedReader(new InputStreamReader(
	    			consoleStream));
	    }
	    
	    @Override
		public void run() {
	    	try {
		    	while (true) {
		    		String line = consoleReader.readLine();
		    		if (line == null) {
		    			// The console stream was closed.
		    			return;
		    		}
		    		
		    		ConsoleMessage message = new ConsoleMessage(line);
		    		Request request = new Request(null, message);
		    		requestQueue.put(request);
		    	}
	    	} catch (IOException e) {
	    		// Something went wrong while reading from the console.
	    		// Not much we can do about it.
	    		e.printStackTrace();
	    	} catch (InterruptedException e) {
				// Someone else wants us to exit.	    		
	    	}
		}
	}
}