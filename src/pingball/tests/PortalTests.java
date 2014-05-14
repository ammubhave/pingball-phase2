package pingball.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import physics.Vect;
import pingball.board.Ball;
import pingball.board.Portal;

public class PortalTests {
    Portal portal1;
    Portal portal2;
    Portal portal3;
    
    @Before
    public void setUp() throws Exception {
        portal1 = new Portal(new Vect(4.5, 4.5), "test", "target", "board");
        portal2 = new Portal(new Vect(7.5, 7.5), "target", "target", "board");
        portal3 = new Portal(new Vect(4.5, 4.5), "test2", "target", "board");
    }
    
    
   //LEAST COLLISION TIME TEST 
    @Test
    public void testLeastCollisionTimeZero(){
        Ball ball = new Ball("ball", new Vect(5, 4.9), new Vect(0, 1));
        assertEquals(0, portal1.leastCollisionTime(ball), 0.0001);
    }
    
    @Test
    public void testLeastCollisionTimeTop() {
        Ball ball = new Ball("ball", new Vect(5, 4), new Vect(0, 1));
        assertEquals(0.25, portal1.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeLeft() {
        Ball ball = new Ball("ball", new Vect(4, 5), new Vect(1, 0));
        assertEquals(0.25, portal1.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeRight() {
        Ball ball = new Ball("ball", new Vect(6, 5), new Vect(-1, 0));
        assertEquals(0.25, portal1.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeBottom() {
        Ball ball = new Ball("ball", new Vect(5, 6), new Vect(0, -1));
        assertEquals(0.25, portal1.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeTopRight() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(-1, 1));
        assertEquals(0.4696, portal1.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeTopLeft() {
        Ball ball = new Ball("ball", new Vect(4, 4), new Vect(1, 1));
        assertEquals(0.4696, portal1.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeBottomRight() {
        Ball ball = new Ball("ball", new Vect(6, 6), new Vect(-1, -1));
        assertEquals(0.4696, portal1.leastCollisionTime(ball), 0.0001);
    }

    @Test
    public void testLeastCollisionTimeBottomLeft() {
        Ball ball = new Ball("ball", new Vect(4, 6), new Vect(1, -1));
        assertEquals(0.4696, portal1.leastCollisionTime(ball), 0.0001);
    }
    
    //REACT BALL TESTS
    
    @Test
    public void testPerpendicularReflectionTop() {
        Ball ball = new Ball("ball", new Vect(5, 4), new Vect(0, 1));
        portal3.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(0, 1));
    }
    
    @Test
    public void testPerpendicularReflectionLeft() {
        Ball ball = new Ball("ball", new Vect(4, 5), new Vect(1, 0));
        portal3.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(1, 0));
    }
    
    @Test
    public void testPerpendicularReflectionRight() {
        Ball ball = new Ball("ball", new Vect(6, 5), new Vect(-1, 0));
        portal3.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(-1, 0));
    }
    
    @Test
    public void testPerpendicularReflectionBottom() {
        Ball ball = new Ball("ball", new Vect(5, 6), new Vect(0, -1));
        portal3.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(0, -1));
    }
    
    @Test
    public void testPerpendicularReflectionTopRight() {
        Ball ball = new Ball("ball", new Vect(6, 4), new Vect(-1, 1));
        portal3.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(-1, 1));
    }
    
    @Test
    public void testPerpendicularReflectionTopLeft() {
        Ball ball = new Ball("ball", new Vect(4, 4), new Vect(1, 1));
        portal3.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(1, 1));
    }
    
    @Test
    public void testPerpendicularReflectionBottomRight() {
        Ball ball = new Ball("ball", new Vect(6, 6), new Vect(-1, -1));
        portal3.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(-1, -1));
    }
    
    @Test
    public void testPerpendicularReflectionBottomLeft() {
        Ball ball = new Ball("ball", new Vect(4, 6), new Vect(1, -1));
        portal3.reactBall(ball);
        assertEquals(ball.getVelocity(),new Vect(1, -1));
    }
    
    
    
}
