package warmup2;

/**
 * Fields include the width and height of the board, as well as a Ball object
 *
 */
public class Board {
    
    private final int height;
    private final int width;
    private final Ball ball;
   
    public Board(Ball b)
    {
        height = 20;
        width = 20;
        ball=b;
    }
    
    public void go(){
        ball.move();
    }
    
    @Override
    public String toString(){
        String board = "";
        for (int i = 0; i <= height; i++){
            for(int j = 0; j <= width; j++){
                if ((i == 0 || i == height) || (j == 0 || j == width)){
                    board = board.concat(".");
                }
                else if(ball.getX() == i && ball.getY() == j){
                    board = board.concat("*");
                }
                else{
                    board = board.concat(" ");
                }
            }
            board = board.concat("\n");
        }
        
        return board;
        
    }
}
