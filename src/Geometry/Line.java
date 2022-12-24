// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package Geometry;
import java.util.ArrayList;

/**
 * Geometry.Line class.
 */
public class Line {
    private Point start;
    private Point end;
    private boolean isVertical;
    private double incline;
    private double linearConstant;

    /**
     * constructor.
     *
     * @param start
     * @param end
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * constructor.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.isVertical = false;
        if (x1 == x2) {
            this.isVertical = true;
        } else {
            this.incline = calculateIncline(start, end);
            this.linearConstant = calculateConst(start);
        }
    }

    /**
     * this method calculate an incline of a line and returns it.
     *
     * @param start - a Geometry.Point
     * @param end   - a Geometry.Point
     * @return double- incline
     */
    private double calculateIncline(Point start, Point end) {
        return (start.getY() - end.getY()) / (start.getX() - end.getX());
    }

    /**
     * this method calculate an incline of a line and returns it.
     *
     * @param start - a Geometry.Point
     * @return double linearConst
     */
    private double calculateConst(Point start) {
        return (start.getY() - (this.incline * start.getX()));
    }

    /**
     * this method Return the length of the line.
     *
     * @return double - a length
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * this method Returns the start point of the line.
     *
     * @return the start Geometry.Point
     */
    public Point start() {
        return start;
    }

    /**
     * this method Returns the end point of the line.
     *
     * @return the end Geometry.Point
     */
    public Point end() {
        return end;
    }

    /**
     * this method Returns true if the lines intersect, false otherwise.
     *
     * @param other - a Geometry.Line
     * @return true or false
     */
    public boolean isIntersecting(Line other) {
        Point result = intersectionWith(other);
        return result != null;
    }

    /**
     * this method Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other - a Geometry.Line
     * @return the intersection Geometry.Point
     */
    public Point intersectionWith(Line other) {
        // case 1: both inclines are not exist
        if (this.isVertical && other.isVertical) {
            // if both lines are not in the same vertical line
            if (this.start.getX() != other.start.getX()) {
                return null;
            }
            // if both lines are in the same vertical line but intersect only in 1 point
            if (this.start.getY() == other.end.getY()) {
                return new Point(this.start.getX(), this.start.getY());
            } else if (this.end.getY() == other.start.getY()) {
                return new Point(this.end.getX(), this.end.getY());
            } else {
                return null;
            }
        }
        // case 2: first incline exist and the second is not
        if (this.isVertical) {
            double corY = other.incline * this.start.getX() + other.linearConstant;
            Point intersecP = new Point(this.start.getX(), corY);
            if (other.start.getX() <= this.start.getX() && this.start.getX() <= other.end.getX()
                    && intersecP.distance(this.start()) + intersecP.distance(this.end()) == this.length()
            ) {
                return new Point(this.start.getX(), corY);
            } else if (other.start.getX() >= this.start.getX() && this.start.getX() >= other.end.getX()
                    && intersecP.distance(this.start()) + intersecP.distance(this.end()) == this.length()
            ) {
                return new Point(this.start.getX(), corY);
            } else {
                return null;
            }
        }
        // case 3: the second incline exist and the first is not
        if (other.isVertical) {
            double corY = this.incline * other.start.getX() + this.linearConstant;
            Point intersecP = new Point(other.start.getX(), corY);
            if (this.start.getX() <= other.start.getX() && other.start.getX() <= this.end.getX()
                    && intersecP.distance(other.start()) + intersecP.distance(other.end()) == other.length()
            ) {
                return new Point(other.start.getX(), corY);
            } else if (this.start.getX() >= other.start.getX() && other.start.getX() >= this.end.getX()
                    && intersecP.distance(other.start()) + intersecP.distance(other.end()) == other.length()
            ) {
                return new Point(other.start.getX(), corY);
            } else {
                return null;
            }
        }
        // case 4: The two lines are parallel
        if (this.incline == other.incline) {
            // if parallel and not the same equation:
            if (this.linearConstant != other.linearConstant) {
                return null;
            }
            // if the lines intersect in one point only:
            if (this.start.getX() == other.end.getX() && this.start.getX() != other.start.getX()) {
                return new Point(this.start.getX(), this.start.getY());
            } else if (this.end.getX() == other.start.getX() && this.end.getX() != other.end.getX()) {
                return new Point(this.end.getX(), this.end.getY());
            } else {
                return null;
            }
        }
        // case 5: normal intersections (crossing each other)
        double inclineDiff = this.incline - other.incline;
        double linearConstantsDiff = other.linearConstant - this.linearConstant;
        double intersectionX = (linearConstantsDiff) / inclineDiff;
        double intersectionY = intersectionX * this.incline + this.linearConstant;
        if (
                (this.start.getX() <= intersectionX && intersectionX <= this.end.getX())
                        && (other.end.getX() <= intersectionX && intersectionX <= other.start.getX())
        ) {
            return new Point(intersectionX, intersectionY);
        } else if ((this.end.getX() <= intersectionX && intersectionX <= this.start.getX())
                && (other.start.getX() <= intersectionX && intersectionX <= other.end.getX())
        ) {
            return new Point(intersectionX, intersectionY);
        } else if ((this.start.getX() <= intersectionX && intersectionX <= this.end.getX())
                && (other.start.getX() <= intersectionX && intersectionX <= other.end.getX())) {
            return new Point(intersectionX, intersectionY);
        } else if ((this.end.getX() <= intersectionX && intersectionX <= this.start.getX())
                && (other.end.getX() <= intersectionX && intersectionX <= other.start.getX())) {
            return new Point(intersectionX, intersectionY);
        } else {
            return null;
        }
    }

    /**
     * this method return true a given point is contained in this line.
     *
     * @param p - a Geometry.Point
     * @return true or false
     */
    public boolean contain(Point p) {
        double dis1 = this.start.distance(p);
        double dis2 = this.end.distance(p);
        if (this.isVertical) {
            if (p.isXEpsilonEquals(this.start) && dis1 + dis2 == this.length()) {
                return true;
            }
        }
        return p.isYEpsilonEquals(this.start) && dis1 + dis2 == this.length();
    }

    /**
     * This method checks which point from 4 given points is the closest to the start of this line and returns it.
     *
     * @param arr - array of Points
     * @return Geometry.Point
     */
    private Point checkClosestPoint(java.util.List<Point> arr) {
        Point start = this.start();
        Point min = arr.get(0);
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 1; j < arr.size(); j++) {
                if (start.distance(arr.get(i)) < start.distance(arr.get(j))) {
                    min = arr.get(i);
                }
            }
        }
        return min;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect
     * @return intersection Geometry.Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        boolean isIntersecUp = this.isIntersecting(rect.getCeil());
        boolean isIntersecDown = this.isIntersecting(rect.getFloor());
        boolean isIntersecRight = this.isIntersecting(rect.getRightWall());
        boolean isIntersecLeft = this.isIntersecting(rect.getLeftWall());

        java.util.List<Point> intersectionPoints = new ArrayList<Point>();

        if (isIntersecUp) {
            intersectionPoints.add(this.intersectionWith(rect.getCeil()));
        }
        if (isIntersecDown) {
            intersectionPoints.add(this.intersectionWith(rect.getFloor()));
        }
        if (isIntersecRight) {
            intersectionPoints.add(this.intersectionWith(rect.getRightWall()));
        }
        if (isIntersecLeft) {
            intersectionPoints.add(this.intersectionWith(rect.getLeftWall()));
        }

        return checkClosestPoint(intersectionPoints);
    }
}
