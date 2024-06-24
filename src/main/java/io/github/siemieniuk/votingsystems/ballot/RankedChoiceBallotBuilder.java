package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.HashMap;

public final class RankedChoiceBallotBuilder {
    private final HashMap<Integer, CandidateEntry> preferences;
    private int maxKey = 0;

    public RankedChoiceBallotBuilder() {
        this.preferences = new HashMap<>();
    }

    public RankedChoiceBallotBuilder append(CandidateEntry candidateEntry) {
        preferences.put(++maxKey, candidateEntry);
        return this;
    }

    public RankedChoiceBallotBuilder clear() {
        preferences.clear();
        maxKey = 0;
        return this;
    }

    public RankedChoiceBallot build() {
        return new RankedChoiceBallot(preferences);
    }

    public int getMaxKey() {
        return maxKey;
    }
}
