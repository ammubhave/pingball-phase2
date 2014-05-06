package pingball.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
/**
 * testing strategy:
 *   - test render for all four corners
 *   - test perpendicular reflection
 * - test equals
 */
public class CircularBumperGadgetTest {
    String emptyBoardString;

    @Before
    public void setUp() throws Exception {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < 23; y++) {
            for(int x = 0; x < 22;x++)
                sb.append(' ');
            sb.append('\n');
        }
        emptyBoardString = sb.toString();
    }
    
    @Test
    public void testRenderNWCorner() {
        CircularBumperGadget gadget = new CircularBumperGadget("test", new Vect(0, 0));
        String renderedString = gadget.render(emptyBoardString);
      //  System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y*23+x) == 'O');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderNECorner() {
        CircularBumperGadget gadget = new CircularBumperGadget("test", new Vect(19, 0));
        String renderedString = gadget.render(emptyBoardString);
       // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 20 && y == 1)
                    assertTrue(renderedString.charAt(y*23+x) == 'O');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderSWCorner() {
        CircularBumperGadget gadget = new CircularBumperGadget("test", new Vect(0, 19));
        String renderedString = gadget.render(emptyBoardString);
      // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 20)
                    assertTrue(renderedString.charAt(y*23+x) == 'O');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderSECorner() {
        CircularBumperGadget gadget = new CircularBumperGadget("test", new Vect(19, 19));
        String renderedString = gadget.render(emptyBoardString);
       // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 20 && y == 20)
                    assertTrue(renderedString.charAt(y*23+x) == 'O');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testPerpendicularReflection() {
        CircularBumperGadget gadget = new CircularBumperGadget("test", new Vect(0,0));
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
        assertEquals(gadget.timeUntilCollision(ball), 0.0, 0.0001);
        gadget.reflect(ball);
        assert ball.getVelocity() == new Vect(1, 1);
        
        ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
        assert gadget.timeUntilCollision(ball) == 1;
        gadget.reflect(ball);
        assert ball.getVelocity().equals(new Vect(6, -1));
    }

    @Test
    public void testEquals() {
        CircularBumperGadget g1 = new CircularBumperGadget("Bilbo", new Vect(0, 0));
        CircularBumperGadget g2 = new CircularBumperGadget("Bilbo", new Vect(0, 0));
        CircularBumperGadget g3 = new CircularBumperGadget("Baggins", new Vect(5, 0));
        
        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
    }
}
