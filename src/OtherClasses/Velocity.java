package OtherClasses;

import Geometry.Point;

/**
 * OtherClasses.Velocity Class specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx horizontal velocity
     * @param dy horizontal velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    // Get`s and Set`s:

    /**
     * This method return the velocity dx.
     *
     * @return dx (double)
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method return the velocity dy.
     *
     * @return dy (double)
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method sets the velocity dx.
     *
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * This method sets the velocity dy.
     *
     * @param dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * This method take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p - a Geometry.Point with the position of the ball
     * @return a Geometry.Point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * This method set new OtherClasses.Velocity according to given angle and speed of the ball.
     *
     * @param angle - of the ball
     * @param speed - current speed of the ball
     * @return OtherClasses.Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        double dy = speed * Math.cos(angle) * -1;
        return new Velocity(dx, dy);
    }

    /**
     * This method calculates the velocity vector size and returns it as speed.
     *
     * @param dx horizontal velocity
     * @param dy vertical velocity
     * @return size of the calculated velocity (double)
     */
    public double getSpeed(double dx, double dy) {
        return Math.sqrt((dx * dx) + (dy * dy));
    }
}