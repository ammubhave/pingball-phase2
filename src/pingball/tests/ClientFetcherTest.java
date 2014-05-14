package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

import pingball.board.Edge;
import pingball.client.ClientFetcher;
import pingball.client.SocketFetcher;
import pingball.client.SocketFetcherTestBase;
import pingball.proto.ControlMessage;
import pingball.proto.DisconnectWallMessage;
import pingball.proto.Message;
import pingball.proto.WelcomeMessage;

/**
 * @category no_didit
 */

/**
 * Testing Strategy: 
 * 
 */

public class ClientFetcherTest extends SocketFetcherTestBase<Message> {
	protected SocketFetcher<Message> createFetcher(
			BlockingQueue<Message> recvQueue, Socket fetcherSocket)
			throws IOException {
		return new ClientFetcher(recvQueue, fetcherSocket);		
	}

	@Test
	public void testSingleMessage() throws InterruptedException {
		Message sent = new WelcomeMessage();
		clientWriter.println(sent.toLine());
		Message received = recvQueue.take();
		assertTrue(received instanceof WelcomeMessage);
	}
	
	@Test
	public void testThreeMessages() throws InterruptedException {
		Message sent1 = new WelcomeMessage();
		Message sent2 = new DisconnectWallMessage(Edge.LEFT);
		clientWriter.println(sent1.toLine());
		clientWriter.println(sent2.toLine());
		clientWriter.println(sent1.toLine());
		Message received1 = recvQueue.take();
		assertTrue(received1 instanceof WelcomeMessage);
		Message received2 = recvQueue.take();
		assertTrue(received2 instanceof DisconnectWallMessage);
		assertEquals(Edge.LEFT, ((DisconnectWallMessage)received2).getEdge());
		Message received3 = recvQueue.take();
		assertTrue(received3 instanceof WelcomeMessage);		
	}

	@Test
	public void testDisconnect() throws InterruptedException, IOException {
		clientSocket.close();
		Message received = recvQueue.take();
		assertTrue(received instanceof ControlMessage);
		assertEquals(ControlMessage.Type.CLOSED,
				((ControlMessage)received).getType());
		fetcherThread.join();
		assertTrue(fetcherSocket.isClosed());
	}

	@Test
	public void testMessageAndDisconnect()
			throws InterruptedException, IOException {
		Message sent = new WelcomeMessage();
		clientWriter.println(sent.toLine());
		clientSocket.close();
		Message received1 = recvQueue.take();
		assertTrue(received1 instanceof WelcomeMessage);
		Message received2 = recvQueue.take();
		assertTrue(received2 instanceof ControlMessage);
		assertEquals(ControlMessage.Type.CLOSED,
				((ControlMessage)received2).getType());
		fetcherThread.join();
		assertTrue(fetcherSocket.isClosed());
	}
}
