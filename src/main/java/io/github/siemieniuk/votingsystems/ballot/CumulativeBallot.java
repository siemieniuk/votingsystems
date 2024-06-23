package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.CumulativeBallotEntry;

import java.util.ArrayList;
import java.util.Set;

public final class CumulativeBallot extends Ballot<ArrayList<CumulativeBallotEntry>> {

    public CumulativeBallot(ArrayList<CumulativeBallotEntry> preferences) {
        super(preferences);
    }

    public CumulativeBallot(CumulativeBallot other) {
        super(other);
    }

    @Override
    public boolean isValidTo(Set<CandidateEntry> setOfAllCandidates) {
        for (CumulativeBallotEntry entry : getPreferences()) {
            if (!setOfAllCandidates.contains(entry.getPreference())) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void validate(ArrayList<CumulativeBallotEntry> preferences) {
        double sum = 0.0D;
        final double LOWER_BOUND = 0.999D;
        final double UPPER_BOUND = 1.001D;

        for (CumulativeBallotEntry entry : preferences) {
            sum += entry.getScore();
        }

        if (sum < LOWER_BOUND || sum >= UPPER_BOUND) {
            String msg = "Sum of scores of CumulativeBallotEntries must be in range [" +
                    LOWER_BOUND + "; " + UPPER_BOUND + "]; provided sum=" + sum;
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public String toString() {
        return "CumulativeBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
