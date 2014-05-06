package pingball.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.TriangularBumperGadget.TriangularBumperOrientation;

/**
 * testing strategy:
 *   - test render for all four corners
 *   - test perpendicular reflection
 *   - test equals
 */
public class TriangularBumperGadgetTest {
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
    public void testRenderNWCorner(){
        TriangularBumperGadget gadget = new TriangularBumperGadget("test", new Vect(0,0), TriangularBumperOrientation.NW);
        String renderedString = gadget.render(emptyBoardString);
      //  System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y*23+x) == '/');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderSECorner(){
        TriangularBumperGadget gadget = new TriangularBumperGadget("test", new Vect(0,0), TriangularBumperOrientation.SE);
        String renderedString = gadget.render(emptyBoardString);
      //  System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y*23+x) == '/');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderNECorner(){
        TriangularBumperGadget gadget = new TriangularBumperGadget("test", new Vect(0,0), TriangularBumperOrientation.NE);
        String renderedString = gadget.render(emptyBoardString);
      //  System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y*23+x) == '\\');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderSWCorner(){
        TriangularBumperGadget gadget = new TriangularBumperGadget("test", new Vect(0,0), TriangularBumperOrientation.SW);
        String renderedString = gadget.render(emptyBoardString);
      //  System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y*23+x) == '\\');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testPerpendicularReflection() {
        TriangularBumperGadget gadget = new TriangularBumperGadget("test", new Vect(0,0), TriangularBumperOrientation.NW);
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
        assertEquals(gadget.timeUntilCollision(ball), ((3 + Math.sqrt(2)) / (4*(2 + Math.sqrt(2)))), 0.0001);
        gadget.reflect(ball);
        assert ball.getVelocity() == new Vect(1, 1);
        
        ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
        assert gadget.timeUntilCollision(ball) == 1;
        gadget.reflect(ball);
        assert ball.getVelocity().equals(new Vect(6, -1));
    }
    
    @Test
    public void testEquals() {
        TriangularBumperGadget g1 = new TriangularBumperGadget("Bilbo", new Vect(0, 0), TriangularBumperOrientation.NW);
        TriangularBumperGadget g2 = new TriangularBumperGadget("Bilbo", new Vect(0, 0), TriangularBumperOrientation.NW);
        TriangularBumperGadget g3 = new TriangularBumperGadget("Baggins", new Vect(5, 0), TriangularBumperOrientation.NW);
        
        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
    }
}
