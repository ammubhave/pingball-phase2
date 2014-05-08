package pingball.ui;

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
    
    /**
     * Default constructor that displays only the menu options
     * 
     */
    public GameDisplay(){
        
    }
    
    /**
     * Constructor for a GUI of the game when a board is provided
     * 
     * @param board game board 
     */
    public GameDisplay(Board board){
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
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
     * Main program.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                GameDisplay main = new GameDisplay();
                main.setVisible(true);
            }
        });
    }


}
