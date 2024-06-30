package io.github.siemieniuk.votingsystems.strategy.highestaverages;

/**
 * This class implements Huntington-Hill method as a voting system.
 * It is an implementation of highest averages method, where the divisor is defined as divisor(k) = sqrt(k*(k+1))
 */
public final class HuntingtonHill
        extends HighestAveragesStrategy {

    /**
     * Creates a new HuntingtonHill instance with one available seat.
     */
    public HuntingtonHill() {
        super(1);
    }

    /**
     * Creates a new HuntingtonHill instance with user's defined available seats.
     * @param seats A number of available seats
     */
    public HuntingtonHill(int seats) {
        super(seats);
    }

    @Override
    public double divisorFormula(int k) {
        if (k > 0) {
            return Math.sqrt(k * (k+1.0D));
        }
        if (k == 0) {
            return 1e-6;
        }
        throw new IllegalArgumentException("K cannot be negative (" + k + " provided)");
    }
}