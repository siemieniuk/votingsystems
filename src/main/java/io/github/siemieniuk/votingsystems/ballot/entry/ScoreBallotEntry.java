package io.github.siemieniuk.votingsystems.ballot.entry;

public final class ScoreBallotEntry extends EntryWithNumber<CandidateEntry, Double> {

    public ScoreBallotEntry(CandidateEntry preference, Double score) {
        super(preference, score);
    }
}
