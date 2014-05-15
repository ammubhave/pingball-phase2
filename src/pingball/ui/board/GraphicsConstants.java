package pingball.ui.board;

public class GraphicsConstants {
    public static final int CELL_SIZE = (int)(GraphicsConstants.SIZE / 21.5);
    public static final int SIZE = 700;
    public static final int STROKE_WIDTH = 2;
    
    public static int convertX(double x) {
        return (int)((x + 1) * CELL_SIZE);
    }
    
    public static int convertY(double y) {
        return (int)((y + 1) * CELL_SIZE);
    }
}
