package pingball.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pingball.board.Board;

/**
 * 
 * 
 *
 */
public class GameDisplay extends JPanel {
    Board board;
    Color backgroundColor = Color.white;
    final int width = 400;
    final int height = 800;
    
    /**
     * Default constructor that displays only the menu options
     * 
     */
    public GameDisplay() {
        
    }
    
    /**
     * Constructor for a GUI of the game when a board is provided
     * 
     * @param board game board 
     */
    public GameDisplay(Board board){        
        this.setPreferredSize(new Dimension(width, height));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        drawMenu(g2);
        drawGadgets(g2);
        drawBalls(g2);
        //other drawings methods needs to be added here
    }
    
    
    private void drawMenu(final Graphics2D g){
        
    }
    
    private void drawGadgets(final Graphics2D g){
        
    }
    
    private void drawBalls(final Graphics2D g){
        
    }
    
    /*
     * Make the drawing buffer entirely white.
     */
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
}
