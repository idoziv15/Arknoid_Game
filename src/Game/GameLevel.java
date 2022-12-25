package Game;

import Collections.GameEnvironment;
import Collections.SpriteCollection;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Animation;
import Interfaces.Collidable;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Listeners.ScoreTrackingListener;
import OtherClasses.Counter;
import OtherClasses.Velocity;
import Screens.KeyPressStoppableAnimation;
import Screens.PauseScreen;
import SpritesAndCollidables.LevelName;
import SpritesAndCollidables.Block;
import SpritesAndCollidables.ScoreIndicator;
import SpritesAndCollidables.Ball;
import SpritesAndCollidables.Paddle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter blocksCounter = new Counter();
    private final Counter remainingBalls = new Counter();
    private final Counter score;
    private final KeyboardSensor keyboardSensor;
    private final AnimationRunner runner;
    private final LevelInformation level;
    private boolean running;
    private boolean disqualification = false;
    private static final int COUNTFROM = 3;
    private static final int SECONDS = 2;
    private static final int RADIUS = 8;
    private static final int PADDLE_START_Y = 565;
    private static final int PADDLE_HEIGHT = 15;
    private static final int FRAME_WIDTH = 800;
    private static final int END_LEVEL_POINTS = 100;

    /**
     * constructor for the game object.
     * @param level - specific level of the game
     * @param ar - AnimationRunner of the game
     * @param ks - KeyboardSensor of the game
     * @param score - score counter of the game
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.level = level;
        this.runner = ar;
        this.keyboardSensor = ks;
        this.score = score;
    }

    /**
     * This function adds certain collidable to the Collections.GameEnvironment.
     *
     * @param c a Interfaces.Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This function adds certain Interfaces.Sprite to the Sprites collection.
     *
     * @param s a Interfaces.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Getter for this game GameEnvironment.
     *
     * @return GameEnvironment object
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * This method sets 4 blocks that are going to be the borders of the game.
     *
     * @return blocks- a list of block borders
     */
    public java.util.List<Block> setBlocksBorders() {
        java.util.List<Block> blocks = new ArrayList<>();
        // Top border:
        blocks.add(new Block(new Rectangle(new Point(0, 20), 800, 20), Color.GRAY));
        // Left border:
        blocks.add(new Block(new Rectangle(new Point(0, 40), 20, 560), Color.GRAY));
        // Right border:
        blocks.add(new Block(new Rectangle(new Point(780, 40), 20, 560), Color.GRAY));
        // Bottom border:
        blocks.add(new Block(new Rectangle(new Point(20, 600), 760, 20), Color.GRAY));
        return blocks;
    }

    /**
     * This method decrease the amount of balls left in the game by 1.
     */
    public void decreaseBalls() {
        this.remainingBalls.decrease(1);
    }

    /**
     * This method initialize the balls of the game.
     *
     * @param num
     * @param ballVelocities
     */
    public void initializeBalls(int num, List<Velocity> ballVelocities) {
        double startingX = (float) FRAME_WIDTH / 2;
        for (int i = 0; i < num; i++) {
            Ball ball = new Ball(startingX, PADDLE_START_Y - RADIUS, RADIUS, Color.white);
            ball.setVelocity(ballVelocities.get(i));
            ball.addToGame(this);
        }
        // Update balls count:
        this.remainingBalls.increase(num);
    }

    /**
     * This method initialize the borders blocks of the game.
     *
     * @param ballRemover
     */
    public void initializeBorders(BallRemover ballRemover) {
        java.util.List<Block> blocks = setBlocksBorders();
        for (Block block : blocks) {
            if (block.getCollisionRectangle().getWidth() == 760) {
                block.addHitListener(ballRemover);
            }
            block.addToGame(this);
        }
    }

    /**
     * This method initialize the paddle of the game.
     * @param paddleWidth
     * @param paddleSpeed
     */
    public void initializePaddle(int paddleWidth, int paddleSpeed) {
        int startX = (FRAME_WIDTH / 2) - (paddleWidth / 2);
        Rectangle padlleRect = new Rectangle(new Point(startX, PADDLE_START_Y), paddleWidth, PADDLE_HEIGHT);
        Paddle paddle = new Paddle(padlleRect, Color.ORANGE, this.keyboardSensor, paddleSpeed);
        paddle.addToGame(this);
    }

    /**
     * This method initialize the blocks of the game.
     *
     * @param blockRemover
     * @param scoreTrackingListener
     * @param blocksList
     */
    public void initializeBlocks(BlockRemover blockRemover, ScoreTrackingListener scoreTrackingListener,
                                 List<Block> blocksList) {
        this.blocksCounter.increase(this.level.numberOfBlocksToRemove());
        for (Block block: blocksList) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
//        Color[] color = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.pink, Color.GREEN};
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 12 - i; j++) {
//                Rectangle newRect = new Rectangle(new Point(width - 20 - ((j + 1) * 45), 120 + i * 20), 45, 20);
//                Block newBlock = new Block(newRect, color[i]);
//                newBlock.addToGame(this);
//                newBlock.addHitListener(blockRemover);
//                newBlock.addHitListener(scoreTrackingListener);
//                this.blocksCounter.increase(1);
//            }
//        }
    }

    /**
     * This method initialize the background of the game.
     *
     * @param bg - background
     */
    public void initializeBackground(Sprite bg) {
        this.sprites.addSprite(bg);
    }

    /**
     * Initialize a new game: create the Blocks, Borders, Balls and a SpritesAndCollidables.
     * Paddle and add them to the game.
     * Also, it creates a listeners for the hit events of the blocks, and of the score count.
     */
    public void initialize() {
        // start the level:
        this.running = true;

        // Initialize background:
        initializeBackground(this.level.getBackground());

        // Add the paddle:
        initializePaddle(this.level.paddleWidth(), this.level.paddleSpeed());

        // Initialize balls and add them to sprite collection:
        initializeBalls(this.level.numberOfBalls(), this.level.initialBallVelocities());

        // Create a ballRemover:
        BallRemover ballRemover = new BallRemover(this);

        // Create a ScoreTrackingListener:
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        this.sprites.addSprite(scoreIndicator);

        // Create Level Name and write it ont he game:
        LevelName levelName = new LevelName(this.level.levelName());
        this.sprites.addSprite(levelName);

        // Add the blocks of the borders:
        initializeBorders(ballRemover);

        // Create a blockRemover:
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);

        // Initialize array of blocks and add to sprite collection:
        List<Block> blocksList = this.level.blocks();
        initializeBlocks(blockRemover, scoreTrackingListener, blocksList);
    }

    /**
     * This method runs the game - start the animation loop.
     */
    public void run() {
        // countdown before turn starts:
        this.runner.run(new CountdownAnimation(SECONDS, COUNTFROM, this.sprites));
        // run the level:
        this.running = true;
        this.runner.run(this);
    }

    /**
     * This method removes given collidable from this game environment.
     *
     * @param c
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().delCollidable(c);
    }

    /**
     * This method removes given sprite from this game sprites' collection.
     *
     * @param s
     */
    public void removeSprite(Sprite s) {
        this.sprites.delSprite(s);
    }

    /**
     * This method indicates whether the animation should be continued or stopped according to its state.
     * If the space key has been pressed- stop the animation.
     * @return boolean - true or false
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This method draws each frame all the sprites of the game, and notify all of them time has passed.
     * This method also checks each frame if the user got disqualification or finished the level.
     * If so, then the level should stop.
     * Also, if the "p" keypress hd been pressed- pause the game.
     * @param d a surface
     */
    public void doOneFrame(DrawSurface d) {
        if (this.remainingBalls.getValue() < 1 || this.blocksCounter.getValue() <= 0) {
            if (this.blocksCounter.getValue() <= 0) {
                this.score.increase(END_LEVEL_POINTS);
            } else {
                this.disqualification = true;
            }
            this.running = false;
        }
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * This method returns if the player had disqualification.
     * @return true or false
     */
    public boolean isDisqualification() {
        return this.disqualification;
    }
}