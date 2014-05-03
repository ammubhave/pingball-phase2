package pingball.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.LineSegment;
import physics.Vect;

/**
 * Testing Strategy: The constructor, trigger, and action methods of the gadgets
 * were tested by constructing gadgets in different orientations (triangular
 * bumpers) and checking if the ball correctly bounced off Input Partioning:
 * Postion of Gadget: Center, Left Edge, Right Edge, Top Edge, Bottom Edge,
 * Corners. Orientation of Triangular Bumper: the 4 possible orientations
 */

public class BumperTest {

    private static SquareBumper s1;
    private static TriangularBumper t1;
    private static TriangularBumper t2;
    private static TriangularBumper t3;
    private static TriangularBumper t4;
    private static CircularBumper c1;

    private static Ball b1;
    private static Ball b2;
    private static Ball b3;
    private static Ball b4;
    private static Ball b5;
    private static Ball b6;

    @BeforeClass
    public static void setUpBeforeClass() {
        s1 = new SquareBumper(4.0, 5.0, "s1");
        t1 = new TriangularBumper(4.0, 5.0, 0, "t1");
        t2 = new TriangularBumper(4.0, 5.0, 90, "t2");
        t3 = new TriangularBumper(4.0, 5.0, 180, "t3");
        t4 = new TriangularBumper(4.0, 5.0, 270, "t4");
        c1 = new CircularBumper(4.0, 5.0, "c1");

        b1 = new Ball(4.0, 4.5, 0, 0); // on border of everything except for 90
                                       // and 180 triangles
        b2 = new Ball(5.0, 4.5, 0, 0); // on border of everything except for 0
                                       // and 270 triangles
        b3 = new Ball(4.5, 4.5, 2, 5); // on border/inside everything
        b4 = new Ball(4.25, 4.5, 0, 0); // inside everything except for 90 and
                                        // 180 triangles
        b5 = new Ball(4.97, 4.95, 0, 0); // inside everything except for 0 and
                                         // 270 triangles and circles
        b6 = new Ball(3, 2, 0, 0); // outside everything
    }

    @Test
    public void testSquareBumperInit() {
        List<LineSegment> s1LineSegs = s1.getLineSegments();
        assertEquals(s1LineSegs.get(0), new LineSegment(4.0, 5.0, 5.0, 5.0));
        assertEquals(s1LineSegs.get(1), new LineSegment(4.0, 6.0, 5.0, 6.0));
        assertEquals(s1LineSegs.get(2), new LineSegment(4.0, 5.0, 4.0, 6.0));
        assertEquals(s1LineSegs.get(3), new LineSegment(5.0, 5.0, 5.0, 6.0));
    }

    @Test
    public void testTriangularBumperInit() {
        // orientation = 0
        List<LineSegment> t1LineSegs = t1.getLineSegments();
        assertEquals(t1LineSegs.get(0), new LineSegment(4.0, 5.0, 5.0, 5.0));
        assertEquals(t1LineSegs.get(1), new LineSegment(4.0, 5.0, 4.0, 6.0));
        assertTrue(t1LineSegs.get(2).equals(new LineSegment(5.0, 5.0, 4.0, 6.0))
                || t1LineSegs.get(2).equals(new LineSegment(4.0, 6.0, 5.0, 5.0)));

        // orientation = 90
        List<LineSegment> t2LineSegs = t2.getLineSegments();
        assertEquals(t2LineSegs.get(0), new LineSegment(4.0, 5.0, 5.0, 5.0));
        assertEquals(t2LineSegs.get(1), new LineSegment(5.0, 5.0, 5.0, 6.0));
        assertTrue(t2LineSegs.get(2).equals(new LineSegment(4.0, 5.0, 5.0, 6.0))
                || t2LineSegs.get(2).equals(new LineSegment(5.0, 6.0, 4.0, 5.0)));

        // orientation = 180
        List<LineSegment> t3LineSegs = t3.getLineSegments();
        assertEquals(t3LineSegs.get(0), new LineSegment(4.0, 6.0, 5.0, 6.0));
        assertEquals(t3LineSegs.get(1), new LineSegment(5.0, 5.0, 5.0, 6.0));
        assertTrue(t3LineSegs.get(2).equals(new LineSegment(5.0, 5.0, 4.0, 6.0))
                || t3LineSegs.get(2).equals(new LineSegment(4.0, 6.0, 5.0, 5.0)));

        // orientation = 270
        List<LineSegment> t4LineSegs = t4.getLineSegments();
        assertEquals(t4LineSegs.get(0), new LineSegment(4.0, 6.0, 5.0, 6.0));
        assertEquals(t4LineSegs.get(1), new LineSegment(4.0, 5.0, 4.0, 6.0));
        assertTrue(t4LineSegs.get(2).equals(new LineSegment(4.0, 5.0, 5.0, 6.0))
                || t4LineSegs.get(2).equals(new LineSegment(5.0, 6.0, 4.0, 5.0)));
    }

    @Test
    public void testCircularBumperInit() {
        assertTrue(c1.getPos().equals(new Vect(4.5, 5.5)));
    }

    @Test
    public void testSquareTrigger() {
        // Tested using board, console, and telnet
    }

    @Test
    public void testTriangleTrigger() {
        // Tested using board, console, and telnet
    }

    @Test
    public void testRandom() {
        // I tested whether getPos and changePos worked for Ball. They do :)
        /*
         * System.out.println(b1.getPos().toString()); Vect oldP = b1.getPos();
         * Vect newP = new Vect(3.0, 7.0); b1.changePos(newP);
         * System.out.println(b1.getPos().toString()); b1.changePos(oldP);
         * System.out.println(b1.getPos().toString());
         */

        // I'm going to test how reflectWall works
        /*
         * Vect orig = new Vect(4.0, 4.0); LineSegment lsN = new
         * LineSegment(0.0, 0.0, 2.0, 0.0); LineSegment lsS = new
         * LineSegment(0.0, 2.0, 2.0, 2.0); LineSegment lsE = new
         * LineSegment(2.0, 0.0, 2.0, 2.0); LineSegment lsW = new
         * LineSegment(0.0, 0.0, 0.0, 2.0); Vect ans1 =
         * Geometry.reflectWall(lsN, orig); Vect ans2 =
         * Geometry.reflectWall(lsS, orig); Vect ans3 =
         * Geometry.reflectWall(lsE, orig); Vect ans4 =
         * Geometry.reflectWall(lsW, orig); System.out.println(orig.toString());
         * System.out.println(ans1.toString());
         * System.out.println(ans2.toString());
         * System.out.println(ans3.toString());
         * System.out.println(ans4.toString());
         */

        // I'm going to test whether move(ball) works.
        System.out.println(b3.getPos().toString());
        System.out.println("go!\n");
        for (int x = 0; x < 50; x++) {
            Vect oldPos = b3.getPos();
            b3.move(0.005);
            System.out.print(b3.getPos().toString() + "  ----  ");
            System.out.println((b3.getPos().minus(oldPos)).toString());
        }
    }

}
