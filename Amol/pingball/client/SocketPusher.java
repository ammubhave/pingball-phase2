package pingball.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import pingball.proto.ControlMessage;
import pingball.proto.Message;

/**
 * Forwards messages from a queue to a socket.
 * 
 * This is intended to be used as a {@link Thread}'s {@link Runnable}.
 */
public class SocketPusher implements Runnable {
	/** The socket that messages get pushed to. */
	private final Socket socket;
	/** Wrapper around the socket's output stream. */
	private final PrintWriter writer;
	/** Messages queued will be sent to the server. */
	private final BlockingQueue<Message> sendQueue;
	
	// Rep invariant:
	//   everything is non-null
	// Thread safety:
	//   not thread-safe; the fields are final, so they will safely make it from
	//   the thread constructing this instance to the thread 
	
	public SocketPusher(BlockingQueue<Message> sendQueue, Socket socket)
			throws IOException {
		assert sendQueue != null;
		assert socket != null;
		
		this.socket = socket;
		this.sendQueue = sendQueue;
		// NOTE: "true" enables auto-flush
		this.writer = new PrintWriter(socket.getOutputStream(), true);			
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				Message message = sendQueue.take();
				if (message instanceof ControlMessage) {
					ControlMessage control = (ControlMessage)message;
					switch(control.getType()) {
					case DISCONNECT:
						return;
					case CLOSED:
						// CLOSED should only be used by SocketFetcher, so
						// this should never happen. Someone got confused if
						// this line executes.
						assert false;
						break;
					}
				}
				String messageString = message.toLine();
				writer.println(messageString);
			}
		} catch(InterruptedException e) {
			// Someone else wants us to be done.
			e.printStackTrace();
		} finally {
			writer.close();
			try {
				socket.close();
			} catch(IOException e) {
				// We're already shutting down, not much to do here.
			}
		}
	}
}