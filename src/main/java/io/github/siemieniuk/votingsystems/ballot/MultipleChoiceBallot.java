package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.ArrayList;
import java.util.Set;

/**
 * A class representing a multiple choice ballot.
 * This is a ballot which contains multiple votes of the same weight.
 */
public final class MultipleChoiceBallot extends Ballot<ArrayList<CandidateEntry>> {

    /**
     * Creates a new ballot based on ArrayList of candidate entries
     * @param preferences An array list of objects CandidateEntry
     */
    public MultipleChoiceBallot(ArrayList<CandidateEntry> preferences) {
        super(preferences);
    }

    /**
     * Creates a new instance of multiple choice ballot based on deep copy of other one
     * @param other An instance of MultipleChoiceBallot to be deeply copied
     */
    public MultipleChoiceBallot(MultipleChoiceBallot other) {
        super(other);
    }

    @Override
    public boolean isValidTo(Set<CandidateEntry> setOfAllCandidates) {
        return setOfAllCandidates.containsAll(getPreferences());
    }

    @Override
    protected void validate(ArrayList<CandidateEntry> preferences) {
        // No validation
    }

    @Override
    public String toString() {
        return "MultipleChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
