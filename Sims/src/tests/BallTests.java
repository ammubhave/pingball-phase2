package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.*;
import utilities.*;
import gameplay.*;

public class BallTests {

    private static Ball b1, b2;
    private static Coords c1, c2;
    private static Vect v1, v2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        c1 = new Coords(1, 1);
        c2 = new Coords(5, 5);
        
        v1 = new Vect(0, 1);
        v2 = new Vect(2, 3);
        
        b1 = new Ball(c1, v1);
        b2 = new Ball(c2, v2);
    }

    @Test
    public void testCircleObject() {
        Circle c = b1.getCircleObject();
        assertEquals(c.getCenter().x(), b1.getCenter().getX(), 0.001);
        assertEquals(c.getCenter().y(), b1.getCenter().getY(), 0.001);
        assertEquals(c.getRadius(), b1.getRadius(), 0.01);
    }
    
    @Test
    public void getX() {
        assertEquals(c1.getX(), b1.getX(), 0.01);
    }
    
    @Test
    public void getY() {
        assertEquals(c1.getY(), b1.getY(), 0.01);
    }
    
    @Test
    public void getCenter() {
        assertEquals(c2, b2.getCenter());
    }
    
    @Test
    public void stop() {
        b2.stop();
        Vect zero = new Vect(0, 0);
        assertEquals(b2.getVelocity(), zero);
        
        b1.stop();
        assertEquals(b1.getVelocity(), zero);
    }
    
    @Test
    public void move() {
        double newX = c1.getX() + v1.x() / 20.0;
        assertEquals(newX, b1.getCenter().getX(), 0.01);
    }
    
    @Test
    public void setVelocity() {
        b2.stop();
        b1.setVelocity(new Vect(0, 0));
        
        assertEquals(b1.getVelocity(), b2.getVelocity());
    }
    
    @Test
    public void setCenter() {
        Coords c1 = new Coords(0, 0);
        b1.setCenter(c1);
        
        assertEquals(c1, b1.getCenter());
    }
    
    @Test
    public void inContact() {
        Ball b3 = new Ball(new Coords(0, 0), new Vect(0, 0));
        Ball b4 = new Ball(new Coords(0.25, 0.25), new Vect(0, 0));
        
        assertTrue(b3.inContact(b4));
        
        Ball b5 = new Ball(new Coords(0, 0.5), new Vect(0, 0));
        assertTrue(b3.inContact(b5));
    }
    
    @Test
    public void bounce() {
        Ball b3 = new Ball(new Coords(0, 0), new Vect(1, 0));
        Ball b4 = new Ball(new Coords(0.5, 0.5), new Vect(-1, 0));
        
        b3.bounce(b4);
        
        assertEquals(b3.getCenter(), new Coords(0, -0.5));
        assertEquals(b4.getCenter(), new Coords(0.5, 1.0));
        
        assertEquals(b3.getVelocity(), new Vect(0, -1));
        assertEquals(b4.getVelocity(), new Vect(0, 1));
    }
    
    @Test
    public void bounceAngle() {
        Ball b3 = new Ball(new Coords(0, 0), new Vect(1, 1));
        Ball b4 = new Ball(new Coords(0.5, 0.5), new Vect(-1, -1));
        
        b3.bounce(b4);
        
        assertEquals(b3.getCenter(), new Coords(-0.5, -0.5));
        assertEquals(b4.getCenter(), new Coords(1.0, 1.0));
        
        assertEquals(b3.getVelocity(), new Vect(-1, -1));
        assertEquals(b4.getVelocity(), new Vect(1, 1));
    }

    @Test
    public void bouncePerpendicular() {
        Ball b3 = new Ball(new Coords(0, 0), new Vect(0, 1));
        Ball b4 = new Ball(new Coords(0.5, 0.5), new Vect(1, 0));
        
        b3.bounce(b4);
        
        assertEquals(b3.getCenter(), new Coords(0, 0.5));
        assertEquals(b4.getCenter(), new Coords(1.0, 0.5));
        
        assertEquals(b3.getVelocity(), new Vect(0, 1));
        assertEquals(b4.getVelocity(), new Vect(1, 0));
    }
    
    @Test
    public void collisionTime() {
        Ball b3 = new Ball(new Coords(0, 0), new Vect(0, 1));
        Ball b4 = new Ball(new Coords(0.5, 0.5), new Vect(1, 0));
        
        HashMap<String, Double> times = b3.leastCollisionTime(b4, b4.getVelocity());
        assertTrue(times.containsKey("BALL"));
        assertEquals(times.get("BALL"), Double.POSITIVE_INFINITY, 0.01);
    }
    
    @Test
    public void collisionTime2() {
        Ball b3 = new Ball(new Coords(0, 0), new Vect(1, 0));
        Ball b4 = new Ball(new Coords(0.5, 0.5), new Vect(-1, 0));
        
        HashMap<String, Double> times = b3.leastCollisionTime(b4, b4.getVelocity());
        assertTrue(times.containsKey("BALL"));
        assertTrue(times.get("BALL") < 1.0d);
    }
    
}
