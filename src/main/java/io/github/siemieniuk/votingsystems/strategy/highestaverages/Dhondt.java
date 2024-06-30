package io.github.siemieniuk.votingsystems.strategy.highestaverages;

/**
 * This class implements Jefferson (D'Hondt) method as a voting system.
 * It is an implementation of highest averages method, where the divisor is defined as divisor(k) = k + 1
 */
public final class Dhondt
        extends HighestAveragesStrategy {

    /**
     * Creates a new DHondt instance with one available seat.
     */
    public Dhondt() {
        super(1);
    }

    /**
     * Creates a new DHondt instance with user's defined available seats.
     * @param seats A number of available seats
     */
    public Dhondt(int seats) {
        super(seats);
    }

    @Override
    public double divisorFormula(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("K cannot be negative (" + k + " provided)");
        }
        return k+1;
    }
}
