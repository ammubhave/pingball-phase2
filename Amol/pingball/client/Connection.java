package pingball.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import pingball.proto.Message;

public class Connection {
	/** The connection to the server. */
	final Socket socket;
	
	/** Messages queued will be sent to the server. */
	private final BlockingQueue<Message> sendQueue;
	/** Messages received from the server will be queued here. */
	private final BlockingQueue<Message> recvQueue;
	
	public Connection(String host, int port, BlockingQueue<Message> sendQueue,
			BlockingQueue<Message> recvQueue) throws IOException {
		assert host != null;
		assert 1 <= port && port <= 65535;
		assert sendQueue != null;
		assert recvQueue != null;
		
		this.sendQueue = sendQueue;
		this.recvQueue = recvQueue;
		
		this.socket = new Socket();
		socket.connect(new InetSocketAddress(InetAddress.getByName(host),
				port));
		socket.setKeepAlive(true);
		socket.setTcpNoDelay(true);
		
		ClientFetcher fetcher = new ClientFetcher(recvQueue, socket);
		Thread fetcherThread = new Thread(fetcher, "Client Fetcher");
		fetcherThread.start();

		SocketPusher sender = new SocketPusher(sendQueue, socket);
		Thread senderThread = new Thread(sender, "Client Pusher");
		senderThread.start();
	}
}
