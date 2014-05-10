package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import physics.LineSegment;
import pingball.board.Absorber;

public class AbsorberPainter implements GadgetPainter {
    private final Color absorberColor = Color.GRAY;
    private Absorber gadget;
    
    public AbsorberPainter(Absorber gadget) {
        this.gadget = gadget;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(absorberColor);
        g.setStroke(new BasicStroke(GraphicsConstants.STROKE_WIDTH));
        List<LineSegment> segments = gadget.getLineSegments();
        g.drawLine(
                GraphicsConstants.convertX(segments.get(0).p1().x()),
                GraphicsConstants.convertY(segments.get(0).p1().y()),
                GraphicsConstants.convertX(segments.get(0).p2().x()),
                GraphicsConstants.convertY(segments.get(0).p2().y()));
        g.drawLine(
                GraphicsConstants.convertX(segments.get(1).p1().x()),
                GraphicsConstants.convertY(segments.get(1).p1().y()),
                GraphicsConstants.convertX(segments.get(1).p2().x()),
                GraphicsConstants.convertY(segments.get(1).p2().y()));
        g.drawLine(
                GraphicsConstants.convertX(segments.get(2).p1().x()),
                GraphicsConstants.convertY(segments.get(2).p1().y()),
                GraphicsConstants.convertX(segments.get(2).p2().x()),
                GraphicsConstants.convertY(segments.get(2).p2().y()));
        g.drawLine(
                GraphicsConstants.convertX(segments.get(3).p1().x()),
                GraphicsConstants.convertY(segments.get(3).p1().y()),
                GraphicsConstants.convertX(segments.get(3).p2().x()),
                GraphicsConstants.convertY(segments.get(3).p2().y()));
    }

}
