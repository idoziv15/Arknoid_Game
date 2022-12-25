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
 * Level4 class.
 */
public class Level4 implements LevelInformation {
    private static final String LEVEL_NAME = "Final Four";
    private static final int NUMBER_OF_BALLS = 3;
    private static final int PADDLE_SPEED = 23;
    private static final int BALL_SPEED = 5;
    private static final int STARTING_ANGLE = 10;
    private static final int ANGLE_CHANGE = 30;
    private static final int PADDLE_WIDTH = 90;
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 105;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int BLOCKS_STARTING_X = FRAME_WIDTH - 25;
    private static final int BLOCKS_STARTING_Y = 110;
    private static final int NUMBER_OF_ROWS = 7;
    private static final int X_LEFTCIRCLE = 80;
    private static final int Y_LEFTCIRCLE = 398;
    private static final int RADIUS = 22;
    private static final int X_RIGHTCIRCLE = 600;
    private static final int Y_RIGHTCIRCLE = 500;
    private static final Color BACKGROUND_COLOR = new Color(92, 176, 224);

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
                // Draw first cloud lines:
                d.setColor(Color.white);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(80 + (i * 10), 400, 60 + (i * 10), FRAME_HEIGHT);
                }
                // Draw first cloud circles:
                d.setColor(new Color(0xC6C7C6));
                d.fillCircle(X_LEFTCIRCLE, Y_LEFTCIRCLE, RADIUS);
                d.fillCircle(X_LEFTCIRCLE + 20, Y_LEFTCIRCLE + 17, RADIUS + 2);
                d.setColor(new Color(0xAEAFAE));
                d.fillCircle(X_LEFTCIRCLE + 40, Y_LEFTCIRCLE - 8, RADIUS + 6);
                d.setColor(new Color(0xA2A3A2));
                d.fillCircle(X_LEFTCIRCLE + 50, Y_LEFTCIRCLE + 19, RADIUS - 2);
                d.fillCircle(X_LEFTCIRCLE + 72, Y_LEFTCIRCLE + 2, RADIUS + 8);
                d.setColor(Color.white);
                // Draw second cloud lines:
                for (int i = 0; i < 10; i++) {
                    d.drawLine(600 + (i * 10), 530, 580 + (i * 10), 600);
                }
                // Draw second cloud circles:
                d.setColor(new Color(0xC6C7C6));
                d.fillCircle(X_RIGHTCIRCLE, Y_RIGHTCIRCLE, RADIUS);
                d.fillCircle(X_RIGHTCIRCLE + 15, Y_RIGHTCIRCLE + 40, RADIUS + 3);
                d.setColor(new Color(0xAEAFAE));
                d.fillCircle(X_RIGHTCIRCLE + 40, Y_RIGHTCIRCLE + 10, RADIUS + 6);
                d.setColor(new Color(0xA2A3A2));
                d.fillCircle(X_RIGHTCIRCLE + 55, Y_RIGHTCIRCLE + 35, RADIUS - 2);
                d.fillCircle(X_RIGHTCIRCLE + 80, Y_RIGHTCIRCLE + 25, RADIUS + 8);
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
                color = new Color(150, 145, 145);
            } else if (i == 1) {
                color = new Color(229, 54, 54);
            } else if (i == 2) {
                color = new Color(239, 227, 73);
            } else if (i == 3) {
                color = new Color(86, 225, 56);
            } else if (i == 4) {
                color = new Color(232, 232, 234);
            } else if (i == 5) {
                color = new Color(232, 158, 232);
            } else {
                color = new Color(153, 234, 215);
            }
            for (int j = 15, k = 1; j > 0; j--, k++) {
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
