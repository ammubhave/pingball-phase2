package pingball.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Flipper.FlipperOrientation;
import pingball.board.RightFlipper;
/**
 * testing strategy:
 * check for each of the 4 corners, both horizontal and vertical
 * also check for perpendicular reflection
 * - test equals
 */
public class RightFlipperGadgetTest {
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
    public void testRenderSWCorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderNWCorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y == 1 && x >= 1 && x <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderNECorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 2 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderSECorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y == 2 && x >= 1 && x <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderMutatedSWCorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        gadget.moveFlipper();
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y == 2 && x >= 1 && x <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderMutatedNWCorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        gadget.moveFlipper();
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderMutatedNECorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        gadget.moveFlipper();
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y == 1 && x >= 1 && x <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderMutatedSECorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        gadget.moveFlipper();
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 2 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
}
