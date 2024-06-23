package io.github.siemieniuk.votingsystems.strategy.highestaverages;

public class SainteLague
        extends HighestAveragesStrategy {

    public SainteLague() {
        super(1);
    }

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
