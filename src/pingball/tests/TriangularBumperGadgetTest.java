package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static pingball.tests.TestHelpers.assertEqualsVect;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Ball;
import pingball.board.SquareBumper;
import pingball.board.TriangularBumper;
import pingball.board.TriangularBumper.TriangularBumperOrientation;

/**
 * testing strategy: - test render for all four corners - test perpendicular
 * reflection - test equals
 */
public class TriangularBumperGadgetTest {
    String emptyBoardString;

    @Before
    public void setUp() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 22; x++)
                sb.append(' ');
            sb.append('\n');
        }
        emptyBoardString = sb.toString();
    }

    @Test
    public void testRenderNWCorner() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "test");
        String renderedString = gadget.render(emptyBoardString);
        // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == '/');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderSECorner() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_RIGHT, "test");
        String renderedString = gadget.render(emptyBoardString);
        // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == '/');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderNECorner() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_RIGHT, "test");
        String renderedString = gadget.render(emptyBoardString);
        // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == '\\');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderSWCorner() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_LEFT, "test");
        String renderedString = gadget.render(emptyBoardString);
        // System.out.println(renderedString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y == 1)
                    assertTrue(renderedString.charAt(y * 23 + x) == '\\');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    
    // TOP_LEFT
    @Test
    public void testPerpendicularTLReflectionBottomRight() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(1, 1), ball.getVelocity());
    }
    @Test
    public void testPerpendicularTLReflectionTop() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -1), ball.getVelocity());
    }
    @Test
    public void testPerpendicularTLReflectionLeft() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(-1, 0.5), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-1, 0), ball.getVelocity());
    }
    @Test
    public void testCornerTLReflectionTopRight() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(2, -1), new Vect(-1, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(1, -1), ball.getVelocity());
    }
    @Test
    public void testCornerTLReflectionTopLeft() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(-1, -1), new Vect(1, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-1, -1), ball.getVelocity());
    }
    @Test
    public void testCornerTLReflectionBottomLeft() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(-1, 2), new Vect(1, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-1, 1), ball.getVelocity());
    }

    // TOP_RIGHT
    
    @Test
    public void testPerpendicularTRReflectionBottomLeft() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(0, 1), new Vect(1, -1));
        // assertEquals(gadget.leastCollisionTime(ball), 0, 0.0001);
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(-1, 1);
    }
    
    // BOTTOM_RIGHT
    
    @Test
    public void testPerpendicularBRReflectionTopLeft() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(0, 0), new Vect(1, 1));
        // assertEquals(gadget.leastCollisionTime(ball), 0, 0.0001);
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(-1, -1);
    }
    
    // BOTTOM_LEFT

    @Test
    public void testPerpendicularBLReflectionTopRight() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(1, 0), new Vect(-1, 1));
        // assertEquals(gadget.leastCollisionTime(ball), 0, 0.0001);
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(1, -1);
    }
}
