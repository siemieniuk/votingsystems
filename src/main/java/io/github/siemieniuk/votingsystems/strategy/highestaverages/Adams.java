package io.github.siemieniuk.votingsystems.strategy.highestaverages;

public final class Adams
        extends HighestAveragesStrategy {

    public Adams() {
        super(1);
    }

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
