package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import pingball.board.Portal;

public class PortalPainter implements GadgetPainter {

    private Portal gadget;
    private final Color circularBumperColor = Color.ORANGE;

    public PortalPainter(Portal gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(circularBumperColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));

        g.fillOval(GraphicsConstants.convertX(this.gadget.getX()), GraphicsConstants.convertY(this.gadget.getY()),
                GraphicsConstants.CELL_SIZE, GraphicsConstants.CELL_SIZE);

    }

}
