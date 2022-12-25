package Interfaces;

import Geometry.Point;
import Geometry.Rectangle;
import OtherClasses.Velocity;
import SpritesAndCollidables.Ball;
import biuoop.DrawSurface;

/**
 * Interfaces.Collidable interface.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return a Geometry.Rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint Geometry.Point
     * @param currentVelocity OtherClasses.Velocity
     * @param hitter a ball object
     * @return new OtherClasses.Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * This method draws a collidable on the given surface.
     * @param d - a surface
     */
    void drawOn(DrawSurface d);
}