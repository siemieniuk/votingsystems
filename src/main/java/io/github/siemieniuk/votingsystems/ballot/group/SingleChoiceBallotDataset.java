package io.github.siemieniuk.votingsystems.ballot.group;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public class SingleChoiceBallotDataset
        extends BallotDataset<SingleChoiceBallot> {

    public SingleChoiceBallotDataset() {
        super();
    }

    public SingleChoiceBallotDataset(List<SingleChoiceBallot> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(SingleChoiceBallot ballot) {
        getCandidates().add(ballot.getPreferences());
    }
}
