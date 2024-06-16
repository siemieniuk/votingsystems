package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

public class SingleChoiceBallot extends Ballot<CandidateEntry<?, ?>> {

    public SingleChoiceBallot(CandidateEntry<?, ?> preference) {
        super(preference);
    }

    @Override
    public String toString() {
        return "SingleChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
