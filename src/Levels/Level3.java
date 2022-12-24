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
 * Level3 class.
 */
public class Level3 implements LevelInformation {
    private static final String LEVEL_NAME = "Green 3";
    private static final int NUMBER_OF_BALLS = 2;
    private static final int PADDLE_SPEED = 20;
    private static final int BALL_SPEED = 6;
    private static final int STARTING_ANGLE = 20;
    private static final int ANGLE_CHANGE = 20;
    private static final int PADDLE_WIDTH = 100;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 45;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int BLOCKS_STARTING_X = FRAME_WIDTH - 20;
    private static final int BLOCKS_STARTING_Y = 150;
    private static final int NUMBER_OF_ROWS = 5;
    private static final int X_LIGHT = 119;
    private static final int Y_LIGHT = 208;
    private static final int RADIUS_LIGHT = 8;
    private static final Color BACKGROUND_COLOR = new Color(51, 124, 45);

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
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(STARTING_ANGLE + i * ANGLE_CHANGE, BALL_SPEED));
        }
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
                d.setColor(BACKGROUND_COLOR);
                d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
                // Draw the building:
                d.setColor(Color.white);
                d.fillRectangle(70, 440, 90, 160);
                d.setColor(new Color(0x343233));
                d.fillRectangle(105, 390, 28, 50);
                d.setColor(new Color(0x434142));
                d.fillRectangle(114, 210, 10, 180);
                d.setColor(new Color(0xCE8D51));
                d.fillCircle(X_LIGHT, Y_LIGHT, RADIUS_LIGHT + 3);
                d.setColor(new Color(0xCE5F4B));
                d.fillCircle(X_LIGHT, Y_LIGHT, RADIUS_LIGHT);
                d.setColor(Color.white);
                d.fillCircle(X_LIGHT, Y_LIGHT, RADIUS_LIGHT - 5);
                d.setColor(new Color(0x292728));
                for (int i = 0; i < 5; i++) {
                    d.fillRectangle(70, 440 + (i * 34), 90, 8);
                }
                for (int i = 0; i < 6; i++) {
                    d.fillRectangle(70 + (i * 18), 440, 8, 160);
                }
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
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            Color color;
            if (i == 0) {
                color = new Color(103, 210, 238);
            } else if (i == 1) {
                color = new Color(43, 129, 147);
            } else if (i == 2) {
                color = new Color(17, 76, 96);
            } else if (i == 3) {
                color = new Color(4, 31, 61);
            } else {
                color = new Color(1, 15, 42);
            }
            for (int j = 10 - i, k = 1; j > -1; j--, k++) {
                Rectangle newRect = new Rectangle(new Point(BLOCKS_STARTING_X - BLOCK_WIDTH * k,
                        BLOCKS_STARTING_Y + BLOCK_HEIGHT * i), BLOCK_WIDTH, BLOCK_HEIGHT);
                Block newBlock = new Block(newRect, color);
                listOfBlocks.add(newBlock);
            }
        }
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
