package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.ArrayList;
import java.util.Set;

public class MultipleChoiceBallot extends Ballot<ArrayList<CandidateEntry>> {

    public MultipleChoiceBallot(ArrayList<CandidateEntry> preferences) {
        super(preferences);
    }

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
