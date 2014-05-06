package pingball.client;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import pingball.proto.Message;

/**
 *  Forwards messages from socket to queue 
 */

public class ClientFetcher extends SocketFetcher<Message> {
	public ClientFetcher(BlockingQueue<Message> recvQueue, Socket socket)
			throws IOException {
		super(recvQueue, socket);
	}
	
	@Override
	protected Message convertMessage(Message message) {
		return message;
	}

}