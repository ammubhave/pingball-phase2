package pingball.proto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *  test for: 
 *  - construction
 */

public class ControlMessageTest {
    @Test
    public void testConstruction() {
        ControlMessage message = new ControlMessage(ControlMessage.Type.DISCONNECT);
        assertEquals(ControlMessage.Type.DISCONNECT, message.getType());
    }
    
}