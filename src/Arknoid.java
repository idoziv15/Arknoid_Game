import Game.AnimationRunner;
import Game.GameFlow;
import Interfaces.LevelInformation;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Game class.
 */
public class Arknoid {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final String TITLE = "Arknoid";


    /**
     * If the user didn't specify any levels, add all the levels to the list. If the user did specify levels, add only the
     * levels he specified
     *
     * @return A list of levels.
     */
    public static List<LevelInformation> getLevels(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        } else {
            for (String arg : args) {
                switch (arg) {
                    case "1" -> levels.add(new Level1());
                    case "2" -> levels.add(new Level2());
                    case "3" -> levels.add(new Level3());
                    case "4" -> levels.add(new Level4());
                }
            }
        }
        return levels;
    }


    /**
     * It creates a GUI surface, and then runs the game with the given list of levels
     */
    public static void main(String[] args) {
        // Create the 4 levels:
        List<LevelInformation> levels = getLevels(args);

        // Create GUI surface, and then run the game with the given list of levels:
        GUI gui = new GUI(TITLE, FRAME_WIDTH, FRAME_HEIGHT);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor());
        gameFlow.runLevels(levels);
    }
}
