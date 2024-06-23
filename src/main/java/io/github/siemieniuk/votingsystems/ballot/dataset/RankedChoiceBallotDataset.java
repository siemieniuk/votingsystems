package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RankedChoiceBallotDataset
        extends BallotDataset<RankedChoiceBallot> {

    public RankedChoiceBallotDataset() {
        super();
    }

    /**
     * Constructs new ballot dataset using list of ballos and set of candidates. <br>
     * <b>WARNING:</b> by using this method make sure each ballot is as a separate pointer unless you do not use
     * method which requires <i>updateBallot()</i> method. Make also sure that set of candidates is consistent with ballots.
     * @param ballots A list of ballots.
     * @param candidates A list of candidates.
     */
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
