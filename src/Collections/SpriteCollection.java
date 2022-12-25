package Collections;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * Collections.SpriteCollection Class.
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites = new ArrayList<>();

    /**
     * This method adds a given sprite to the sprites collection.
     *
     * @param s a Interfaces.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * This method removes a given sprite from the sprites collection.
     *
     * @param s a Interfaces.Interfaces.Sprite
     */
    public void delSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        java.util.List<Sprite> newSprites = new ArrayList<>(this.sprites);
        for (Sprite sprite : newSprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d - a surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}