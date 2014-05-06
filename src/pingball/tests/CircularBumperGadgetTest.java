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
        CircularBumper gadget = new CircularBumper(new Vect(0, 0), "test");
        String renderedString = gadget.render(emptyBoardString);
        // System.out.println(renderedString);
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
    public void testPerpendicularReflection() {
        CircularBumper gadget = new CircularBumper(new Vect(0, 0), "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
        assertEquals(gadget.leastCollisionTime(ball), 0.0, 0.0001);
        gadget.reactBall(ball);
        assert ball.getVelocity() == new Vect(1, 1);

        ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
        assert gadget.leastCollisionTime(ball) == 1;
        gadget.reactBall(ball);
        assert ball.getVelocity().equals(new Vect(6, -1));
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
