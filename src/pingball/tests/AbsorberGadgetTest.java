package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Absorber;
import pingball.board.Ball;

/**
 * Testing Strategy: 
 * -Test render for each of the four corners (NW, NE, SW, SE)
 * -Test reactBall method, which should reflect a ball the absorber. 
 *     -Create balls approaching the absorber from the left, right,
 *      top, bottom, NW corner, NE corner, SW corner, and SE corner.
 * -Test leastCollisionTime method.
 *      -test time until collision for balls that will hit the absorber
 *      from left, right, top, bottom, NW corner, NE corner, SW corner,
 *      and SE corner.
 */

public class AbsorberGadgetTest {
    String emptyBoardString;
    Absorber gadget;

    @Before
    public void setUp() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 22; x++)
                sb.append(' ');
            sb.append('\n');
        }
        emptyBoardString = sb.toString();
        
        gadget = new Absorber(new Vect(0, 0), 2, 2, "test");
    }

    // RENDER TESTS

    @Test
    public void testRenderNWCorner() {
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 1 && x <= 2 && y >= 1 && y <= 2)
                    assertTrue(renderedString.charAt(y * 23 + x) == '=');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderNECorner() {
        Absorber gadget = new Absorber(new Vect(18, 0), 2, 2, "test");
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 19 && x <= 20 && y >= 1 && y <= 2)
                    assertTrue(renderedString.charAt(y * 23 + x) == '=');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderSWCorner() {
        Absorber gadget = new Absorber(new Vect(0, 18), 2, 2, "test");
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 1 && x <= 2 && y >= 19 && y <= 20)
                    assertTrue(renderedString.charAt(y * 23 + x) == '=');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderSECorner() {
        Absorber gadget = new Absorber(new Vect(18, 18), 2, 2, "test");
        String renderedString = gadget.render(emptyBoardString);

        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x >= 19 && x <= 20 && y >= 19 && y <= 20)
                    assertTrue(renderedString.charAt(y * 23 + x) == '=');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    // REFLECTION TESTS

    public void testPerpendicularReflectionRight() {
        Ball ball = new Ball("ball", new Vect(3, 2), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(), new Vect(1, 0));
    }

    public void testPerpendicularReflectionBottom() {
        Ball ball = new Ball("ball", new Vect(2, 3), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(), new Vect(0, 1));
    }

    public void testPerpendicularReflectionBottomRight() {
        Ball ball = new Ball("ball", new Vect(3, 3), new Vect(-1, -1));
        gadget.reactBall(ball);
        assertEquals(ball.getVelocity(), new Vect(1, 1));
    }

    // LEAST COLLISION TIME TESTS

    @Test
    public void testLeastCollisionTimeRight() {
        Ball ball = new Ball("ball", new Vect(3, 2), new Vect(-1, 0));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeBottom() {
        Ball ball = new Ball("ball", new Vect(2, 3), new Vect(0, -1));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeBottomRight() {
        Ball ball = new Ball("ball", new Vect(3, 3), new Vect(-1, -1));
        assertEquals(0.8232, gadget.leastCollisionTime(ball), 0.0001);
    }

}
