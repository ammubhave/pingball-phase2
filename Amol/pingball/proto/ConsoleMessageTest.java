package pingball.proto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *  test for: 
 *  - construction
 */

public class ConsoleMessageTest {
    @Test
    public void testConstruction() {
        ConsoleMessage message = new ConsoleMessage("h board1 board2");
        assertEquals("h board1 board2", message.getLine());
    }
}