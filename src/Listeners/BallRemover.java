package Listeners;

import Game.GameLevel;
import Interfaces.HitListener;
import SpritesAndCollidables.Ball;
import SpritesAndCollidables.Block;

/**
 * remover.BallRemover class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;

    /**
     * constructor.
     * @param gameLevel
     */
    public BallRemover(GameLevel gameLevel) {
        this.game = gameLevel;
    }

    /**
     * This method is in charge of removing balls from the game if hit the bottom border block,
     * as well as keeping count of the number of balls that remain by decreasing the amount.
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.decreaseBalls();
        hitter.removeFromGame(this.game);
    }
}