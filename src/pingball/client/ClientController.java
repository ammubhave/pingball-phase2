package pingball.client;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import pingball.board.Board;
import pingball.proto.HelloMessage;
import pingball.proto.Message;
import pingball.proto.WelcomeMessage;
import pingball.ui.MainWindow;

public class ClientController {
    private final double BOARD_REFRESH_INTERVAL = 0.050; // seconds
    private final double DT = 0.05/200.0;
    
	private final Board board;
	private final Connection serverConnection;
	private final BlockingQueue<Message> sendQueue;
	private final BlockingQueue<Message> recvQueue;
	MainWindow window;
	/**
	 * Sets up a controller for a new client.
	 * 
	 * @param board the client's board
	 * @param host the hostname of the server
	 * @param port the network port where the server is listening
	 * @param inTerminal if true, the screen will be cleared after each frame is
	 *   drawn
	 * @throws IOException
	 */
	public ClientController(final Board board, String host, int port)
			throws IOException {
		assert board != null;

		this.board = board;
		this.recvQueue = new ArrayBlockingQueue<Message>(1);
		this.sendQueue = new LinkedBlockingQueue<Message>();
		
		if (host != null) {
			this.serverConnection = new Connection(host, port, sendQueue,
					recvQueue);
			handshake();
		} else {
			this.serverConnection = null;
		}         // set up the UI (on the event-handling thread)
		SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	               window = new MainWindow(board);
	            }
	    });
	}
    
    /**
     * Starts the controller. Starts rendering the board onto the console
     * and also starts simulation of the board.
     */
    public void start() {
        // scheudle both tasks to run at specific intervals -
        // heartbeat and board printer
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
      
        exec.scheduleAtFixedRate(new BoardPrinterTask(), 0, (long)(BOARD_REFRESH_INTERVAL*1000*1000), TimeUnit.MICROSECONDS);

        exec.scheduleWithFixedDelay(new HeartbeatTask(), 0, (long)(DT*1000*1000), TimeUnit.MICROSECONDS);
        
    }
    
    
    /**
     * Task to increment time on the board by DT
     */
    class HeartbeatTask implements Runnable {
        @Override
        public void run() {
        	List<Message> messages;
            synchronized(board) {
            	while (recvQueue.peek() != null) {
            		// NOTE: NoSuchElementException should never be thrown,
            		//       because we used peek() before, and we're this
            		//		 queue's consumer
            		Message message = recvQueue.remove();
            		board.onMessage(message);
            	}            	
                board.simulateTime(DT);                
                messages = board.getOutOfBoundBallMessages();
            }
            for (Message message : messages)
                sendQueue.add(message);
        }
    }

    /**
     * Prints the board onto the console
     */
    class BoardPrinterTask implements Runnable {
        @Override
        public void run() {
            synchronized(board) {
                System.out.println(board.toString());
                if (window != null)
                    window.repaint();
            }
        }                
    }
    
	/**
	 * Waits until the server sends the welcome message.
	 */
	private void handshake() {
		assert sendQueue != null;
		assert recvQueue != null;
		assert serverConnection != null;
		
		try {
			sendQueue.put(new HelloMessage(board.getName()));
			while (true) {
				Message message = recvQueue.take();
				if (message instanceof WelcomeMessage) {
					break;
				}
			}
		} catch (InterruptedException e) {
			throw new IllegalStateException(
					"Interrupted while waiting for server welcome", e);
		}
	}    
}
