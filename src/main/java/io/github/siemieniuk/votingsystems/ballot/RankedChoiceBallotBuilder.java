package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import lombok.Getter;

import java.util.Hashtable;

public class RankedChoiceBallotBuilder {
    private final Hashtable<Integer, CandidateEntry> preferences;

    @Getter
    private int maxKey = 0;

    public RankedChoiceBallotBuilder() {
        this.preferences = new Hashtable<>();
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

}
