package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import physics.LineSegment;
import pingball.board.TriangularBumper;

public class TriangularBumperPainter implements GadgetPainter {
    private final Color triangularBumperColor = Color.GREEN;
    private TriangularBumper gadget;
    
    public TriangularBumperPainter(TriangularBumper gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(triangularBumperColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        List<LineSegment> segments = gadget.getLineSegments();
        for (int i = 0; i < 3; i++) {
            g.drawLine(
                    GraphicsConstants.convertX(segments.get(i).p1().x()),
                    GraphicsConstants.convertY(segments.get(i).p1().y()),
                    GraphicsConstants.convertX(segments.get(i).p2().x()),
                    GraphicsConstants.convertY(segments.get(i).p2().y()));
        }
    }
}
