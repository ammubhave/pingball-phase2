package warmup2;
import physics.Vect;

/**
 * TODO: put documentation for your class here
 */
public class Main {
    
    /**
     * TODO: describe your main function's command line arguments here
     */
    public static void main(String[] args) {
        Ball b = new Ball(new int[] {1,1}, new Vect(1, 8));
        Board board = new Board(b);
        while(true) {
            try {
                //sending the actual Thread of execution to sleep X milliseconds
                Thread.sleep(50);
            } catch(InterruptedException ie) {}
            
            board.go();
            System.out.println(board);
        }
    }
}