package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public class MultipleChoiceBallot extends Ballot<List<CandidateEntry>> {

    public MultipleChoiceBallot(List<CandidateEntry> preferences) {
        super(preferences);
    }

    @Override
    public boolean isValidTo(Set<CandidateEntry> setOfAllCandidates) {
        return setOfAllCandidates.containsAll(getPreferences());
    }

    @Override
    protected void validate(List<CandidateEntry> preferences) {
        // No validation
    }

    @Override
    public String toString() {
        return "MultipleChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
