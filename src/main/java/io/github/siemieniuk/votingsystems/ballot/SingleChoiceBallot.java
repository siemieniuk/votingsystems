package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.Set;

/**
 * A single choice ballot, where a preference is defined as a CandidateEntry
 */
public class SingleChoiceBallot extends Ballot<CandidateEntry> {

    public SingleChoiceBallot(CandidateEntry preference) {
        super(preference);
    }

    @Override
    public boolean isValidTo(Set<CandidateEntry> setOfAllCandidates) {
        return setOfAllCandidates.contains(getPreferences());
    }

    @Override
    protected void validate(CandidateEntry preference) {
        // No validation - empty
    }

    @Override
    public String toString() {
        return "SingleChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
