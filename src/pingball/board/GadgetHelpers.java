package pingball.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;

/**
 * Helping methods for gadgets
 *
 */
public class GadgetHelpers {
    /**
     * Computers the least collision time for the ball with the lines or circles, whichever is least
     * @param lines the lines with which to collide
     * @param circles the circles with which to collide
     * @param ball the ball which is colliding
     * @return the least time until collision
     */
    public static double leastCollisionTime(List<LineSegment> lines, List<Circle> circles, Ball ball) {
        if (lines == null) lines = new ArrayList<LineSegment>();
        if (circles == null) circles = new ArrayList<Circle>();
        
        double smallestTime = Double.POSITIVE_INFINITY;
        for (LineSegment ls : lines) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
            }
        }
        for (Circle circ : circles) {
            double time = Geometry.timeUntilCircleCollision(circ, ball.getCircle(), ball.getVelocity());
            if (time <= smallestTime) {
                smallestTime = time;
            }
        }
        return smallestTime;
    }

    /**
     * Performs a collision of ball with lines/circles whichever is going to happen using reflectionCoefficient
     * @param lines the lines with which to collide
     * @param circles the circles with which to collide
     * @param ball the ball which is colliding
     * @param reflectionCoefficient the reflection coefficient
     */
    public static void reflectBall(List<LineSegment> lines, List<Circle> circles, Ball ball, double reflectionCoefficient) {
        if (lines == null) lines = new ArrayList<LineSegment>();
        if (circles == null) circles = new ArrayList<Circle>();
        
        double smallestTimeWall = Double.POSITIVE_INFINITY;
        LineSegment smallestWall = null;
        double timeToWall = 0;
        for (LineSegment ls : lines) {
            timeToWall = Geometry.timeUntilWallCollision(ls, ball.getCircle(), ball.getVelocity());
            if (timeToWall <= smallestTimeWall) {
                smallestTimeWall = timeToWall;
                smallestWall = ls;
            }
        }
        Circle smallestCircle = null;
        double smallestTimeCircle = Double.POSITIVE_INFINITY;
        double timeToCircle = 0;
        for (Circle circ : circles) {
            timeToCircle = Geometry.timeUntilCircleCollision(circ, ball.getCircle(), ball.getVelocity());
            if (timeToCircle <= smallestTimeCircle) {
                smallestTimeCircle = timeToCircle;
                smallestCircle = circ;
            }
        }
        if (smallestTimeWall < smallestTimeCircle) {
            ball.changeVelocity(Geometry.reflectWall(smallestWall, ball.getVelocity(), reflectionCoefficient));
        } else {
            ball.changeVelocity(Geometry.reflectCircle(smallestCircle.getCenter(), ball.getPos(), ball.getVelocity(), reflectionCoefficient));
        }
    }
    
    /**
     * Performs a collision of ball with lines/circles whichever is going to happen with reflection coefficient as 1.
     * @param lines the lines with which to collide
     * @param circles the circles with which to collide
     * @param ball the ball which is colliding
     */
    public static void reflectBall(List<LineSegment> lines, List<Circle> circles, Ball ball) {
        reflectBall(lines, circles, ball, 1);
    }
    
    /**
     * Calls the action method of all gadgets
     * @param gadgets the gadgets to call action on
     */
    public static void callActionOnGadgets(List<Gadget> gadgets) {
        for (Gadget gadget : gadgets) {
            gadget.action();
        }
    }
    
    private static Clip clip;
    public static void playBounceSound() {
        try {
            if (clip == null) {
                clip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/bounce.wav").getAbsoluteFile());
                clip.open(audioInputStream);
            }
            if (clip.isRunning())
                clip.stop();
            clip.setFramePosition(0);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
