package pingball.ui;

//THREAD SAFETY ARGUMENT:
//
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import pingball.board.Board;
import pingball.ui.board.GadgetPainter;
import pingball.ui.board.GraphicsConstants;

/**
 * 
 * 
 *
 */
public class GameDisplay extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Board board;
    Color backgroundColor = Color.WHITE;

    /**
     * Default constructor that displays only the menu options
     * 
     */
    public GameDisplay() {

    }

    /**
     * Constructor for a GUI of the game when a board is provided
     * 
     * @param board
     *            game board
     */
    public GameDisplay(Board board) {
        this.board = board;
        this.setPreferredSize(new Dimension(GraphicsConstants.SIZE, GraphicsConstants.SIZE));
        this.setLayout(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        drawGadgets(g2);
        // other drawings methods needs to be added here
    }

    private void drawGadgets(final Graphics2D g) {
        for (GadgetPainter painter : board.getGadgetPainters()) {
            painter.paint(g);
        }
    }
    /*
     * Make the drawing buffer entirely white.
     */
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        //g.setColor(new Color(0xd8, 0xec, 0xff));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(new Color(0xf0, 0xf6, 0xfc));
        for (int x = 0; x < GraphicsConstants.SIZE; x += GraphicsConstants.CELL_SIZE/2) {
           // System.out.println(x);
            g.drawLine(x, 0, x, GraphicsConstants.SIZE);
        }
        for (int y = 0; y < GraphicsConstants.SIZE; y += GraphicsConstants.CELL_SIZE/2) {
            g.drawLine(0, y, GraphicsConstants.SIZE, y);
        }
    }
}
