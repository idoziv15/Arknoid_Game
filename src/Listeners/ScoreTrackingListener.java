package Listeners;

import Interfaces.HitListener;
import OtherClasses.Counter;
import SpritesAndCollidables.Ball;
import SpritesAndCollidables.Block;

/**
 * Listeners.ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method increases the score counter by 5 points on every hit of a block.
     *
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}