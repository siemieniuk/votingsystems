package io.github.siemieniuk.votingsystems.strategy.highestaverages;

/**
 * This class implements Sainte-Lague method as a voting system.
 * It is an implementation of highest averages method, where the divisor is defined as divisor(k) = k + 0.5
 */
public final class SainteLague
        extends HighestAveragesStrategy {

    /**
     * Creates a new SainteLague instance with one available seat.
     */
    public SainteLague() {
        super(1);
    }

    /**
     * Creates a new SainteLague instance with user's defined available seats.
     * @param seats A number of available seats
     */
    public SainteLague(int seats) {
        super(seats);
    }

    @Override
    public double divisorFormula(int k) {
        if (k < 0) {
            throw new IllegalArgumentException("K cannot be negative (" + k + " provided)");
        }
        return (double) k + 0.5;
    }
}
