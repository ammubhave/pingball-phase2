package tests;

import static org.junit.Assert.*;


import gameplay.Ball;
import gameplay.Bumper;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import utilities.Coords;

public class GadgetTests {

    // Initialize some coordinates on the board that we will put gadgets in
    // later
    private static Coords coord1;
    private static Coords coord2;
    private static Coords coord3;
    private static Coords coord4;
    private static Coords coord5;
    private static Coords coord6;
    private static Coords coord7;
    private static Coords coord8;
    private static Coords coord9;
    
    
    // Initialize some velocities on the board that we will put gadgets in
    // later
    private static Vect vel1;
    private static Vect vel2;
    private static Vect vel3;
    private static Vect vel4;

    

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        coord1 = new Coords(0, 0);
        coord2 = new Coords(1, 1);
        coord3 = new Coords(6, 5);
        coord4 = new Coords(19, 0);
        coord5 = new Coords(19, 19);
        coord6 = new Coords(6,2);
        coord7 = new Coords(10,5);
        coord8 = new Coords(6,10);
        coord9 = new Coords(2,5);
        
        vel1 = new Vect(1,0);
        vel2 = new Vect(0,1);
        vel3 = new Vect(-1,0);
        vel4 = new Vect(0,-1);
        
    }

    /*
     * Bumper
     */
    
    
     //CIRCLE BUMPERS
    @Test
    public void circularBumperBounceTest() {
        Ball b = new Ball(coord9, vel1);
        Bumper circBump = new Bumper(coord3, "CIRCLE");
        Ball result = circBump.bounce(b, "circle");
        assertTrue(result.getVelocity().x()==-1);
        assertTrue(result.getVelocity().y()==0);
    }
    
    //SQUARE BUMPERS
    @Test
    public void squareBumperBounceTopTest() {
        Ball b = new Ball(coord6, vel2);
        
        Bumper squareBump = new Bumper(coord3, "SQUARE");
        Ball result = squareBump.bounce(b, "top");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == -1);
    }
    
    //NEED TO FIX THIS
    @Test
    public void squareBumperBounceRightTest() {
        Ball b = new Ball(coord7, vel3);
        
        Bumper squareBump = new Bumper(coord3, "SQUARE");
        Ball result = squareBump.bounce(b, "right");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == -1);
    }
    
    @Test
    public void squareBumperBounceLeftTest() {
        Ball b = new Ball(coord9, vel1);
        
        Bumper squareBump = new Bumper(coord3, "SQUARE");
        Ball result = squareBump.bounce(b, "left");
        
        assertTrue(result.getVelocity().x() == -1);
        assertTrue(result.getVelocity().y() == 0);
    }
    
    @Test
    public void squareBumperBounceBottomTest() {
        Ball b = new Ball(coord8, vel4);
        
        Bumper squareBump = new Bumper(coord3, "SQUARE");
        Ball result = squareBump.bounce(b, "bottom");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == 1);
    }
    
    //TRIANGLE BUMPERS
    @Test
    public void triangle0BumperBounceLegHTest(){
        Ball b = new Ball(coord6, vel2);
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        
        Ball result = triBump.bounce(b, "hLeg");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == -1);
    }
    
    @Test
    public void triangle0BumperBounceLegVTest(){
        Ball b = new Ball(coord9, vel1);
        
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        Ball result = triBump.bounce(b, "vLeg");
        
        assertTrue(result.getVelocity().x() == -1);
        assertTrue(result.getVelocity().y() == 0); 
    }
    
    @Test
    public void triangle0BumperBounceHypotenuseTest(){
        Ball b = new Ball(coord7, vel3);
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        
        Ball result = triBump.bounce(b, "hypotenuse");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == 1);
    }
    @Test
    public void triangle90BumperBounceLegHTest(){
        Ball b = new Ball(coord6, vel2);
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        
        Ball result = triBump.bounce(b, "hLeg");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == -1);
    }
    @Test
    public void triangle90BumperBounceLegVTest(){
        Ball b = new Ball(coord7, vel3);
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        
        Ball result = triBump.bounce(b, "vLeg");
        
        assertTrue(result.getVelocity().x() == 1);
        assertTrue(result.getVelocity().y() == 0);
    }
    @Test
    public void triangle90BumperBounceHypotenuseTest(){
        Ball b = new Ball(coord9, vel1);
        
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        Ball result = triBump.bounce(b, "hypotenuse");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == -1); 
    }
    @Test
    public void triangle180BumperBounceLegHTest(){
        Ball b = new Ball(coord8, vel4);
        
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        Ball result = triBump.bounce(b, "hLeg");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == 1);
    }
    @Test
    public void triangle180BumperBounceLegVTest(){
        Ball b = new Ball(coord7, vel3);
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        
        Ball result = triBump.bounce(b, "vLeg");
        
        assertTrue(result.getVelocity().x() == 1);
        assertTrue(result.getVelocity().y() == 0);
    }
    @Test
    public void triangle180BumperBounceHypotenuseTest(){
        Ball b = new Ball(coord9, vel1);
        
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        Ball result = triBump.bounce(b, "hypotenuse");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == -1);
    }
    @Test
    public void triangle270BumperBounceLegHTest(){
        Ball b = new Ball(coord8, vel4);
        
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        Ball result = triBump.bounce(b, "hLeg");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == 1);
    }
    @Test
    public void triangle270BumperBounceLegVTest(){
        Ball b = new Ball(coord9, vel1);
        
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        Ball result = triBump.bounce(b, "vLeg");
        
        assertTrue(result.getVelocity().x() == -1);
        assertTrue(result.getVelocity().y() == 0); 
        
    }
    @Test
    public void triangle270BumperBounceHypotenuseTest(){
        Ball b = new Ball(coord7, vel3);
        Bumper triBump = new Bumper(coord3, "TRIANGLE");
        
        Ball result = triBump.bounce(b, "hypotenuse");
        
        assertTrue(result.getVelocity().x() == 0);
        assertTrue(result.getVelocity().y() == 1);
    }
    
    

}
