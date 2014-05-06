package pingball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pingball.board.Board;
import pingball.parser.BoardBuilder;

/**
 * testing strategy:
 * - test buildBoard on:
 *   - the staff boards
 *   - test out all the gadgets with various orientations
 */
public class BoardFactoryTest {

    @Before
    public void setUp() throws Exception {
        
    }

    @Test
    public void testEmptyBoard() throws IOException {
        Board empty =  BoardBuilder.buildBoard(new File("boards/empty.pb"));
        String s = empty.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += ".                    .\n";
        for (int i = 1; i < 20; i++){
            board += emptyRow;
        }
        
        board += "......................\n";
        assertEquals(board, s);
    }
    
    @Test
    public void testWarmupBoard() throws IOException {
        Board warmup =  BoardBuilder.buildBoard(new File("boards/warmup.pb"));
        String s = warmup.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += ".*                   .\n";
        for (int i = 1; i < 20; i++){
            board += emptyRow;
        }
        
        board += "......................\n";
        assertEquals(board, s);
    }
    
    @Test
    public void testSampleBoard1() throws IOException {
        Board sample =  BoardBuilder.buildBoard(new File("boards/sampleBoard1.pb"));
        String s = sample.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += ".*                   .\n";
        board += emptyRow;
        board += ".########| --########.\n";
        board += ".    O   |      O    .\n";
        board += ".     O        O     .\n";
        board += ".      O      O      .\n";
        board += ".       O    O       .\n";
        board += ".        | --        .\n";
        board += ".        |           .\n";
        board += ".        \\  /        .\n";
        for (int i = 0; i < 9; i++){
            board += emptyRow;
        }
        board += ".====================.\n";
        board += "......................\n";
        assertEquals(s, board);
    }
    
    @Test
    public void testSampleBoard2_1() throws IOException {
        Board sample =  BoardBuilder.buildBoard(new File("boards/sampleBoard2-1.pb"));
        String s = sample.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += ".*                   .\n";
        board += emptyRow;
        board += ".################|   .\n";
        board += ".          O     |   .\n";
        board += ".           O        .\n";
        board += ".            O       .\n";
        board += ".             O      .\n";
        board += ".              O     .\n";
        board += ".               O    .\n";
        board += ".                |   .\n";
        board += ".                |   .\n";
        board += ".                 \\  .\n";
        board += ".                  \\ .\n";
        for (int i = 0; i < 6; i++){
            board += emptyRow;
        }
        board += ".====================.\n";
        board += "......................\n";
        assertEquals(s, board);
    }
    
    @Test 
    public void testSampleBoard2_2() throws IOException {
        Board sample =  BoardBuilder.buildBoard(new File("boards/sampleBoard2-2.pb"));
        String s = sample.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += emptyRow;
        board += emptyRow;
        board += ".  --################.\n";
        board += ".         O          .\n";
        board += ".        O           .\n";
        board += ".       O            .\n";
        board += ".      O             .\n";
        board += ".     O              .\n";
        board += ".    O               .\n";
        board += ".  --                .\n";
        board += emptyRow;
        board += ".  /                 .\n";
        board += ". /                  .\n";
        
        for (int i = 1; i < 7; i++){
            board += emptyRow;
        }

        board += ".====================.\n";
        board += "......................\n";
        assertEquals(s, board);
    }
    
    @Test
    public void testSampleBoard3() throws IOException {
        Board sample =  BoardBuilder.buildBoard(new File("boards/sampleBoard3.pb"));
        String s = sample.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".    O              \\.\n";
        board += ". *                  .\n";
        board += emptyRow;
        board += emptyRow;
        board += ".          | --      .\n";
        board += ".          |         .\n";
        board += emptyRow;
        board += ".########            .\n";
        board += emptyRow;
        board += emptyRow;
        board += ".          *         .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".          ==========.\n";
        board += ".          ==========.\n";
        board += emptyRow;
        board += "......................\n";
        assertEquals(s, board);
    }
    
    @Test
    public void testSampleBoard4() throws IOException {
        Board sample =  BoardBuilder.buildBoard(new File("boards/sampleBoard4.pb"));
        String s = sample.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".    O              \\.\n";
        board += ". *                  .\n";
        board += emptyRow;
        board += emptyRow;
        board += ".          | --      .\n";
        board += ".          |         .\n";
        board += emptyRow;
        board += ".####                .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".          ==========.\n";
        board += ".          ==========.\n";
        board += emptyRow;
        board += "......................\n";
        assertEquals(s, board);
    }
    
    @Test
    public void testSampleBoard5() throws IOException {
        Board sample =  BoardBuilder.buildBoard(new File("boards/sampleBoard5.pb"));
        String s = sample.toString();

        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".          *         .\n";
        board += emptyRow;
        board += ".     --             .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".   # O \\            .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".   =====            .\n";
        board += ".   =====            .\n";
        board += emptyRow;
        board += emptyRow;
        board += "......................\n";
        assertEquals(s, board);
    }
    
    @Test
    public void testSampleBoard6() throws IOException {
        Board sample =  BoardBuilder.buildBoard(new File("boards/sampleBoard6.pb"));
        String s = sample.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += emptyRow;
        board += ". *                  .\n";
        board += ".            ########.\n";
        board += emptyRow;
        board += ".########            .\n";
        board += ".      O             .\n";
        board += ".       O            .\n";
        board += ".        --  |       .\n";
        board += ".            |       .\n";
        board += ".          \\         .\n";
        board += emptyRow;
        board += ".            ########.\n";
        for (int i = 0; i < 7; i++){
            board += emptyRow;
        }
        board += ".====================.\n";
        board += "......................\n";
        assertEquals(s, board);
    }
    
    @Test
    public void testSampleBoard7() throws IOException {
        Board sample =  BoardBuilder.buildBoard(new File("boards/sampleBoard7.pb"));
        String s = sample.toString();
        String board = "......................\n";
        String emptyRow = ".                    .\n";
        board += emptyRow;
        board += ". *                  .\n";
        board += emptyRow;
        board += emptyRow;
        board += ".########            .\n";
        board += ".           ======== .\n";
        board += emptyRow;
        board += ".        --  |       .\n";
        board += ".            |       .\n";
        board += ".          \\         .\n";
        board += ".========            .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".           ======== .\n";
        board += emptyRow;
        board += emptyRow;
        board += emptyRow;
        board += ".====================.\n";
        board += "......................\n";
        assertEquals(s, board);
    }
}
