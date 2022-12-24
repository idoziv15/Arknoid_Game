// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package Interfaces;

import OtherClasses.Velocity;
import SpritesAndCollidables.Block;
import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * This method return the number of balls in the level.
     * @return int - number of balls
     */
    int numberOfBalls();

    /**
     * This method returns a list of velocities; the initial velocity of each ball.
     * @return List of Velocity
     */
    List<Velocity> initialBallVelocities();

    /**
     * This method returns the paddle speed of the level.
     * @return int speed
     */
    int paddleSpeed();

    /**
     * This method returns the paddle width of the level.
     * @return int width
     */
    int paddleWidth();

    /**
     * This method returns the name of the level.
     * @return String LevelName
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite background
     */
    Sprite getBackground();

    /**
     * This method creates all the blocks of the level.
     * @return List of Block
     */
    List<Block> blocks();

    /**
     * This method returns the amount of initialized blocks at this level.
     * @return int number of blocks
     */
    int numberOfBlocksToRemove();
}
