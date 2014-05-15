package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import pingball.board.OuterWall;
import pingball.board.OuterWall.OuterWallsOrientation;

public class OuterWallPainter implements GadgetPainter {
    private final Color wallColor = new Color(0xd8, 0xec, 0xff);
    
    private OuterWall gadget;
    
    public OuterWallPainter(OuterWall gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(final Graphics2D g) {
        g.setColor(wallColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        if (gadget.getNeighborName() != null) return;
        if (gadget.getOrientation() == OuterWallsOrientation.HORIZONTAL)
            g.fillRect((int)(gadget.getX() * GraphicsConstants.CELL_SIZE),
                       (int)(gadget.getY() * GraphicsConstants.CELL_SIZE), 
                       GraphicsConstants.SIZE,
                       GraphicsConstants.CELL_SIZE);
        if (gadget.getOrientation() == OuterWallsOrientation.VERTICAL)
            g.fillRect((int)(gadget.getX() * GraphicsConstants.CELL_SIZE),
                       (int)(gadget.getY() * GraphicsConstants.CELL_SIZE), 
                       GraphicsConstants.CELL_SIZE,
                       GraphicsConstants.SIZE);
    }
    
}
