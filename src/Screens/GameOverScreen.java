package Screens;

import Interfaces.Animation;
import OtherClasses.Counter;
import biuoop.DrawSurface;

/**
 * GameOverScreen class.
 */
public class GameOverScreen implements Animation {
    private final int score;
    private final boolean isVictory;
    private static final int FONT_SIZE = 32;

    /**
     * Constructor.
     *
     * @param score
     * @param victory
     */
    public GameOverScreen(Counter score, boolean victory) {
        this.score = score.getValue();
        this.isVictory = victory;
    }

    /**
     * This method runs a GameOver screen until the space key is being pressed.
     *
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        String text;
        if (this.isVictory) {
            text = "You Win! Your score is ";
        } else {
            text = "Game Over. Your score is ";
        }

        d.drawText(10, d.getHeight() / 2, text + this.score, FONT_SIZE);
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
