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

/**
 * 
 * 
 *
 */
public class GameDisplay extends JPanel {
    Board board;
    Color backgroundColor = Color.white;
    final int width = 440;
    final int height = 440;

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
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        drawGadgets(g2);
        drawBalls(g2);
        drawMenu(g2);
        // other drawings methods needs to be added here
    }

    private void drawMenu(final Graphics2D g) {
        this.add(new DisplayMenu(), BorderLayout.NORTH);
    }

    private void drawGadgets(final Graphics2D g) {
        for (GadgetPainter painter : board.getGadgetPainters()) {
            painter.paint(g);
        }
    }

    private void drawBalls(final Graphics2D g) {

    }

    /*
     * Make the drawing buffer entirely white.
     */
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
