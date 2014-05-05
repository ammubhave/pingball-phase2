package pingball.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import pingball.proto.ControlMessage;
import pingball.proto.Message;
import pingball.proto.ControlMessage.Type;

/** Forwards messages from a socket to a queue. */
public abstract class SocketFetcher<T> implements Runnable {
	/** The socket. */
	private final Socket socket;
	/** Wraps the socket's input stream. */
	private final BufferedReader reader;
	/** Messages received from the server will be queued here. */
	private final BlockingQueue<T> recvQueue;
	
	public SocketFetcher(BlockingQueue<T> recvQueue, Socket socket)
			throws IOException {
		assert recvQueue != null;
		assert socket != null;
		this.socket = socket;
		this.recvQueue = recvQueue;
		this.reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String messageLine = reader.readLine();
				if (messageLine == null)  // The other side closed the socket.
					break;
				Message message = Message.fromLine(messageLine);
				T queueItem = convertMessage(message);
				recvQueue.put(queueItem);
			}
		} catch(InterruptedException e) {
			// Someone else wants us to be done.
			e.printStackTrace();
		} catch (IOException e) {
			// The connection broke down.
			e.printStackTrace();
		} finally {
			Message closed = new ControlMessage(Type.CLOSED);
			T queueItem = convertMessage(closed);
			try {
				recvQueue.put(queueItem);
			} catch (InterruptedException e) {
				// Someone else wants us to be done. We're almost there.
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				// Shutting down, nothing we can do about this.
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// Shutting down, nothing we can do about this.
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Convert a {@link Message} into the right type for the queue.
	 * @param message the message to be converted
	 * @return the right type for the queue
	 */
	protected abstract T convertMessage(Message message);
}