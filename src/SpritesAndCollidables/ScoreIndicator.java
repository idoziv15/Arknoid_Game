package SpritesAndCollidables;

import Interfaces.Sprite;
import OtherClasses.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * SpritesAndCollidables.ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    private final String score = "Score: ";
    private final Counter counter;

    /**
     * Constructor.
     *
     * @param counter
     */
    public ScoreIndicator(Counter counter) {
        this.counter = counter;
    }

    /**
     * This method is drawing the score on a given surface.
     *
     * @param d a surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, 800, 20);
        String str = score + this.counter.getValue();
        d.setColor(Color.BLACK);
        d.drawText(375, 15, str, 15);
    }

    /**
     * Empty for this assignment.
     */
    @Override
    public void timePassed() {

    }
}
