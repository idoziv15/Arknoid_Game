package Interfaces;
import biuoop.DrawSurface;

/**
 * Interfaces.Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d a surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}