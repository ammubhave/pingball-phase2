package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Ball;
import pingball.board.CircularBumper;

/**
 * testing strategy: - test render for all four corners - test perpendicular
 * reflection - test equals
 */
public class CircularBumperGadgetTest {
    String emptyBoardString;
    CircularBumper gadget;

    @Before
    public void setUp() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 22; x++)
                sb.append(' ');
            sb.append('\n');
        }
        emptyBoardString = sb.toString();
        gadget = new CircularBumper(new Vect(5, 5), "test");
    }

    @Test
    public void testRenderNWCorner() {
        CircularBumper gadget = new CircularBumper(new Vect(0, 0), "test");
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == 'O');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderNECorner() {
        CircularBumper gadget = new CircularBumper(new Vect(19, 0), "test");
        String renderedString = gadget.render(emptyBoardString);
        // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 20 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == 'O');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderSWCorner() {
        CircularBumper gadget = new CircularBumper(new Vect(0, 19), "test");
        String renderedString = gadget.render(emptyBoardString);
        // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 20)
                    assertTrue(renderedString.charAt(y * 23 + x) == 'O');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderSECorner() {
        CircularBumper gadget = new CircularBumper(new Vect(19, 19), "test");
        String renderedString = gadget.render(emptyBoardString);
        // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 20 && y == 20)
                    assertTrue(renderedString.charAt(y * 23 + x) == 'O');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testPerpendicularReflectionTop() {
        Ball ball = new Ball("ball", new Vect(5, 4), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(0, -1));
    }
    
    @Test
    public void testPerpendicularReflectionLeft() {
        Ball ball = new Ball("ball", new Vect(4, 5), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(-1, 0));
    }
    
    @Test
    public void testPerpendicularReflectionRight() {
        Ball ball = new Ball("ball", new Vect(6, 5), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(1, 0));
    }
    
    @Test
    public void testPerpendicularReflectionBottom() {
        Ball ball = new Ball("ball", new Vect(5, 6), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(0, 1));
    }
    
    @Test
    public void testPerpendicularReflectionTopRight() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(-1, 1));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(1, -1));
    }
    
    @Test
    public void testPerpendicularReflectionTopLeft() {
        Ball ball = new Ball("ball", new Vect(4, 4), new Vect(1, 1));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(-1, -1));
    }
    
    @Test
    public void testPerpendicularReflectionBottomRight() {
        Ball ball = new Ball("ball", new Vect(6, 6), new Vect(-1, -1));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(1, 1));
    }
    
    @Test
    public void testPerpendicularReflectionBottomLeft() {
        Ball ball = new Ball("ball", new Vect(4, 6), new Vect(1, -1));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(-1, 1));
    }
    


    @Test
    public void testEquals() {
        CircularBumper g1 = new CircularBumper(new Vect(0, 0), "Bilbo");
        CircularBumper g2 = new CircularBumper(new Vect(0, 0), "Bilbo");
        CircularBumper g3 = new CircularBumper(new Vect(5, 0), "Baggins");

        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
    }
}
