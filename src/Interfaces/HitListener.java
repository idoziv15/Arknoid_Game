package Interfaces;

import SpritesAndCollidables.Ball;
import SpritesAndCollidables.Block;

/**
 * Interfaces.HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprites.SpritesAndCollidables.Ball that's doing the hitting.
     * @param beingHit
     * @param hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
