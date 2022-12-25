package SpritesAndCollidables;

import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * LevelName class.
 */
public class LevelName implements Sprite {
    private final String name;

    /**
     * Constructor.
     *
     * @param name
     */
    public LevelName(String name) {
        this.name = name;
    }

    /**
     * draw the LevelName to the screen.
     *
     * @param d a surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        String name = "Level Name: " + this.name;
        d.drawText(600, 15, name, 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        // empty for this assignment
    }
}
