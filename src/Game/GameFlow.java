// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package Game;

import Interfaces.LevelInformation;
import OtherClasses.Counter;
import Screens.GameOverScreen;
import Screens.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * GameFlow.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter scoreTrack;
    private boolean victory = true;

    /**
     * Constructor.
     *
     * @param ar animationRunner
     * @param ks keyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoreTrack = new Counter();
    }

    /**
     * This method is running all the levels of the game, level by level.
     * If the user lost, then displays the game-over screen, and if won- display the win message.
     * Then close the program if required.
     *
     * @param levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.scoreTrack);

            // Initialize the level:
            level.initialize();

            // Run each level until it says it should stop:
            while (!level.shouldStop()) {
                level.run();
            }

            // Stop the game if disqualification:
            if (level.isDisqualification()) {
                this.victory = false;
                break;
            }

        }

        GameOverScreen gameOverScreen = new GameOverScreen(this.scoreTrack, this.victory);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", gameOverScreen));
        this.animationRunner.getGui().close();
    }
}

