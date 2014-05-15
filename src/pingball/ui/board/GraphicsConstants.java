package pingball.ui.board;

public class GraphicsConstants {
    public static final int CELL_SIZE = (int)(GraphicsConstants.SIZE / 21.5);
    public static final int SIZE = 700;
    public static final int STROKE_WIDTH = 2;
    
    /**
     * Converts column to x coordinate
     * @param x the column
     * @return the x coordinate
     */
    public static int convertX(double x) {
        return (int)((x + 1) * CELL_SIZE);
    }
    
    /**
     * Converts row to y coordinate
     * @param y the row
     * @return the y coordinate
     */
    public static int convertY(double y) {
        return (int)((y + 1) * CELL_SIZE);
    }
    
    /**
     * converts x  coordinate to column
     * @param x the x coordinate
     * @return the column
     */
    public static int convertFromX(int x) {
        return (int)(x/CELL_SIZE-1);
    }
    
    /**
     * Convert y coordinate to row
     * @param y the y coordinate
     * @return the row
     */
    public static int convertFromY(int y) {
        return (int)(y/CELL_SIZE-1);
    }
}
