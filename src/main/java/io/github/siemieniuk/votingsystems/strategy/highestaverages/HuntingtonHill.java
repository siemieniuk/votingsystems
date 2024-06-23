package io.github.siemieniuk.votingsystems.strategy.highestaverages;

public class HuntingtonHill
        extends HighestAveragesStrategy {

    public HuntingtonHill() {
        super(1);
    }

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