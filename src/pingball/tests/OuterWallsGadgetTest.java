package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Ball;
import pingball.board.OuterWall;
import pingball.board.OuterWall.OuterWallsOrientation;

/**
 * Testing Strategy: 
 * -Test render for each of the four sides (Top, Left, Bottom, Right) with 
 *  and without neighbors names. 
 *     -total of 8 tests for render 
 * -Test reactBall method, which should reflect a ball the outer wall. 
 *     -Create balls approaching the outer wall from the left, right, top, bottom. 
 *     For each direction, test both transparent wall with a neighbor and a solid 
 *     wall without a neighbor. 
 * -Test leastCollisionTime method. 
 *     -Create balls approaching the outer wall from the left, right, top, bottom. 
 *     For each direction, test both transparent wall with a neighbor and a solid 
 *     wall without a neighbor. 
 */
public class OuterWallsGadgetTest {
    String emptyBoardString;
    OuterWall topWall;
    OuterWall bottomWall;
    OuterWall rightWall;
    OuterWall leftWall;

    @Before
    public void setUp() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 22; x++)
                sb.append(' ');
            sb.append('\n');
        }
        emptyBoardString = sb.toString();
        topWall = new OuterWall(new Vect(0, 0),
                OuterWallsOrientation.HORIZONTAL, "wall");
        leftWall = new OuterWall(new Vect(0, 0),
                OuterWallsOrientation.VERTICAL, "wall");
        rightWall = new OuterWall(new Vect(21, 0),
                OuterWallsOrientation.VERTICAL, "wall");
        bottomWall = new OuterWall(new Vect(0, 22),
                OuterWallsOrientation.HORIZONTAL, "wall");

    }

    // RENDER TESTS

    @Test
    public void testRenderNoNameTopWall() {
        topWall.setNeighborName("");
        String renderedString = topWall.render(emptyBoardString);
        assertEquals("......................\n",
                renderedString.substring(0, 23));
        for (int y = 1; y < 23; y++) {
            for (int x = 0; x < 23; x++) {
                if (x == 22)
                    assertTrue(renderedString.charAt(y * 23 + x) == '\n');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderWithNameTopWall() {
        topWall.setNeighborName("Jimmy Neutron");
        String renderedString = topWall.render(emptyBoardString);
        assertEquals("....Jimmy Neutron.....\n",
                renderedString.substring(0, 23));
        for (int y = 1; y < 23; y++) {
            for (int x = 0; x < 23; x++) {
                if (x == 22)
                    assertTrue(renderedString.charAt(y * 23 + x) == '\n');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderNoNameLeftWall() {
        leftWall.setNeighborName("");
        String renderedString = leftWall.render(emptyBoardString);
        System.out.println(renderedString);
        for (int y = 0; y < 22; y++) {
            assertEquals("Y: " + String.valueOf(y), '.',
                    renderedString.charAt(y * 23 + 0));
        }

        for (int y = 0; y < 23; y++) {
            for (int x = 1; x < 23; x++) {
                if (x == 22)
                    assertTrue(renderedString.charAt(y * 23 + x) == '\n');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderWithNameLeftWall() {
        leftWall.setNeighborName("Jimmy Neutron");
        String renderedString = leftWall.render(emptyBoardString);
        for (int y = 0; y < 4; y++) {
            assertEquals("Y: " + String.valueOf(y), '.',
                    renderedString.charAt(y * 23 + 0));
        }
        for (int y = 4; y < 17; y++) {
            assertEquals("Y: " + String.valueOf(y),
                    "Jimmy Neutron".charAt(y - 4),
                    renderedString.charAt(y * 23 + 0));
        }
        for (int y = 17; y < 22; y++) {
            assertEquals("Y: " + String.valueOf(y), '.',
                    renderedString.charAt(y * 23 + 0));
        }
        for (int y = 0; y < 23; y++) {
            for (int x = 1; x < 23; x++) {
                if (x == 22)
                    assertTrue(renderedString.charAt(y * 23 + x) == '\n');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderNoNameBottomWall() {
        bottomWall.setNeighborName("");
        String renderedString = bottomWall.render(emptyBoardString);
        assertEquals("......................\n",
                renderedString.substring(23 * 22, 23 * 23));
        for (int y = 0; y < 22; y++) {
            for (int x = 0; x < 23; x++) {
                if (x == 22)
                    assertTrue(renderedString.charAt(y * 23 + x) == '\n');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderWithNameBottomWall() {
        bottomWall.setNeighborName("Jimmy Neutron");
        String renderedString = bottomWall.render(emptyBoardString);
        assertEquals("....Jimmy Neutron.....\n",
                renderedString.substring(23 * 22, 23 * 23));
        for (int y = 0; y < 22; y++) {
            for (int x = 0; x < 23; x++) {
                if (x == 22)
                    assertTrue(renderedString.charAt(y * 23 + x) == '\n');
                else
                    assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
        }
    }

    @Test
    public void testRenderNoNameRightWall() {
        rightWall.setNeighborName("");
        String renderedString = rightWall.render(emptyBoardString);
        System.out.println(renderedString);

        for (int y = 0; y < 22; y++) {
            for (int x = 0; x < 21; x++) {
                assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
            assertEquals("Y: " + String.valueOf(y), '.',
                    renderedString.charAt(y * 23 + 21));
            assertTrue(renderedString.charAt(y * 23 + 22) == '\n');
        }
    }

    @Test
    public void testRenderWithNameRightWall() {
        rightWall.setNeighborName("Jimmy Neutron");
        String renderedString = rightWall.render(emptyBoardString);
        for (int y = 0; y < 4; y++) {
            assertEquals("Y: " + String.valueOf(y), '.',
                    renderedString.charAt(y * 23 + 21));
        }
        for (int y = 4; y < 17; y++) {
            assertEquals("Y: " + String.valueOf(y),
                    "Jimmy Neutron".charAt(y - 4),
                    renderedString.charAt(y * 23 + 21));
        }
        for (int y = 17; y < 22; y++) {
            assertEquals("Y: " + String.valueOf(y), '.',
                    renderedString.charAt(y * 23 + 21));
        }
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 21; x++) {
                assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }

            assertTrue(renderedString.charAt(y * 23 + 22) == '\n');
        }
    }

    // REACT BALL TESTS

    @Test
    public void testPerpendicularReflectionTop() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, -1));
        topWall.reactBall(ball);
        assertEquals(new Vect(0, 1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionTransparentTop() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, -1));
        topWall.setNeighborName("suryo");
        topWall.reactBall(ball);
        assertEquals(new Vect(0, -1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionLeft() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(-1, 0));
        leftWall.reactBall(ball);
        assertEquals(new Vect(1, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionTransparentLeft() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(-1, 0));
        leftWall.setNeighborName("suryo");
        leftWall.reactBall(ball);
        assertEquals(new Vect(-1, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionBottom() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, 1));
        bottomWall.reactBall(ball);
        assertEquals(new Vect(0, -1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionTransparentBottom() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, 1));
        bottomWall.setNeighborName("suryo");
        bottomWall.reactBall(ball);
        assertEquals(new Vect(0, 1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionRight() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(1, 0));
        rightWall.reactBall(ball);
        assertEquals(new Vect(-1, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularReflectionTransparentRight() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(1, 0));
        rightWall.setNeighborName("suryo");
        rightWall.reactBall(ball);
        assertEquals(new Vect(1, 0), ball.getVelocity());
    }

    // LEAST COLLISION TIME TESTS

    @Test
    public void testLeastCollisionTimeTop() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, -1));
        assertEquals(4.75, topWall.leastCollisionTime(ball), 0.001);
    }

    @Test
    public void testLeastCollisionTimeTransparentTop() {
        topWall.setNeighborName("cindyo");
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, -1));
        assertTrue(topWall.leastCollisionTime(ball) > 10000);
    }

    @Test
    public void testLeastCollisionTimeLeft() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(-1, 0));
        assertEquals(4.75, leftWall.leastCollisionTime(ball), 0.001);
    }

    @Test
    public void testLeastCollisionTimeTransparentLeft() {
        leftWall.setNeighborName("cindyo");
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(-1, 0));
        assertTrue(leftWall.leastCollisionTime(ball) > 10000);
    }

    @Test
    public void testLeastCollisionTimeBottom() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, 1));
        assertEquals(16.75, bottomWall.leastCollisionTime(ball), 0.001);
    }

    @Test
    public void testLeastCollisionTimeTransparentBottom() {
        bottomWall.setNeighborName("cindyo");
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, 1));
        System.out.println(bottomWall.leastCollisionTime(ball));
        assertTrue(bottomWall.leastCollisionTime(ball) > 10000);
    }

    @Test
    public void testLeastCollisionTimeRight() {
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(1, 0));
        assertEquals(14.75, rightWall.leastCollisionTime(ball), 0.001);
    }

    @Test
    public void testLeastCollisionTimeTransparentRight() {
        rightWall.setNeighborName("cindyo");
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(1, 0));
        assertTrue(rightWall.leastCollisionTime(ball) > 10000);
    }
    

    @Test
    public void testPerpendicularReflection() {
        OuterWall gadget = new OuterWall(new Vect(0, 0),
                OuterWallsOrientation.HORIZONTAL, "wall");
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, -1));
        assertEquals(4.75, gadget.leastCollisionTime(ball), 0.001);
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 1), ball.getVelocity());
    }

    @Test
    public void testPerpendicularTransparentReflection() {
        OuterWall gadget = new OuterWall(new Vect(0, 0),
                OuterWallsOrientation.HORIZONTAL, "wall");
        gadget.setNeighborName("ne");
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEquals(new Vect(0, -1), ball.getVelocity());
    }

    @Test
    public void testAngledReflection() {
        OuterWall gadget = new OuterWall(new Vect(0, 0),
                OuterWallsOrientation.HORIZONTAL, "wall");
        Ball ball = new Ball("ball", new Vect(10, 5), new Vect(-1, -1));
        assertEquals(4.75, gadget.leastCollisionTime(ball), 0.001);
        gadget.reactBall(ball);
        assertTrue(ball.getVelocity().minus(new Vect(-1, 1)).length() < 0.001);
    }
}
