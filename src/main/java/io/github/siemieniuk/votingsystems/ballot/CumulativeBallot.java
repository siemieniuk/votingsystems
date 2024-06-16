package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CumulativeBallotEntry;

import java.util.List;

public class CumulativeBallot extends Ballot<List<CumulativeBallotEntry>> {

    public CumulativeBallot(List<CumulativeBallotEntry> preferences) {
        super(preferences);
    }

    @Override
    public boolean isValid() {
        double sum = 0.0D;
        for (CumulativeBallotEntry entry : getPreferences()) {
            sum += entry.getScore();
        }
        return sum > 0.999 && sum <= 1.001;
    }

    @Override
    public String toString() {
        return "CumulativeBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
