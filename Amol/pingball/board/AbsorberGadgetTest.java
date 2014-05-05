package pingball.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;

/**
 * testing strategy:
 *   - test render for all four corners
 *   - test perpendicular reflection
 *   - test equals
 */
public class AbsorberGadgetTest {
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
        AbsorberGadget gadget = new AbsorberGadget("test", new Vect(0, 0), 2, 2);
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 1 && x <= 2 && y >= 1 && y <= 2)
                    assertTrue(renderedString.charAt(y*23+x) == '=');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderNECorner() {
        AbsorberGadget gadget = new AbsorberGadget("test", new Vect(18, 0), 2, 2);
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 19 && x <= 20 && y >= 1 && y <= 2)
                    assertTrue(renderedString.charAt(y*23+x) == '=');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderSWCorner() {
        AbsorberGadget gadget = new AbsorberGadget("test", new Vect(0, 18), 2, 2);
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 1 && x <= 2 && y >= 19 && y <= 20)
                    assertTrue(renderedString.charAt(y*23+x) == '=');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderSECorner() {
        AbsorberGadget gadget = new AbsorberGadget("test", new Vect(18, 18), 2, 2);
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 19 && x <= 20 && y >= 19 && y <= 20)
                    assertTrue(renderedString.charAt(y*23+x) == '=');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testPerpendicularReflection() {
        AbsorberGadget gadget = new AbsorberGadget("test", new Vect(0,0), 2,2);
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
        assertEquals(gadget.timeUntilCollision(ball), 3/4., 0.0001);
        gadget.reflect(ball);
        assert ball.getVelocity() == new Vect(1, 1);
        
        ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
        assert gadget.timeUntilCollision(ball) == 1;
        gadget.reflect(ball);
        assert ball.getVelocity().equals(new Vect(6, -1));
    }
    
    @Test
    public void testEquals() {
        AbsorberGadget g1 = new AbsorberGadget("Bilbo", new Vect(0, 0), 1, 1);
        AbsorberGadget g2 = new AbsorberGadget("Bilbo", new Vect(0, 0), 1, 1);
        AbsorberGadget g3 = new AbsorberGadget("Baggins", new Vect(5, 0), 1, 1);
        
        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
    }
}
