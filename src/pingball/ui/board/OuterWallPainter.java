package pingball.ui.board;

import java.awt.Graphics;

import pingball.board.OuterWall;

public class OuterWallPainter implements GadgetPainter {
    
    private OuterWall gadget;
    
    public OuterWallPainter(OuterWall gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(Graphics g) {
        
    }
    
}
