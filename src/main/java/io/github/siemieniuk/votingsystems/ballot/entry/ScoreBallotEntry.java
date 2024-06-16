package io.github.siemieniuk.votingsystems.ballot.entry;

public class ScoreBallotEntry<T> extends EntryWithNumber<T, Float> {

    public ScoreBallotEntry(T preference, Float score) {
        super(preference, score);
    }
}
