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
 * - test the four sides for neighbor name rendering - test perpendicular
 * reflection, angled reflection - test equals
 */
public class OuterWallsGadgetTest {
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
    public void testRenderNoNameTopWall() {
        OuterWall gadget = new OuterWall(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL, "wall");
        gadget.setNeighborName("");
        String renderedString = gadget.render(emptyBoardString);
        assertEquals("......................\n", renderedString.substring(0, 23));
        //System.out.println(renderedString);
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
        OuterWall gadget = new OuterWall(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL, "wall");
        gadget.setNeighborName("Jimmy Neutron");
        String renderedString = gadget.render(emptyBoardString);
        assertEquals("....Jimmy Neutron.....\n", renderedString.substring(0, 23));
        //System.out.println(renderedString);
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
        OuterWall gadget = new OuterWall(new Vect(0, 0), OuterWallsOrientation.VERTICAL, "wall");
        gadget.setNeighborName("");
        String renderedString = gadget.render(emptyBoardString);
        //System.out.println(renderedString);
        for (int y = 0; y < 23; y++) {
            assertEquals("Y: " + String.valueOf(y), '.', renderedString.charAt(y * 23 + 0));
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
        OuterWall gadget = new OuterWall(new Vect(0, 0), OuterWallsOrientation.VERTICAL, "wall");
        gadget.setNeighborName("Jimmy Neutron");
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 0; y < 4; y++) {
            assertEquals("Y: " + String.valueOf(y), '.', renderedString.charAt(y * 23 + 0));
        }
        for (int y = 4; y < 17; y++) {
            assertEquals("Y: " + String.valueOf(y), "Jimmy Neutron".charAt(y - 4), renderedString.charAt(y * 23 + 0));
        }
        for (int y = 17; y < 23; y++) {
            assertEquals("Y: " + String.valueOf(y), '.', renderedString.charAt(y * 23 + 0));
        }
        // System.out.println(renderedString);
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
        OuterWall gadget = new OuterWall(new Vect(0, 22), OuterWallsOrientation.HORIZONTAL, "wall");
        gadget.setNeighborName("");
        String renderedString = gadget.render(emptyBoardString);
        //System.out.println(renderedString);
        assertEquals("......................\n", renderedString.substring(23*22, 23*23));
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
        OuterWall gadget = new OuterWall(new Vect(0, 22), OuterWallsOrientation.HORIZONTAL, "wall");
        gadget.setNeighborName("Jimmy Neutron");
        String renderedString = gadget.render(emptyBoardString);
        assertEquals("....Jimmy Neutron.....\n", renderedString.substring(23*22, 23*23));
        //System.out.println(renderedString);
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
        OuterWall gadget = new OuterWall(new Vect(21, 0), OuterWallsOrientation.VERTICAL, "wall");
        gadget.setNeighborName("");
        String renderedString = gadget.render(emptyBoardString);
        System.out.println(renderedString);

        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 21; x++) {
                assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }
            assertEquals("Y: " + String.valueOf(y), '.', renderedString.charAt(y * 23 + 21));
            assertTrue(renderedString.charAt(y * 23 + 22) == '\n');
        }
    }
    
    @Test
    public void testRenderWithNameRightWall() {
        OuterWall gadget = new OuterWall(new Vect(21, 0), OuterWallsOrientation.VERTICAL, "wall");
        gadget.setNeighborName("Jimmy Neutron");
        String renderedString = gadget.render(emptyBoardString);
        //System.out.println(renderedString);
        for (int y = 0; y < 4; y++) {
            assertEquals("Y: " + String.valueOf(y), '.', renderedString.charAt(y * 23 + 21));
        }
        for (int y = 4; y < 17; y++) {
            assertEquals("Y: " + String.valueOf(y), "Jimmy Neutron".charAt(y - 4), renderedString.charAt(y * 23 + 21));
        }
        for (int y = 17; y < 23; y++) {
            assertEquals("Y: " + String.valueOf(y), '.', renderedString.charAt(y * 23 + 21));
        }
        for (int y = 0; y < 23; y++) {
            for (int x = 0; x < 21; x++) {
                assertTrue(renderedString.charAt(y * 23 + x) == ' ');
            }

            assertTrue(renderedString.charAt(y * 23 + 22) == '\n');
        }
    }
    
    @Test
    public void testPerpendicularReflection() {
        OuterWall gadget = new OuterWall(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL, "wall");
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, -1));
        assertEquals(4.75, gadget.leastCollisionTime(ball), 0.001);
        gadget.reactBall(ball);
        assertEquals(new Vect(0, 1), ball.getVelocity());
    }

    @Test
    public void testAngledReflection() {
        OuterWall gadget = new OuterWall(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL, "wall");
        Ball ball = new Ball("ball", new Vect(10, 5), new Vect(-1, -1));
        assertEquals(4.75, gadget.leastCollisionTime(ball), 0.001);
        gadget.reactBall(ball);
        assertTrue(ball.getVelocity().minus(new Vect(-1, 1)).length() < 0.001);
    }
}
