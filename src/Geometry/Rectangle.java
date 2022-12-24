// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package Geometry;
import java.util.ArrayList;

/**
 * Geometry.Rectangle class.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Line ceiling;
    private Line floor;
    private Line rightWall;
    private Line leftWall;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft a Geometry.Point
     * @param width (double)
     * @param height (double)
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.setRectangleBordersLines(upperLeft, width, height);
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line
     * @return A list of points that this rectangle is interesting with the given line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new ArrayList<>();
        boolean isIntersecUp = line.isIntersecting(this.ceiling);
        boolean isIntersecDown = line.isIntersecting(this.floor);
        boolean isIntersecRight = line.isIntersecting(this.rightWall);
        boolean isIntersecLeft = line.isIntersecting(this.leftWall);

        if (isIntersecUp) {
            list.add(line.intersectionWith(this.ceiling));
        }
        if (isIntersecDown) {
            list.add(line.intersectionWith(this.floor));
        }
        if (isIntersecRight) {
            list.add(line.intersectionWith(this.rightWall));
        }
        if (isIntersecLeft) {
            list.add(line.intersectionWith(this.leftWall));
        }
        return list;
    }

    /**
     * This function responsible to move the rectangle (of the paddle) right when the right arrow is being pressed.
     *
     * @param dx
     * @param rightBorder - to limit the paddle from the right
     */
    public void moveRight(double dx, double rightBorder) {
        Point newUpperLeft;
        if (this.upperLeft.getX() + this.width + dx > rightBorder) {
            newUpperLeft = new Point((int) (this.upperLeft.getX() + (rightBorder - this.width - this.upperLeft.getX())),
                    (int) this.upperLeft.getY());
        } else {
            newUpperLeft = new Point((int) (this.upperLeft.getX() + dx), (int) this.upperLeft.getY());
        }
        this.upperLeft = newUpperLeft;
        this.setRectangleBordersLines(newUpperLeft, this.width, this.height);
    }

    /**
     * This function responsible to move the rectangle (of the paddle) left when the left arrow is being pressed.
     *
     * @param dx
     * @param leftBorder - to limit the paddle from the left
     */
    public void moveLeft(double dx, double leftBorder) {
        Point newUpperLeft;
        if (this.upperLeft.getX() - dx < leftBorder) {
            newUpperLeft = new Point(leftBorder, (int) this.upperLeft.getY());
        } else {
            newUpperLeft = new Point((int) (this.upperLeft.getX() - dx), (int) this.upperLeft.getY());
        }
        this.upperLeft = newUpperLeft;
        this.setRectangleBordersLines(newUpperLeft, this.width, this.height);
    }

    /**
     * This method generates lines for each side of the rectangle.
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    public void setRectangleBordersLines(Point upperLeft, double width, double height) {
        this.ceiling = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        this.floor = new Line(upperLeft.getX(), upperLeft.getY() + height,
                 upperLeft.getX() + width, upperLeft.getY() + height);
        this.rightWall = new Line(upperLeft.getX() + width, upperLeft.getY(),
                 upperLeft.getX() + width, upperLeft.getY() + height);
        this.leftWall = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * Return the width of the rectangle.
     *
     * @return width (double)
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return height (double)
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getter - Returns the upper line of the rectangle.
     *
     * @return a Geometry.Line
     */
    public Line getCeil() {
        return this.ceiling;
    }

    /**
     * Getter - Returns the lower line of the rectangle.
     *
     * @return a Geometry.Line
     */
    public Line getFloor() {
        return this.floor;
    }

    /**
     * Getter - Returns the right line of the rectangle.
     *
     * @return a Geometry.Line
     */
    public Line getRightWall() {
        return this.rightWall;
    }

    /**
     * Getter - Returns the left line of the rectangle.
     *
     * @return a Geometry.Line
     */
    public Line getLeftWall() {
        return this.leftWall;
    }

    /**
     * Getter - Returns the upper-left point of the rectangle.
     *
     * @return a Geometry.Point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}