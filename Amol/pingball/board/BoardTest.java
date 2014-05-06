package pingball.board;

import static org.junit.Assert.*;

import org.junit.Test;

import physics.Vect;
import pingball.board.OuterWallsGadget.OuterWallsOrientation;
/**
 * test addGadget, rest of the testing is done by
 * the individual gadget tests.
 */
public class BoardTest {

    @Test
    public void testAddGadget() {
        Board board = new Board("testBoard", 1, 1, 1);
        board.addBall(new Ball("testBall", new Vect(0, 0), new Vect(0, 0)));
        board.addGadget(new OuterWallsGadget(new Vect(0, 0), OuterWallsOrientation.HORIZONTAL));
        board.addGadget(new OuterWallsGadget(new Vect(0, 0), OuterWallsOrientation.VERTICAL));
        String str = board.toString();
        for (int i = 0; i < 22; i++)
            assertEquals('.', str.charAt(i));

        for (int i = 0; i < 22; i++)
            assertEquals('.', str.charAt(23*i));

        assertEquals('*', str.charAt(23*1+1));
    }
}
