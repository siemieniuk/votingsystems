package io.github.siemieniuk.votingsystems.strategy.highestaverages;

/**
 * This class implements Adams method as a voting system.
 * It is an implementation of highest averages method, where the divisor is defined as divisor(k) = k
 */
public final class Adams
        extends HighestAveragesStrategy {

    /**
     * Creates a new Adams instance with one available seat.
     */
    public Adams() {
        super(1);
    }

    /**
     * Creates a new Adams instance with user's defined available seats.
     * @param seats A number of available seats
     */
    public Adams(int seats) {
        super(seats);
    }

    @Override
    public double divisorFormula(int k) {
        if (k > 0) {
            return k;
        }
        if (k == 0) {
            return 1e-6;
        }
        throw new IllegalArgumentException("K cannot be negative (" + k + " provided)");
    }
}
