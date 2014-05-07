package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Ball;
import pingball.board.SquareBumper;

/**
 * testing strategy: - test render for all four corners - test perpendicular
 * reflection - test equals
 */
public class SquareBumperGadgetTest {

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

    @Test
    public void testPerpendicularReflection() {
        SquareBumper gadget = new SquareBumper(new Vect(0, 0), "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
        assertEquals(gadget.leastCollisionTime(ball), 3 / 4.0, 0.0001);
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(1, 1);

        ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
        assert gadget.leastCollisionTime(ball) == 1;
        gadget.reactBall(ball);
        assert ball.getVelocity().equals(new Vect(6, -1));
    }

    @Test
    public void testEquals() {
        SquareBumper g1 = new SquareBumper(new Vect(0, 0), "Bilbo");
        SquareBumper g2 = new SquareBumper(new Vect(0, 0), "Bilbo");
        SquareBumper g3 = new SquareBumper(new Vect(5, 0), "Baggins");

        assertEquals(g1, g2);
        assertNotEquals(g1, g3);
    }
}
