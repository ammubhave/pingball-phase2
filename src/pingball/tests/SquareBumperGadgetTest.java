package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pingball.tests.TestHelpers.assertEqualsVect;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Ball;
import pingball.board.SquareBumper;

/**
 * Testing Strategy: 
 * -Test render for each of the four corners (NW, NE, SW, SE)
 * -Test reactBall method, which should reflect a ball the square
 *  bumper. 
 *     -Create balls approaching the square bumper from the left, right,
 *      top, bottom, NW corner, NE corner, SW corner, and SE corner.
 * -Test leastCollisionTime method.
 *      -Create balls that will hit the square bumper from left, right, 
 *      top, bottom, NW corner, NE corner, SW corner, and SE corner. Also, 
 *      includes a ball currently touching the square bumper.
 */

public class SquareBumperGadgetTest {

    String emptyBoardString;
    SquareBumper gadget;

    @Before
    public void setUp() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 22; x++)
                sb.append(' ');
            sb.append('\n');
        }
        emptyBoardString = sb.toString();
        gadget = new SquareBumper(new Vect(5, 5), "test");
    }

    //RENDER TESTS
    
    @Test
    public void testRenderNWCorner() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == '#');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderNECorner() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == '#');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderSWCorner() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == '#');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderSECorner() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == '#');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    //REACT BALL TESTS
    
    @Test
    public void testPerpendicularReflectionBottomRight() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        Ball ball = new Ball("ball", new Vect(2, 2), new Vect(-1, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(1, 1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionBottomLeft() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        Ball ball = new Ball("ball", new Vect(-1, 2), new Vect(1, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-1, 1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionTopLeft() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        Ball ball = new Ball("ball", new Vect(-1, -1), new Vect(1, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-1, -1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionTopRight() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        Ball ball = new Ball("ball", new Vect(2, -1), new Vect(-1, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(1, -1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionBottomSide() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        Ball ball = new Ball("ball", new Vect(0.5, 2), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionLeftSide() {
        SquareBumper gadget = new SquareBumper(new Vect(1, 1), "test");
        Ball ball = new Ball("ball", new Vect(1, 1.5), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-1, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionTopSide() {
        SquareBumper gadget = new SquareBumper(new Vect(1, 1), "test");
        Ball ball = new Ball("ball", new Vect(1.5, 1), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(1, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionRightSide() {
        SquareBumper gadget = new SquareBumper(new Vect(1, 1), "test");
        Ball ball = new Ball("ball", new Vect(2, 1.5), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(1, 0), ball.getVelocity());
    }

    // LEAST COLLISION TIME TESTS

    @Test
    public void testLeastCollisionTimeZero() {
        Ball ball = new Ball("ball", new Vect(4.75, 5), new Vect(1, 0));
        assertEquals(0, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeTop() {
        Ball ball = new Ball("ball", new Vect(5, 4), new Vect(0, 1));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeLeft() {
        Ball ball = new Ball("ball", new Vect(4, 5), new Vect(1, 0));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeRight() {
        Ball ball = new Ball("ball", new Vect(6, 5), new Vect(-1, 0));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeBottom() {
        Ball ball = new Ball("ball", new Vect(5, 6), new Vect(0, -1));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeTopRight() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(-1, 1));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeTopLeft() {
        Ball ball = new Ball("ball", new Vect(4, 4), new Vect(1, 1));
        assertEquals(0.8232, gadget.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeBottomRight() {
        Ball ball = new Ball("ball", new Vect(7, 7), new Vect(-1, -1));
        assertEquals(0.8232, gadget.leastCollisionTime(ball), 0.0001);

    }

    @Test
    public void testLeastCollisionTimeBottomLeft() {
        Ball ball = new Ball("ball", new Vect(4, 6), new Vect(1, -1));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }

}