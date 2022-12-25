package Game;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class.
 */
public class AnimationRunner {
    private GUI gui;
    private static final int FRAMES_PER_SEC = 60;

    /**
     * Constructor.
     * @param gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
    }

    /**
     * Getter for this game GUI.
     *
     * @return a GUI object
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * This method gets an animation object and runs it on a given gui every 60 frames per second.
     * @param animation
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / FRAMES_PER_SEC;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);

            // Timing:
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
