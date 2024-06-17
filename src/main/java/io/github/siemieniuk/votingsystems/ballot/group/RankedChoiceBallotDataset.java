package io.github.siemieniuk.votingsystems.ballot.group;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class RankedChoiceBallotDataset
        extends BallotDataset<RankedChoiceBallot> {

    public RankedChoiceBallotDataset() {
        super();
    }

    public RankedChoiceBallotDataset(List<RankedChoiceBallot> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(RankedChoiceBallot ballot) {
        getCandidates().addAll(ballot.getPreferences().values());
    }
}
