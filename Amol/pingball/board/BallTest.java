package pingball.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import physics.Circle;
import physics.Vect;

/**
 * Test the ball rendering on all four corners
 * Test getCircle, equals
 */
public class BallTest {
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
        Ball ball = new Ball("test",new Vect(0, 0),new Vect(1, 1));
        String renderedString = ball.render(emptyBoardString);
       //System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y*23+x) == '*');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
        
    } @Test
    public void testRenderNECorner() {
        Ball ball = new Ball("test",new Vect(19, 0), new Vect(1,1));
        String renderedString = ball.render(emptyBoardString);
       // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 20 && y == 1)
                    assertTrue(renderedString.charAt(y*23+x) == '*');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderSWCorner() {
        Ball ball = new Ball("test",new Vect(0, 19), new Vect(1,1));
        String renderedString = ball.render(emptyBoardString);
        //System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 20)
                    assertTrue(renderedString.charAt(y*23+x) == '*');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testRenderSECorner() {
        Ball ball = new Ball("test",new Vect(19, 19), new Vect(1,1));
        String renderedString = ball.render(emptyBoardString);
        //System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 20 && y == 20)
                    assertTrue(renderedString.charAt(y*23+x) == '*');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testGetCircle() {
        Ball ball = new Ball("test",new Vect(19, 19), new Vect(1,1));
        Circle newCircle = ball.getCircle();
        assertEquals(newCircle, new Circle(new Vect(19,19), 0.25));
    }
    
    @Test
    public void testEquals() {
        Ball ball1 = new Ball("Bilbo", new Vect(0, 0), new Vect(1, 1));
        Ball ball2 = new Ball("Bilbo", new Vect(2, 0), new Vect(6, 1));
        Ball ball3 = new Ball("Baggins", new Vect(5, 0), new Vect(7, 1));
        
        assertEquals(ball1, ball2);
        assertNotEquals(ball1, ball3);
    }
}
