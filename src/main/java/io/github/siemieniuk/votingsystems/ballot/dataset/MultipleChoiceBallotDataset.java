package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.MultipleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MultipleChoiceBallotDataset
        extends BallotDataset<MultipleChoiceBallot> {

    public MultipleChoiceBallotDataset() {
        super();
    }

    public MultipleChoiceBallotDataset(List<MultipleChoiceBallot> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(MultipleChoiceBallot ballot) {
        Set<CandidateEntry> candidates = getCandidates();
        candidates.addAll(ballot.getPreferences());
    }

    @Override
    public boolean isConsistent() {
        for (MultipleChoiceBallot ballot : getBallots()) {
            if (!getCandidates().containsAll(ballot.getPreferences())) {
                return false;
            }
        }
        return true;
    }
}
