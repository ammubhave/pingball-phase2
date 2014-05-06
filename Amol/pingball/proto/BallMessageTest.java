package pingball.proto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import physics.Circle;
import physics.Vect;
import pingball.board.Edge;

/**
 *  test for: 
 *  - construction
 *  - serialization
 */

public class BallMessageTest {
    @Test
    public void testConstruction() {
        BallMessage message = new BallMessage(Edge.LEFT, new Circle(new Vect(1.0,1.0), 1.0), new Vect(1.0, 1.0));
        assertEquals(Edge.LEFT, message.getEdge());
        assertEquals(new Circle(new Vect(1.0,1.0), 1.0), message.getShape());
        assertEquals(new Vect(1.0, 1.0), message.getVelocity());
    }
    
    @Test
    public void testSerialization() {
        BallMessage message = new BallMessage(Edge.RIGHT, new Circle(new Vect(10.0,10.0), 1.0), new Vect(1.0, 1.0));
        String messageLine = message.toLine();
        
        Message message2 = Message.fromLine(messageLine);
        assertTrue(message2 instanceof BallMessage);
        BallMessage ballMessage2 = (BallMessage)message2;
        assertEquals(Edge.RIGHT, ballMessage2.getEdge());
        assertEquals(new Circle(new Vect(10.0,10.0), 1.0), ballMessage2.getShape());
        assertEquals(new Vect(1.0, 1.0), ballMessage2.getVelocity());
    }
}