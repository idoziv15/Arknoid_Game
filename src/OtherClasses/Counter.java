// 318420254 Ido Ziv
/**
 * @author Ido Ziv
 * @version ass6
 * @since 02.06.22
 */

package OtherClasses;

/**
 * OtherClasses.Counter class.
 */
public class Counter {
    private int count = 0;

    /**
     * Add number to current count.
     *
     * @param number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Get current count.
     *
     * @return int - count value
     */
    public int getValue() {
        return this.count;
    }
}