package pingball.tests;

import static org.junit.Assert.*;
import static pingball.tests.TestHelpers.assertEqualsVect;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Flipper.FlipperOrientation;
import pingball.board.Ball;
import pingball.board.LeftFlipper;
import pingball.board.RightFlipper;
/**
 * testing strategy:
 * check for each of the 4 corners, both horizontal and vertical
 * also check for perpendicular reflection
 * - test equals
 */
public class RightFlipperGadgetTest {
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
    public void testRenderSWCorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderNWCorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y == 1 && x >= 1 && x <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderNECorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 2 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderSECorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y == 2 && x >= 1 && x <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderMutatedSWCorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        gadget.moveFlipper();
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y == 2 && x >= 1 && x <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderMutatedNWCorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        gadget.moveFlipper();
        String renderedString = gadget.render(emptyBoardString);
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 1 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderMutatedNECorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        gadget.moveFlipper();
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (y == 1 && x >= 1 && x <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '-');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    @Test
    public void testRenderMutatedSECorner() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        gadget.moveFlipper();
        String renderedString = gadget.render(emptyBoardString);
        
        for (int y = 1; y < 21; y++) {
            for (int x = 1; x < 21; x++) {
                if (x == 2 && y >= 1 && y <= 2 )
                    assertTrue(renderedString.charAt(y*23+x) == '|');
                else{
                    assertTrue(renderedString.charAt(y*23+x) == ' ');
                }
            }
        }
    }
    
    // REFLECTION
    
    @Test
    public void testPerpendicularTopReflectionBottomSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        Ball ball = new Ball("ball", new Vect(1, 2), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularTopReflectionLeftSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        Ball ball = new Ball("ball", new Vect(-1, 0.25), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularTopReflectionTopSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        Ball ball = new Ball("ball", new Vect(1, -1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularTopReflectionRightSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        Ball ball = new Ball("ball", new Vect(3, 0.25), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0.95, 0), ball.getVelocity());
    }
    
    @Test
    public void testPerpendicularLeftReflectionBottomSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        Ball ball = new Ball("ball", new Vect(0.25, 3), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularLeftReflectionLeftSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        Ball ball = new Ball("ball", new Vect(-1, 1), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularLeftReflectionTopSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        Ball ball = new Ball("ball", new Vect(0.25, -1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularLeftReflectionRightSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0.95, 0), ball.getVelocity());
    }
    
    @Test
    public void testPerpendicularRightReflectionBottomSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(1.75, 3), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularRightReflectionLeftSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularRightReflectionTopSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(1.75, -1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularRightReflectionRightSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(3, 1), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularBottomReflectionBottomSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        Ball ball = new Ball("ball", new Vect(1, 3), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularBottomReflectionLeftSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        Ball ball = new Ball("ball", new Vect(-1, 1.75), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularBottomReflectionTopSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularBottomReflectionRightSide() {
        RightFlipper gadget = new RightFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        Ball ball = new Ball("ball", new Vect(3, 1.75), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0.95, 0), ball.getVelocity());
    }
}
