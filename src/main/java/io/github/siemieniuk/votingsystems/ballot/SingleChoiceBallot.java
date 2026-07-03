package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.Set;

/**
 * A class representing a single choice ballot.
 * Stores only one particular vote.
 */
public final class SingleChoiceBallot extends Ballot<CandidateEntry> {

    /**
     * Creates a new single choice ballot based on object of class CandidateEntry
     * @param preference An instance of class CandidateEntry
     */
    public SingleChoiceBallot(CandidateEntry preference) {
        super(preference);
    }

    /**
     * Creates a new instance of a single choice ballot based on deep copy of other one
     * @param other An instance of SingleChoiceBallot to be deeply copied
     */
    public SingleChoiceBallot(SingleChoiceBallot other) {
        super(other);
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
