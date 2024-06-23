package io.github.siemieniuk.votingsystems.strategy.highestaverages;

public class Dhondt
        extends HighestAveragesStrategy {

    public Dhondt() {
        super(1);
    }

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
