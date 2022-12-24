// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package Screens;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation extends KeyPressDecorator {
    private final String key;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param sensor
     * @param key
     * @param animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        super(sensor, animation);
        this.key = key;
        this.isAlreadyPressed = true;
    }

    /**
     * This method runs an animation screen until the given key is being pressed.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        super.doOneFrame(d);
        if (super.getSensor().isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                super.setStop();
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
}
