package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CumulativeBallotEntry;

import java.util.List;

public class CumulativeBallot<T> extends Ballot<List<CumulativeBallotEntry<T>>> {

    public CumulativeBallot(List<CumulativeBallotEntry<T>> preferences) {
        super(preferences);
    }

    @Override
    public boolean isValid() {
        double sum = 0.0D;
        for (CumulativeBallotEntry<?> entry : getPreferences()) {
            sum += entry.getScore();
        }
        return sum > 0.999 && sum <= 1.001;
    }

    @Override
    public String toString() {
        return "CumulativeBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
