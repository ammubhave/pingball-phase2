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
 * -Test reactBall method, which should absorb the first ball.
 *     -Create balls approaching the absorber from the left, right,
 *      top, bottom, NW corner, NE corner, SW corner, and SE corner.
 * -Test reactBall method, which should reflect if it's holding a ball.
 *     -Create balls approaching the absorber from the left, right,
 *      top, bottom, NW corner, NE corner, SW corner, and SE corner
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
        
        gadget = new Absorber(new Vect(5, 5), 2, 2, "test");
    }

    // RENDER TESTS

    @Test
    public void testRenderNWCorner() {
        Absorber gadget = new Absorber(new Vect(0, 0), 2, 2, "test");
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

    // REACH BALL TESTS (SHOULD ABSORB; ONLY ONE BALL)

    @Test
    public void testReactBallAbsorbTop() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 0), ball.getVelocity());
        assertEquals(new Vect(6.75, 6.75), ball.getPos());
    }
    
    @Test
    public void testReactBallAbsorbBottom() {
        Ball ball = new Ball("ball", new Vect(6, 8), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 0), ball.getVelocity());
        assertEquals(new Vect(6.75, 6.75), ball.getPos());
    }
    
    @Test
    public void testReactBallAbsorbRight() {
        Ball ball = new Ball("ball", new Vect(8, 6), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 0), ball.getVelocity());
        assertEquals(new Vect(6.75, 6.75), ball.getPos());
    }
    
    @Test
    public void testReactBallAbsorbLeft() {
        Ball ball = new Ball("ball", new Vect(4, 6), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 0), ball.getVelocity());
        assertEquals(new Vect(6.75, 6.75), ball.getPos());
    }
    
    @Test
    public void testReactBallAbsorbNW() {
        Ball ball = new Ball("ball", new Vect(4, 4), new Vect(1, 1));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 0), ball.getVelocity());
        assertEquals(new Vect(6.75, 6.75), ball.getPos());
    }
    
    @Test
    public void testReactBallAbsorbNE() {
        Ball ball = new Ball("ball", new Vect(8, 4), new Vect(-1, 1));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 0), ball.getVelocity());
        assertEquals(new Vect(6.75, 6.75), ball.getPos());
    }
    
    @Test
    public void testReactBallAbsorbSW() {
        Ball ball = new Ball("ball", new Vect(4, 8), new Vect(1, -1));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 0), ball.getVelocity());
        assertEquals(new Vect(6.75, 6.75), ball.getPos());
    }
    
    @Test
    public void testReactBallAbsorbSE() {
        Ball ball = new Ball("ball", new Vect(8, 8), new Vect(-1, -1));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 0), ball.getVelocity());
        assertEquals(new Vect(6.75, 6.75), ball.getPos());
    }
    
    //REACT BALL TEST (SHOULD REFLECT; 2 BALLS)
    
    @Test
    public void testReactBallReflectTop() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        Ball ball2 = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        gadget.reactBall(ball);
        gadget.reactBall(ball2);
        assertEquals(new Vect(0, -1), ball2.getVelocity());
    }
    
    @Test
    public void testReactBallReflectBottom() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        Ball ball2 = new Ball("ball", new Vect(6, 8), new Vect(0, -1));
        gadget.reactBall(ball);
        gadget.reactBall(ball2);
        assertEquals(new Vect(0, 1), ball2.getVelocity());
    }
    
    @Test
    public void testReactBallReflectLeft() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        Ball ball2 = new Ball("ball", new Vect(4, 6), new Vect(1, 0));
        gadget.reactBall(ball);
        gadget.reactBall(ball2);
        assertEquals(new Vect(-1, 0), ball2.getVelocity());
    }
    
    @Test
    public void testReactBallReflectRight() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        Ball ball2 = new Ball("ball", new Vect(8, 6), new Vect(-1, 0));
        gadget.reactBall(ball);
        gadget.reactBall(ball2);
        assertEquals(new Vect(1, 0), ball2.getVelocity());
    }
    @Test
    public void testReactBallReflectNW() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        Ball ball2 = new Ball("ball", new Vect(4, 4), new Vect(1, 1));
        gadget.reactBall(ball);
        gadget.reactBall(ball2);
        assertEquals(new Vect(-1, -1), ball2.getVelocity());
    }
    
    @Test
    public void testReactBallReflectNE() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        Ball ball2 = new Ball("ball", new Vect(8, 4), new Vect(-1, 1));
        gadget.reactBall(ball);
        gadget.reactBall(ball2);
        assertEquals(new Vect(1, -1), ball2.getVelocity());
    }
    
    @Test
    public void testReactBallReflectSW() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        Ball ball2 = new Ball("ball", new Vect(4, 8), new Vect(1, -1));
        gadget.reactBall(ball);
        gadget.reactBall(ball2);
        assertEquals(new Vect(-1, 1), ball2.getVelocity());
    }
    
    @Test
    public void testReactBallReflectSE() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        Ball ball2 = new Ball("ball", new Vect(8, 8), new Vect(-1, -1));
        gadget.reactBall(ball);
        gadget.reactBall(ball2);
        assertEquals(new Vect(1, 1), ball2.getVelocity());
    }
    
    // LEAST COLLISION TIME TESTS

    @Test
    public void testLeastCollisionTimeTop() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(0, 1));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }
    
    @Test
    public void testLeastCollisionTimeBottom() {
        Ball ball = new Ball("ball", new Vect(6, 8), new Vect(0, -1));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }
    
    @Test
    public void testLeastCollisionTimeLeft() {
        Ball ball = new Ball("ball", new Vect(4, 6), new Vect(1, 0));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }
    
    @Test
    public void testLeastCollisionTimeRight() {
        Ball ball = new Ball("ball", new Vect(8, 6), new Vect(-1, 0));
        assertEquals(0.75, gadget.leastCollisionTime(ball), 0.0001);
    }
    
    @Test
    public void testLeastCollisionTimeNW() {
        Ball ball = new Ball("ball", new Vect(4, 4), new Vect(1, 1));
        assertEquals(0.8232, gadget.leastCollisionTime(ball), 0.0001);
    }
    
    @Test
    public void testLeastCollisionTimeNE() {
        Ball ball = new Ball("ball", new Vect(8, 4), new Vect(-1, 1));
        assertEquals(0.8232, gadget.leastCollisionTime(ball), 0.0001);
    }
    
    @Test
    public void testLeastCollisionTimeSW() {
        Ball ball = new Ball("ball", new Vect(4, 8), new Vect(1, -1));
        assertEquals(0.8232, gadget.leastCollisionTime(ball), 0.0001);
    }
    
    @Test
    public void testLeastCollisionTimeSE() {
        Ball ball = new Ball("ball", new Vect(8, 8), new Vect(-1, -1));
        assertEquals(0.8232, gadget.leastCollisionTime(ball), 0.0001);
    }

}
