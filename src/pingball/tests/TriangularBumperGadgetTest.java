package pingball.tests;

import static org.junit.Assert.assertTrue;
import static pingball.tests.TestHelpers.assertEqualsVect;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Ball;
import pingball.board.TriangularBumper;
import pingball.board.TriangularBumper.TriangularBumperOrientation;

/**
 * Testing Strategy:
 * 
 * -Test render for each of the three corners depending on its orientation
 * 
 * -Test the triangular bumper for all orientations (TOP_LEFT, TOP_RIGHT,
 * BOTTOM_LEFT, BOTTOM_RIGHT
 * 
 * -Test reactBall method, which should reflect a ball the triangular bumper.
 * 
 * -Create balls approaching the triangular bumper from the left, right, top,
 * bottom, NW corner, NE corner, SW corner, and SE corner.
 * 
 * -Create balls that will hit the triangular bumper from left, right, top,
 * bottom, NW corner, NE corner, SW corner, and SE corner. Also, includes a ball
 * currently touching the triangular bumper.
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
    public void testPerpendicularTRReflectionHypotenuse() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(0, 1), new Vect(1, -1));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(-1, 1);
    }

    @Test
    public void testPerpendicularTRReflectionTopLeg() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(0.5, 0), new Vect(0, 1));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(0, -1);
    }

    @Test
    public void testPerpendicularTRReflectionLeftLeg() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(1, 0.5), new Vect(-1, 0));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(1, 0);
    }

    // BOTTOM_RIGHT

    @Test
    public void testPerpendicularBRReflectionHypotenuse() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(0, 0), new Vect(1, 1));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(-1, -1);
    }

    @Test
    public void testPerpendicularBRReflectionBottomLeg() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(0.5, 1), new Vect(0, 1));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(0, -1);
    }

    @Test
    public void testPerpendicularBRReflectionRightLeg() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(1, 0.5), new Vect(-1, 0));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(1, 0);
    }

    // BOTTOM_LEFT

    @Test
    public void testPerpendicularBLReflectionHypotenuse() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(1, 0), new Vect(-1, 1));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(1, -1);
    }

    @Test
    public void testPerpendicularBLReflectionLeftLeg() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(0, 0.5), new Vect(1, 0));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(-1, 0);
    }

    @Test
    public void testPerpendicularBLReflectionBottomLeg() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.BOTTOM_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(1, 0.5), new Vect(0, -1));
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(0, 1);
    }
}
