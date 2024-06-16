package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;

public class MultipleChoiceBallot extends Ballot<List<CandidateEntry<?, ?>>> {

    public MultipleChoiceBallot(List<CandidateEntry<?, ?>> preferences) {
        super(preferences);
    }

    @Override
    public boolean isValid() {
        return !getPreferences().isEmpty();
    }

    @Override
    public String toString() {
        return "MultipleChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
