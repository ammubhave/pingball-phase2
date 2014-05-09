package pingball.ui.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import pingball.board.Ball;

public class BallPainter implements GadgetPainter {
    private Ball ball;
    
    public BallPainter(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void paint(final Graphics2D g) {
        final Point ballpt = new Point((int)((ball.getCircle().getCenter().x()-0.25)*20), (int)((ball.getCircle().getCenter().y()-0.25)*20));
        final int smileStrokeWidth = 3;
        
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(smileStrokeWidth));
        
        g.fillOval(ballpt.x, ballpt.y,
                       10,
                       10);
    }
}
