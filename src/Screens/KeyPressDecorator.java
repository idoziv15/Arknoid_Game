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
public abstract class KeyPressDecorator implements Animation {
    private final KeyboardSensor sensor;
    private final Animation animation;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param sensor
     * @param animation
     */
    public KeyPressDecorator(KeyboardSensor sensor, Animation animation) {
        this.sensor = sensor;
        this.animation = animation;
        this.stop = false;
    }

    /**
     * This method runs an animation screen until the given key is being pressed.
     *
     * @param d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
    }

    /**
     * This method indicates whether the animation should be continued or stopped according to its state.
     * If the space key has been pressed- stop the animation.
     *
     * @return boolean - true or false
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * This method returns this KeyboardSensor.
     * @return keyBoard sensor
     */
    protected KeyboardSensor getSensor() {
        return this.sensor;
    }

    /**
     * This method sets this stop to true.
     */
    protected void setStop() {
        this.stop = true;
    }

}
