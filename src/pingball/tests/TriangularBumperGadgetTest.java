package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Ball;
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

    @Test
    public void testPerpendicularReflection() {
        TriangularBumper gadget = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
        assertEquals(gadget.leastCollisionTime(ball), ((3 + Math.sqrt(2)) / (4 * (2 + Math.sqrt(2)))), 0.0001);
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(1, 1);

        ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
        assert gadget.leastCollisionTime(ball) == 1;
        gadget.reactBall(ball);
        assert ball.getVelocity().equals(new Vect(6, -1));
    }

    @Test
    public void testEquals() {
        TriangularBumper g1 = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "Bilbo");
        TriangularBumper g2 = new TriangularBumper(new Vect(0, 0), TriangularBumperOrientation.TOP_LEFT, "Bilbo");
        TriangularBumper g3 = new TriangularBumper(new Vect(5, 0), TriangularBumperOrientation.TOP_LEFT, "Baggins");

        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
    }
}