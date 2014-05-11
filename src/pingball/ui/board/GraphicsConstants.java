package pingball.ui.board;

public class GraphicsConstants {
    public static final int CELL_SIZE = 20;
    public static final int SIZE = 440;
    public static final int STROKE_WIDTH = 3;
    
    public static int convertX(double x) {
        return (int)(x + 1) * CELL_SIZE;
    }
    
    public static int convertY(double y) {
        return (int)(y + 1) * CELL_SIZE;
    }
}