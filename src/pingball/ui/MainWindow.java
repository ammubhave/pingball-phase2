package pingball.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import pingball.board.Board;

public class MainWindow extends JFrame implements KeyListener {
    GameDisplay display;
    private Board boardForKeys;

    public MainWindow(Board board) {
        super("Pingball");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        display = new GameDisplay(board);
        boardForKeys = board;
        this.add(display, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String keyName = e.getKeyText(e.getKeyCode());
        keyName = keyName.replaceAll(" ", "");
        keyName = keyName.toLowerCase();
        boardForKeys.handleKeyDown(keyName);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String keyName = e.getKeyText(e.getKeyCode());
        keyName = keyName.replaceAll(" ", "");
        keyName = keyName.toLowerCase();
        boardForKeys.handleKeyDown(keyName);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
