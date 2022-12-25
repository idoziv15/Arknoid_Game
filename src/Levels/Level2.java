package Levels;

import Interfaces.LevelInformation;
import Interfaces.Sprite;
import OtherClasses.Velocity;
import SpritesAndCollidables.Block;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level2 class.
 */
public class Level2 implements LevelInformation {
    private static final String LEVEL_NAME = "Wide Easy";
    private static final int NUMBER_OF_BALLS = 10;
    private static final int PADDLE_SPEED = 18;
    private static final int BALL_SPEED = 6;
    private static final int PADDLE_WIDTH = 650;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 15;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int BLOCKS_STARTING_X = 25;
    private static final int BLOCKS_STARTING_Y = 250;
    private static final int X_SUN = 140;
    private static final int Y_SUN = 150;
    private static final int RADIUS_SUN = 40;
    private static final int STARTING_ANGLE = -35;
    private static final int ANGLE_CHANGE = 9;

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
        return new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, FRAME_HEIGHT), Color.white) {
            @Override
            public void drawOn(DrawSurface d) {
                // Draw the background:
                d.setColor(Color.white);
                d.fillRectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
                // Draw the lines of the sun:
                d.setColor(new Color(0xF1EDB3));
                for (int i = 0; i < 100; i++) {
                    d.drawLine(X_SUN, Y_SUN, 20 + (i * 8), Y_SUN + 120);
                }
                // Draw the circles of the sun:
                d.setColor(new Color(0xF1EDB3));
                d.fillCircle(X_SUN, Y_SUN, RADIUS_SUN + 20);
                d.setColor(new Color(0xE4D577));
                d.fillCircle(X_SUN, Y_SUN, RADIUS_SUN + 10);
                d.setColor(new Color(0xFFD81D));
                d.fillCircle(X_SUN, Y_SUN, RADIUS_SUN);
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
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Rectangle newRect = new Rectangle(
                    new Point(BLOCKS_STARTING_X + BLOCK_WIDTH * i, BLOCKS_STARTING_Y), BLOCK_WIDTH, BLOCK_HEIGHT);
            Color color;
            if (i < 2) {
                color = Color.RED;
            } else if (i < 4) {
                color = Color.ORANGE;
            } else if (i < 6) {
                color = Color.yellow;
            } else if (i < 9) {
                color = Color.green;
            } else if (i < 11) {
                color = Color.BLUE;
            } else if (i < 13) {
                color = Color.PINK;
            } else {
                color = new Color(0, 200, 250);
            }
            Block newBlock = new Block(newRect, color);
            listOfBlocks.add(newBlock);
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
