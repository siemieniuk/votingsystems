package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.ScoreBallotEntry;

import java.util.List;

public class ScoreBallot
        extends Ballot<List<ScoreBallotEntry<CandidateEntry<?, ?>>>>{

    public ScoreBallot(List<ScoreBallotEntry<CandidateEntry<?, ?>>> preferences) {
        super(preferences);
    }

    @Override
    public String toString() {
        return "ScoreBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
