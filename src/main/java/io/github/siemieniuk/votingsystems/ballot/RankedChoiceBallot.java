package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.Map;
import java.util.Set;

public class RankedChoiceBallot
        extends Ballot<Map<Integer, CandidateEntry>> {

    private int minRanking = 1;
    private int maxRanking = 0;

    public RankedChoiceBallot(Map<Integer, CandidateEntry> preferences) {
        super(preferences);
    }

    @Override
    public boolean isValidTo(Set<CandidateEntry> setOfAllCandidates) {
        for (int i=minRanking; i<=maxRanking; i++) {
            if (!(getPreferences().containsKey(i) && setOfAllCandidates.contains(getPreferences().get(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void validate(Map<Integer, CandidateEntry> preferences) {
        minRanking = 1;
        maxRanking = preferences.keySet().stream().max(Integer::compareTo).orElse(-1);
        if (minRanking > maxRanking) {
            throw new IllegalArgumentException("Max ranking is " + maxRanking + ", should be 1");
        }
        for (int i=minRanking; i<=maxRanking; i++) {
            if (!preferences.containsKey(i)) {
                throw new IllegalArgumentException("Expected key=" + i + " not found!");
            }
        }
    }

    @Override
    public String toString() {
        return "RankedChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
