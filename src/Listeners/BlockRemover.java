// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package Listeners;

import Game.GameLevel;
import Interfaces.HitListener;
import OtherClasses.Counter;
import SpritesAndCollidables.Ball;
import SpritesAndCollidables.Block;

/**
 * remover.BlockRemover class.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game
     * @param removedBlocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * This method is in charge of removing blocks from the game if it gets hit by a ball,
     * as well as keeping count of the number of blocks that remain by decreasing the blocks count.
     *
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}