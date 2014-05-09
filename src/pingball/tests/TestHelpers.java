package pingball.tests;

import physics.Vect;
import static org.junit.Assert.assertEquals;

public class TestHelpers {
    public static void assertEqualsVect(Vect expected, Vect actual, double delta) {
        assertEquals(expected.x(), actual.x(), delta);
        assertEquals(expected.y(), actual.y(), delta);
    }
    public static void assertEqualsVect(Vect expected, Vect actual) {
        assertEqualsVect(expected, actual, 0.0001);
    }
}
