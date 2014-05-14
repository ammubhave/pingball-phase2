package pingball.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import physics.Circle;
import physics.Vect;
import pingball.board.Ball;


/**
 * Testing Strategy: 
 * -Test render for each of the four corners (NW, NE, SW, SE)
 * -Test getPos method
 * -Test getVelocity method
 * -Test getName method
 * -Test getCircle method
 * -Test changePos method
 * -Test changeVelocity method
 * 
 */
public class BallTest {

    String emptyBoardString;
    Ball ball;

    @Before
    public void setUp() throws Exception {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < 23; y++) {
            for(int x = 0; x < 22;x++)
                sb.append(' ');
            sb.append('\n');
        }
        emptyBoardString = sb.toString();
        ball = new Ball("Bob", new Vect(10,10), new Vect(-1,1));
    }
    
    //RENDER TESTS
    
    @Test
    public void testRenderNWCorner() {
        Ball ball = new Ball("test",new Vect(0, 0),new Vect(1, 1));
        String renderedString = ball.render(emptyBoardString);
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
    public void testGetPos(){
        assertEquals(new Vect(10,10), ball.getPos());
    }
    
    @Test
    public void testGetVelocity(){
        assertEquals(new Vect(-1,1), ball.getVelocity());
    }
    
    @Test
    public void testGetName(){
        assertEquals("Bob", ball.getName());
    }
    
    @Test
    public void testGetCircle(){
        assertEquals(new Circle(new Vect(10,10), 0.25), ball.getCircle());
    }
    
    @Test
    public void testChangePos(){
        ball.changePos(new Vect(15,15));
        assertEquals(new Vect(15,15), ball.getPos());
    }
    
    @Test
    public void testChangeVelocity(){
        ball.changeVelocity(new Vect(1,1));
        assertEquals(new Vect(1,1), ball.getVelocity());
    }
    
}
