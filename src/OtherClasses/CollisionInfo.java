package OtherClasses;

import Geometry.Point;
import Interfaces.Collidable;

/**
 * CollisionInfo class.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable object;

    /**
     * Constructor.
     *
     * @param p Geometry.Point
     * @param c Interfaces.Collidable
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.object = c;
    }

    /**
     * The point at which the collision occurs.
     *
     * @return a Geometry.Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Set the point at which the collision occurs.
     *
     * @param p a Geometry.Point
     */
    public void setCollisionPoint(Point p) {
        this.collisionPoint = p;
    }

    /**
     * The collidable object involved in the collision.
     *
     * @return a Interfaces.Collidable
     */
    public Collidable collisionObject() {
        return this.object;
    }

    /**
     * Set the collidable object involved in the collision.
     *
     * @param o a Interfaces.Collidable
     */
    public void setCollisionObject(Collidable o) {
        this.object = o;
    }
}