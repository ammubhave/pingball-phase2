package pingball.tests;


/**
 * testing strategy: check for each of the 4 corners, both horizontal and
 * vertical also check for perpendicular reflection - test equals
 */
/*public class RightFlipperGadgetTest {
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
 public void testRenderHorizontalNWCorner() {
 RightFlipper gadget = new RightFlipper("test", new Vect(0, 0), FlipperOrientation.TOP_LEFT);
 String renderedString = gadget.render(emptyBoardString);

 for (int y = 1; y < 21; y++) {
 for (int x = 1; x < 21; x++) {
 if (x >= 1 && x <= 2 && y == 1)
 assertTrue(renderedString.charAt(y * 23 + x) == '-');
 else
 assertTrue(renderedString.charAt(y * 23 + x) == ' ');
 }
 }
 }

 @Test
 public void testRenderHorizontalNECorner() {
 RightFlipper gadget = new RightFlipper("test", new Vect(18, 0), FlipperOrientation.NW);
 String renderedString = gadget.render(emptyBoardString);

 for (int y = 1; y < 21; y++) {
 for (int x = 1; x < 21; x++) {
 if (x >= 19 && x <= 20 && y == 1)
 assertTrue(renderedString.charAt(y * 23 + x) == '-');
 else
 assertTrue(renderedString.charAt(y * 23 + x) == ' ');
 }
 }
 }

 @Test
 public void testRenderHorizontalSWCorner() {
 RightFlipper gadget = new RightFlipper("test", new Vect(0, 19), FlipperOrientation.NW);
 String renderedString = gadget.render(emptyBoardString);

 for (int y = 1; y < 21; y++) {
 for (int x = 1; x < 21; x++) {
 if (x >= 1 && x <= 2 && y == 20)
 assertTrue(renderedString.charAt(y * 23 + x) == '-');
 else
 assertTrue(renderedString.charAt(y * 23 + x) == ' ');
 }
 }
 }

 @Test
 public void testRenderHorizontalSECorner() {
 RightFlipper gadget = new RightFlipper("test", new Vect(18, 19), FlipperOrientation.NW);
 String renderedString = gadget.render(emptyBoardString);

 for (int y = 1; y < 21; y++) {
 for (int x = 1; x < 21; x++) {
 if (x >= 19 && x <= 20 && y == 20)
 assertTrue(renderedString.charAt(y * 23 + x) == '-');
 else
 assertTrue(renderedString.charAt(y * 23 + x) == ' ');
 }
 }
 }

 @Test
 public void testRenderVerticalNWCorner() {
 RightFlipper gadget = new RightFlipper("test", new Vect(0, 0), FlipperOrientation.NW);
 gadget.action();
 String renderedString = gadget.render(emptyBoardString);

 for (int y = 1; y < 21; y++) {
 for (int x = 1; x < 21; x++) {
 if (y >= 1 && y <= 2 && x == 1)
 assertTrue(renderedString.charAt(y * 23 + x) == '-' || renderedString.charAt(y * 23 + x) == '|'
 || renderedString.charAt(y * 23 + x) == ' ');
 else
 assertTrue(renderedString.charAt(y * 23 + x) == ' ');
 }
 }
 }

 @Test
 public void testRenderVerticalNECorner() {
 RightFlipper gadget = new RightFlipper("test", new Vect(18, 0), FlipperOrientation.NW);
 String renderedString = gadget.render(emptyBoardString);
 gadget.action();
 for (int y = 1; y < 21; y++) {
 for (int x = 1; x < 21; x++) {
 if (x >= 19 && x <= 20 && y >= 1 && y <= 2)
 assertTrue(renderedString.charAt(y * 23 + x) == '-' || renderedString.charAt(y * 23 + x) == '|'
 || renderedString.charAt(y * 23 + x) == ' ');
 else
 assertTrue(renderedString.charAt(y * 23 + x) == ' ');
 }
 }
 }

 @Test
 public void testRenderVerticalSWCorner() {
 RightFlipper gadget = new RightFlipper("test", new Vect(0, 18), FlipperOrientation.NW);
 gadget.action();
 String renderedString = gadget.render(emptyBoardString);

 for (int y = 1; y < 21; y++) {
 for (int x = 1; x < 21; x++) {
 if (x == 1 && y >= 19 && y <= 20)
 assertTrue(renderedString.charAt(y * 23 + x) == '-' || renderedString.charAt(y * 23 + x) == '|'
 || renderedString.charAt(y * 23 + x) == ' ');
 else
 assertTrue(renderedString.charAt(y * 23 + x) == ' ');
 }
 }
 }

 @Test
 public void testRenderVerticalSECorner() {
 RightFlipper gadget = new RightFlipper("test", new Vect(18, 18), FlipperOrientation.NW);
 String renderedString = gadget.render(emptyBoardString);
 gadget.action();
 for (int y = 1; y < 21; y++) {
 for (int x = 1; x < 21; x++) {
 if (x >= 19 && x <= 20 && y >= 19 && y <= 20)
 assertTrue(renderedString.charAt(y * 23 + x) == '-' || renderedString.charAt(y * 23 + x) == '|'
 || renderedString.charAt(y * 23 + x) == ' ');
 else
 assertTrue(renderedString.charAt(y * 23 + x) == ' ');
 }
 }
 }

 @Test
 public void testPerpendicularReflection() {
 RightFlipper gadget = new RightFlipper("test", new Vect(0, 0), FlipperOrientation.NW);
 Ball ball = new Ball("ball", new Vect(1, 1), new Vect(-1, -1));
 assertEquals(gadget.leastCollisionTime(ball), 3 / 4., 0.0001);
 gadget.reactBall(ball);
 assert ball.getVelocity() == new Vect(1, 1);

 ball = new Ball("ball", new Vect(0.5, -1), new Vect(0, 1));
 assert gadget.leastCollisionTime(ball) == 1;
 gadget.reactBall(ball);
 assert ball.getVelocity().equals(new Vect(6, -1));
 }

 @Test
 public void testEquals() {
 RightFlipper g1 = new RightFlipper(new Vect(0, 0), FlipperOrientation.NW, "Bilbo");
 RightFlipper g2 = new RightFlipper(new Vect(0, 0), FlipperOrientation.NW, "Bilbo");
 RightFlipper g3 = new RightFlipper(new Vect(5, 0), FlipperOrientation.NW, "Baggins");

 assertEquals(g1, g2);
 assertNotEquals(g1, g3);
 }
 }*/
