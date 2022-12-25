package Geometry;

/**
 * Geometry.Point class.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method return the distance of this point to the other point.
     *
     * @param other - a Geometry.Point
     * @return distance - double
     */
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        double sumOfSquares = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
        return Math.sqrt(sumOfSquares);
    }

    /**
     * this method checks if this Geometry.Point`x is equally with other Geometry.Point`s x.
     *
     * @param other - a Geometry.Point
     * @return boolean true or false
     */
    public boolean isXEpsilonEquals(Point other) {
        double epsilon = Math.pow(10, -10);
        return Math.abs(this.getX() - other.getX()) < epsilon;
    }

    /**
     * this method checks if this Geometry.Point`y is equally with other Geometry.Point`s y.
     *
     * @param other - a Geometry.Point
     * @return boolean true or false
     */
    public boolean isYEpsilonEquals(Point other) {
        double epsilon = Math.pow(10, -10);
        return Math.abs(this.getY() - other.getY()) < epsilon;
    }

    /**
     * This method Return the x value of this point.
     * @return points`s X value (double)
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method Return y value of this point.
     * @return points`s Y value (double)
     */
    public double getY() {
        return this.y;
    }

    /**
     * This method set the x value of this point.
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This method set the y value of this point.
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
}
