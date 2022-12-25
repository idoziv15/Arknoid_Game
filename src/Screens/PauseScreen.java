package Screens;

import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * PauseScreen class.
 */
public class PauseScreen implements Animation {
    private static final int FONT_SIZE = 32;
    /**
     * This method runs a pause screen until the space key is being pressed.
     *
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", FONT_SIZE);
    }

    /**
     * This method indicates whether the animation should be continued or stopped according to its state.
     * If the space key has been pressed- stop the animation.
     *
     * @return boolean - true or false
     */
    public boolean shouldStop() {
        return false;
    }
}
