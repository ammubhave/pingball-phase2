package pingball.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.LeftFlipperGadget.VertexOrientation;
import pingball.board.OuterWallsGadget.OuterWallsOrientation;

/**
 * - test the four sides for neighbour name rendering
 * - test perpendicular reflection, angled reflection
 *- test equals
 */
public class OuterWallsGadgetTest {
    String emptyBoardString;

    @Before
    public void setUp() throws Exception {
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < 23; y++) {
            for(int x = 0; x < 22;x++)
                sb.append(' ');
            sb.append('\n');
        }
        emptyBoardString = sb.toString();
    }
    
    @Test
    public void testRenderHorizontalTopWall() {        
        OuterWallsGadget gadget = new OuterWallsGadget(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL);
        gadget.setNeighborName("Jimmy Neutron");
        String renderedString = gadget.render(emptyBoardString);
        assertEquals("....Jimmy Neutron.....\n", renderedString.substring(0,  23));
        //System.out.println(renderedString);
        for (int y = 1; y < 23; y++) {
            for (int x = 0; x < 23; x++) {
                if (x == 22)
                    assertTrue(renderedString.charAt(y*23+x) == '\n');
                else
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
            }
        }
    }
    
    @Test
    public void testPerpendicularReflection() {
        OuterWallsGadget gadget = new OuterWallsGadget(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL);
        Ball ball = new Ball("ball", new Vect(5, 5), new Vect(0, -1));
        assertEquals(4.75, gadget.timeUntilCollision(ball), 0.001);
        gadget.reflect(ball);
        assertEquals(new Vect(0, 1), ball.getVelocity());
    }
    
    @Test
    public void testAngledReflection() {
        OuterWallsGadget gadget = new OuterWallsGadget(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL);
        Ball ball = new Ball("ball", new Vect(10, 5), new Vect(-1, -1));
        assertEquals(4.75, gadget.timeUntilCollision(ball), 0.001);
        gadget.reflect(ball);
        assertTrue(ball.getVelocity().minus(new Vect(-1, 1)).length() < 0.001);
    }
}
