// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package Levels;

import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import OtherClasses.Velocity;
import SpritesAndCollidables.Block;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level1 class.
 */
public class Level1 implements LevelInformation {
    private static final String LEVEL_NAME = "Direct Hit";
    private static final int NUMBER_OF_BALLS = 1;
    private static final int PADDLE_SPEED = 15;
    private static final int PADDLE_WIDTH = 130;
    private static final int DX = 0;
    private static final int DY = -3;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 1;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int X_STARTING_POS = 381;
    private static final int Y_STARTING_POS = 150;
    private static final int X_CIRCLE = 395;
    private static final int Y_CIRCLE = 165;
    private static final int RADIUS = 60;
    private static final Color BLOCK_COLOR = Color.RED;
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    /**
     * This method return the number of balls in the level.
     *
     * @return int - number of balls
     */
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    /**
     * This method returns a list of velocities; the initial velocity of each ball.
     *
     * @return List of Velocity
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(DX, DY));
        return velocities;
    }

    /**
     * This method returns the paddle speed of the level.
     *
     * @return int speed
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * This method returns the paddle width of the level.
     *
     * @return int width
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * This method returns the name of the level.
     *
     * @return String LevelName
     */
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return Sprite background
     */
    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, FRAME_HEIGHT), BACKGROUND_COLOR) {
            @Override
            public void drawOn(DrawSurface d) {
                // Draw the background:
                d.setColor(Color.black);
                d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
                d.setColor(Color.blue);
                // Draw empty circles:
                d.drawCircle(X_CIRCLE, Y_CIRCLE, 2 * RADIUS);
                d.drawCircle(X_CIRCLE, Y_CIRCLE, (RADIUS + RADIUS / 2));
                d.drawCircle(X_CIRCLE, Y_CIRCLE, RADIUS);
                // Draw lines:
                d.drawLine(X_CIRCLE, Y_CIRCLE - 20, X_CIRCLE, Y_CIRCLE - 140);
                d.drawLine(X_CIRCLE, Y_CIRCLE + 20, X_CIRCLE, Y_CIRCLE + 140);
                d.drawLine(X_CIRCLE - 20, Y_CIRCLE, X_CIRCLE - 140, Y_CIRCLE);
                d.drawLine(X_CIRCLE + 20, Y_CIRCLE, X_CIRCLE + 140, Y_CIRCLE);
            }
        };
    }

    /**
     * This method creates all the blocks of the level.
     *
     * @return List of Block
     */
    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<>();
        Rectangle newRect = new Rectangle(new Point(X_STARTING_POS, Y_STARTING_POS), WIDTH, HEIGHT);
        Block newBlock = new Block(newRect, BLOCK_COLOR);
        listOfBlocks.add(newBlock);
        return listOfBlocks;
    }

    /**
     * This method returns the amount of initialized blocks at this level.
     *
     * @return int number of blocks
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_TO_REMOVE;
    }
}
