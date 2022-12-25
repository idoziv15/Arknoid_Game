package Collections;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;
import OtherClasses.CollisionInfo;

import java.util.ArrayList;

/**
 * Collections.GameEnvironment class.
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * Add the given collidable to the environment.
     *
     * @param c - a collidable
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            collidables.add(c);
        }
    }

    /**
     * Remove a given collidable from the environment.
     *
     * @param c - a collidable
     */
    public void delCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * A getter- returns a list of collidables of this Collections.GameEnvironment.
     *
     * @return - arrayList of collidables
     */
    public java.util.List<Collidable> getColliables() {
        return this.collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory - a given Geometry.Line
     * @return a OtherClasses.CollisionInfo object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollidable = new CollisionInfo(null, null);
        Point closestPoint;
        double distance = 1000000;

        for (Collidable collidable : this.collidables) {
            Rectangle rect = collidable.getCollisionRectangle();

            if (!rect.intersectionPoints(trajectory).isEmpty()) {
                closestPoint = trajectory.closestIntersectionToStartOfLine(rect);
                double tempDistance = trajectory.start().distance(closestPoint);

                if (closestCollidable.collisionPoint() == null) {
                    closestCollidable.setCollisionPoint(closestPoint);
                    closestCollidable.setCollisionObject(collidable);
                    distance = tempDistance;
                } else if (tempDistance < distance) {
                    closestCollidable.setCollisionPoint(closestPoint);
                    closestCollidable.setCollisionObject(collidable);
                    distance = tempDistance;
                }
            }
        }
        return closestCollidable;
    }
}