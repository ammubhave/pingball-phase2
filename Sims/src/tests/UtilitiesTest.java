package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import utilities.*;
import physics.Vect;

/**
 * Test the Coords class here.
 * 
 *
 */

public class UtilitiesTest {

    public static Coords c1;
    public static Coords c2;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        c1 = new Coords(0.25, 0.75);
        c2 = new Coords(0, 0.5);
    }
    
    @Test
    public void getters() {
        assertEquals(c1.getX(), 0.25, 0.0001);
        assertEquals(c1.getY(), 0.75, 0.0001);
        assertEquals(c2.getX(), 0.00d, 0.00001);
        assertEquals(c2.getY(), 0.5d, 0.00001);
    }
    
    @Test
    public void testEquals() {
        Coords c3 = new Coords(0.2500000, 0.75000);
        Coords c4 = new Coords(0.2500001, 0.75);
        
        assertTrue(c1.equals(c3));
        assertFalse(c1.equals(c4));
    }
    
    @Test
    public void getDistance() {
        double d1 = c1.getDistance(c2);
        double d2 = c2.getDistance(c1);
        double d3 = c1.getDistance(c1);
        assertEquals(d1, 0.353553, 0.0001);
        assertEquals(d2, d1, 0.0001);
        assertEquals(d3, 0, 0.000000000000000000000000000000000001);
    }
    
    @Test
    public void round() {
        Coords c4 = new Coords(0, 1);
        assertEquals(c4, c1.round());
        assertEquals(c4, c2.round());
    }
    
    @Test
    public void ceil() {
        Coords c4 = new Coords(1, 1);
        Coords c5 = new Coords(0, 1);
        assertEquals(c4, c1.ceil());
        assertEquals(c5, c2.ceil());
    }
    
    @Test
    public void floor() {
        Coords c4 = new Coords(0, 0);
        Coords c5 = new Coords(0, 0);
        assertEquals(c4, c1.floor());
        assertEquals(c5, c2.floor());
    }
}
