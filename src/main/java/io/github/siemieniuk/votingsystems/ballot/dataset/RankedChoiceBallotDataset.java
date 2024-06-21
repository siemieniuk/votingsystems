package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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

    @Override
    public boolean isConsistent() {
        for (RankedChoiceBallot ballot : getBallots()) {
            if (!getCandidates().containsAll(ballot.getPreferences().values())) {
                return false;
            }
        }
        return true;
    }

    public boolean containsTotalRankings() {
        Set<CandidateEntry> firstBallotEntries = new HashSet<>(getBallots().getFirst().getPreferences().values());
        Set<Integer> firstBallotKeys = new HashSet<>(getBallots().getFirst().getPreferences().keySet());

        for (RankedChoiceBallot ballot : getBallots().subList(1, getBallots().size())) {
            if (!ballot.getPreferences().keySet().equals(firstBallotKeys)) {
                return false;
            }
            if (!ballot.getPreferences().values().equals(firstBallotEntries)) {
                return false;
            }
        }
        return true;
    }

    public int getMaxRanking() {
        Set<Integer> keys = getBallots().getFirst().getPreferences().keySet();
        return keys.stream()
                .max(Integer::compareTo)
                .get();
    }
}
