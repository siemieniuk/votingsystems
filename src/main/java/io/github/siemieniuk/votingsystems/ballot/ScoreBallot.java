package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.ScoreBallotEntry;

import java.util.List;

public class ScoreBallot<T> extends Ballot<List<ScoreBallotEntry<T>>>{

    public ScoreBallot(List<ScoreBallotEntry<T>> preferences) {
        super(preferences);
    }

    @Override
    public String toString() {
        return "ScoreBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
