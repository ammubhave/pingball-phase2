package pingball.tests;

import physics.Vect;

public class GadgetTests {

    // Initialize some coordinates on the board that we will put gadgets in
    // later
    private static Vect coord1;
    private static Vect coord2;
    private static Vect coord3;
    private static Vect coord4;
    private static Vect coord5;
    private static Vect coord6;
    private static Vect coord7;
    private static Vect coord8;
    private static Vect coord9;

    // Initialize some velocities on the board that we will put gadgets in
    // later
    private static Vect vel1;
    private static Vect vel2;
    private static Vect vel3;
    private static Vect vel4;

    /*
     * @BeforeClass public static void setUpBeforeClass() throws Exception {
     * coord1 = new Vect(0, 0); coord2 = new Vect(1, 1); coord3 = new Vect(6,
     * 5); coord4 = new Vect(19, 0); coord5 = new Vect(19, 19); coord6 = new
     * Vect(6, 2); coord7 = new Vect(10, 5); coord8 = new Vect(6, 10); coord9 =
     * new Vect(2, 5);
     * 
     * vel1 = new Vect(1, 0); vel2 = new Vect(0, 1); vel3 = new Vect(-1, 0);
     * vel4 = new Vect(0, -1);
     * 
     * }
     * 
     * /* Bumper
     */

    /*
     * @Test public void circularBumperBounceTest() { Ball b = new Ball("b1",
     * coord9, vel1); CircularBumper circBump = new CircularBumper(coord3,
     * "CIRCLE"); circBump.reactBall(b); assertTrue(b.getVelocity().x() == -1);
     * assertTrue(b.getVelocity().y() == 0); }
     * 
     * @Test public void squareBumperBounceTopTest() { Ball b = new Ball("b2",
     * coord6, vel2);
     * 
     * SquareBumper squareBump = new SquareBumper(coord3, "SQUARE");
     * squareBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * -1); }
     */
    
    // NEED TO FIX THIS
    /*
     * @Test public void squareBumperBounceRightTest() { Ball b = new Ball("b3",
     * coord7, vel3);
     * 
     * SquareBumper squareBump = new SquareBumper(coord3, "SQUARE");
     * squareBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * -1); }
     * 
     * @Test public void squareBumperBounceLeftTest() { Ball b = new Ball("b1",
     * coord9, vel1);
     * 
     * SquareBumper squareBump = new SquareBumper(coord3, "SQUARE");
     * squareBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == -1); assertTrue(b.getVelocity().y() ==
     * 0); }
     * 
     * @Test public void squareBumperBounceBottomTest() { Ball b = new
     * Ball("b1", coord8, vel4);
     * 
     * SquareBumper squareBump = new SquareBumper(coord3, "SQUARE");
     * squareBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * 1); }
     */

    // TRIANGLE BUMPERS
    /*
     * @Test public void triangle0BumperBounceLegHTest() { Ball b = new
     * Ball("b1", coord6, vel2); TriangularBumper triBump = new
     * TriangularBumper(coord3, "TRIANGLE");
     * 
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * -1); }
     * 
     * @Test public void triangle0BumperBounceLegVTest() { Ball b = new
     * Ball("b1", coord9, vel1);
     * 
     * TriangularBumper triBump = new TriangularBumper(coord3, "TRIANGLE");
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == -1); assertTrue(b.getVelocity().y() ==
     * 0); }
     * 
     * @Test public void triangle0BumperBounceHypotenuseTest() { Ball b = new
     * Ball("b1", coord7, vel3); TriangularBumper triBump = new
     * TriangularBumper(coord3, "TRIANGLE");
     * 
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * 1); }
     * 
     * @Test public void triangle90BumperBounceLegHTest() { Ball b = new
     * Ball("b1", coord6, vel2); TriangularBumper triBump = new
     * TriangularBumper(coord3, "TRIANGLE");
     * 
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * -1); }
     * 
     * @Test public void triangle90BumperBounceLegVTest() { Ball b = new
     * Ball("b1", coord7, vel3); TriangularBumper triBump = new
     * TriangularBumper(coord3, "TRIANGLE");
     * 
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 1); assertTrue(b.getVelocity().y() ==
     * 0); }
     * 
     * @Test public void triangle90BumperBounceHypotenuseTest() { Ball b = new
     * Ball("b1", coord9, vel1);
     * 
     * TriangularBumper triBump = new TriangularBumper(coord3, "TRIANGLE");
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * -1); }
     * 
     * @Test public void triangle180BumperBounceLegHTest() { Ball b = new
     * Ball("b1", coord8, vel4);
     * 
     * TriangularBumper triBump = new TriangularBumper(coord3, "TRIANGLE");
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * 1); }
     * 
     * @Test public void triangle180BumperBounceLegVTest() { Ball b = new
     * Ball("b1", coord7, vel3); TriangularBumper triBump = new
     * TriangularBumper(coord3, "TRIANGLE");
     * 
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 1); assertTrue(b.getVelocity().y() ==
     * 0); }
     * 
     * @Test public void triangle180BumperBounceHypotenuseTest() { Ball b = new
     * Ball("b1", coord9, vel1);
     * 
     * TriangularBumper triBump = new TriangularBumper(coord3, "TRIANGLE");
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * -1); }
     * 
     * @Test public void triangle270BumperBounceLegHTest() { Ball b = new
     * Ball("b1", coord8, vel4);
     * 
     * TriangularBumper triBump = new TriangularBumper(coord3, "TRIANGLE");
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * 1); }
     * 
     * @Test public void triangle270BumperBounceLegVTest() { Ball b = new
     * Ball("b1", coord9, vel1);
     * 
     * TriangularBumper triBump = new TriangularBumper(coord3, "TRIANGLE");
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == -1); assertTrue(b.getVelocity().y() ==
     * 0);
     * 
     * }
     * 
     * @Test public void triangle270BumperBounceHypotenuseTest() { Ball b = new
     * Ball("b1", coord7, vel3); TriangularBumper triBump = new
     * TriangularBumper(coord3, "TRIANGLE");
     * 
     * triBump.reactBall(b);
     * 
     * assertTrue(b.getVelocity().x() == 0); assertTrue(b.getVelocity().y() ==
     * 1); }
     */

}
