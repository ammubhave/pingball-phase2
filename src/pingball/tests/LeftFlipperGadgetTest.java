package pingball.tests;

import static org.junit.Assert.*;
import static pingball.tests.TestHelpers.assertEqualsVect;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Flipper.FlipperOrientation;
import pingball.board.Ball;
import pingball.board.LeftFlipper;

/**
 * Testing strategy:
 * check for each of the 4 corners, both horizontal and vertical
 * also check for perpendicular reflection
 * 
 */
public class LeftFlipperGadgetTest {
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
    
    //RENDER TESTS
    
    @Test
    public void testRenderNWCorner() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
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
    public void testRenderNECorner() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
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
    public void testRenderSECorner() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
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
    public void testRenderSWCorner() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
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
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
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
    public void testRenderMutatedNECorner() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
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
    
    @Test
    public void testRenderMutatedSECorner() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
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
    public void testRenderMutatedSWCorner() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
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
    
    // REACT BALL TESTS
    
    @Test
    public void testPerpendicularTopReflectionBottomSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        Ball ball = new Ball("ball", new Vect(1, 2), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularTopReflectionLeftSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        Ball ball = new Ball("ball", new Vect(-1, 0.25), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularTopReflectionTopSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        Ball ball = new Ball("ball", new Vect(1, -1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularTopReflectionRightSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.TOP, "test");
        Ball ball = new Ball("ball", new Vect(3, 0.25), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0.95, 0), ball.getVelocity());
    }
    
    @Test
    public void testPerpendicularLeftReflectionBottomSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        Ball ball = new Ball("ball", new Vect(0.25, 3), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularLeftReflectionLeftSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        Ball ball = new Ball("ball", new Vect(-1, 1), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularLeftReflectionTopSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        Ball ball = new Ball("ball", new Vect(0.25, -1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularLeftReflectionRightSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.LEFT, "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0.95, 0), ball.getVelocity());
    }
    
    @Test
    public void testPerpendicularRightReflectionBottomSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(1.75, 3), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularRightReflectionLeftSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularRightReflectionTopSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(1.75, -1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularRightReflectionRightSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.RIGHT, "test");
        Ball ball = new Ball("ball", new Vect(3, 1), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularBottomReflectionBottomSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        Ball ball = new Ball("ball", new Vect(1, 3), new Vect(0, -1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, 0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularBottomReflectionLeftSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        Ball ball = new Ball("ball", new Vect(-1, 1.75), new Vect(1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(-0.95, 0), ball.getVelocity());
    }

    @Test
    public void testPerpendicularBottomReflectionTopSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        Ball ball = new Ball("ball", new Vect(1, 1), new Vect(0, 1));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0, -0.95), ball.getVelocity());
    }

    @Test
    public void testPerpendicularBottomReflectionRightSide() {
        LeftFlipper gadget = new LeftFlipper(new Vect(0, 0), FlipperOrientation.BOTTOM, "test");
        Ball ball = new Ball("ball", new Vect(3, 1.75), new Vect(-1, 0));
        gadget.reactBall(ball);
        assertEqualsVect(new Vect(0.95, 0), ball.getVelocity());
    }
}
