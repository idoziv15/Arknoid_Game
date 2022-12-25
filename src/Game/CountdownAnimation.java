package Game;

import Collections.SpriteCollection;
import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * CountdownAnimation class.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean displayCountDown;
    private static final int FONT_SIZE = 42;
    private static final int MOVE_LEFT = 20;

    /**
     * Constructor.
     *
     * @param numOfSeconds
     * @param countFrom
     * @param gameScreen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = (numOfSeconds * 1000) / countFrom;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.displayCountDown = true;
    }

    /**
     * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds,
     * and on top of them it will show a countdown from countFrom back to 1, where each number will
     * appear on the screen for (numOfSeconds / countFrom) seconds, before it is replaced with the next one.
     *
     * @param d
     */
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        if (this.countFrom < -1) {
            this.displayCountDown = false;
            return;
        }

        if (this.countFrom == 0) {
            d.drawText(d.getWidth() / 2 - MOVE_LEFT, d.getHeight() / 2, "Go!", FONT_SIZE);
        } else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.format("%s", this.countFrom), FONT_SIZE);
        }
        this.countFrom--;
        sleeper.sleepFor((long) this.numOfSeconds);
    }

    /**
     * This method indicates if the countdown animation display should stop if the time has passed.
     *
     * @return true or false
     */
    public boolean shouldStop() {
        return !this.displayCountDown;
    }
}
