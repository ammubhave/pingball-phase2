package pingball.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import pingball.board.Board;


public class MainWindow extends JFrame {
    GameDisplay display;
    
    public MainWindow(Board board) {
        super("Pingball");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        display = new GameDisplay(board);
        this.add(display, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}
