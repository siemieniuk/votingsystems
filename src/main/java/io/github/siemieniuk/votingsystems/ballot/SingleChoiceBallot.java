package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

/**
 * A single choice ballot, where a preference is defined as a CandidateEntry
 */
public class SingleChoiceBallot extends Ballot<CandidateEntry> {

    public SingleChoiceBallot(CandidateEntry preference) {
        super(preference);
    }

    @Override
    public String toString() {
        return "SingleChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
