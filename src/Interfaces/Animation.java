// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package Interfaces;

import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * This method runs an animation until a given key has been pressed.
     *
     * @param d
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method indicates whether the animation should be continued or stopped according to its state.
     * If a given key has been pressed- stop the animation.
     *
     * @return boolean - true or false
     */
    boolean shouldStop();
}
