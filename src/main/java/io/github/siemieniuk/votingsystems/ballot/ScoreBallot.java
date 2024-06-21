package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.EntryWithNumber;
import io.github.siemieniuk.votingsystems.ballot.entry.ScoreBallotEntry;

import java.util.List;
import java.util.Set;

public class ScoreBallot
        extends Ballot<List<ScoreBallotEntry>>{

    public ScoreBallot(List<ScoreBallotEntry> preferences) {
        super(preferences);
    }

    public boolean isValidTo(Set<CandidateEntry> setOfAllCandidates) {
        List<CandidateEntry> entries = getPreferences().stream()
                .map(EntryWithNumber::getPreference)
                .toList();

        return setOfAllCandidates.containsAll(entries);
    }

    @Override
    protected void validate(List<ScoreBallotEntry> preferences) {
        for (ScoreBallotEntry entry : preferences) {
            if (entry.getScore() == Double.NEGATIVE_INFINITY || entry.getScore() == Double.POSITIVE_INFINITY) {
                throw new IllegalArgumentException("Provided entry with an infinite score!");
            }
        }
    }

    @Override
    public String toString() {
        return "ScoreBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
