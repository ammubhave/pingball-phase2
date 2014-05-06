package pingball.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.LeftFlipperGadget.VertexOrientation;
/**
 * testing strategy:
 * check for each of the 4 corners, both horizontal and vertical
 * also check for perpendicular reflection
 * - test equals
 */
public class LeftFlipperGadgetTest {
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
    public void testRenderHorizontalNWCorner() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(0, 0), VertexOrientation.NW);
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 1 && x <= 2 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-' || renderedString.charAt(y*23+x) == '|' || renderedString.charAt(y*23+x) == ' ');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderHorizontalNECorner() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(18, 0), VertexOrientation.NW);
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 19 && x <= 20 && y >= 1 && y <= 2)
                    assertTrue(renderedString.charAt(y*23+x) == '-' || renderedString.charAt(y*23+x) == '|' || renderedString.charAt(y*23+x) == ' ');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderHorizontalSWCorner() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(0, 19), VertexOrientation.NW);
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 1 && x <= 2 && y == 20)
                    assertTrue(renderedString.charAt(y*23+x) == '-' || renderedString.charAt(y*23+x) == '|' || renderedString.charAt(y*23+x) == ' ');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderHorizontalSECorner() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(18, 19), VertexOrientation.NW);
        String renderedString = gadget.render(emptyBoardString);
      
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 19 && x <= 20 && y == 20)
                    assertTrue(renderedString.charAt(y*23+x) == '-' || renderedString.charAt(y*23+x) == '|' || renderedString.charAt(y*23+x) == ' ');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }

    @Test
    public void testRenderVerticalNWCorner() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(0, 0), VertexOrientation.NW);
        gadget.doAction();
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y >= 1 && y <= 2 && x >= 1 && x <= 2)
                    assertTrue(renderedString.charAt(y*23+x) == '-' || renderedString.charAt(y*23+x) == '|' || renderedString.charAt(y*23+x) == ' ');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderVerticalNECorner() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(18, 0), VertexOrientation.NW);
        String renderedString = gadget.render(emptyBoardString);
        gadget.doAction();
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 19 && y >= 1 && y <= 2)
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderVerticalSWCorner() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(0, 18), VertexOrientation.NW);
        gadget.doAction();
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 1 && x <= 2  && y >= 19 && y <= 20)
                    assertTrue(renderedString.charAt(y*23+x) == '-' || renderedString.charAt(y*23+x) == '|' || renderedString.charAt(y*23+x) == ' ');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderVerticalSECorner() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(18, 18), VertexOrientation.NW);
        String renderedString = gadget.render(emptyBoardString);
        gadget.doAction();
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 19 && y >= 19 && y <= 20)
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testPerpendicularReflection() {
        LeftFlipperGadget gadget = new LeftFlipperGadget("test", new Vect(0,0), VertexOrientation.NW);
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
        // assertEquals(gadget.timeUntilCollision(ball), 3/4., 0.0001);
        gadget.reflect(ball);
        assert ball.getVelocity() == new Vect(1, 1);
        
        ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
        assert gadget.timeUntilCollision(ball) == 1;
        gadget.reflect(ball);
        assert ball.getVelocity().equals(new Vect(6, -1));
    }

    @Test
    public void testEquals() {
        LeftFlipperGadget g1 = new LeftFlipperGadget("Bilbo", new Vect(0, 0), VertexOrientation.NW);
        LeftFlipperGadget g2 = new LeftFlipperGadget("Bilbo", new Vect(0, 0), VertexOrientation.NW);
        LeftFlipperGadget g3 = new LeftFlipperGadget("Baggins", new Vect(5, 0), VertexOrientation.NW);
        
        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
    }
}
