package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Represents a ranked choice ballot dataset;
 * A structure which stores ranked choice ballots.
 */
public final class RankedChoiceBallotDataset
        extends BallotDataset<RankedChoiceBallot> {

    /**
     * Constructs an empty dataset
     */
    public RankedChoiceBallotDataset() {
        super();
    }

    /**
     * Constructs new ballot dataset using list of ballots and set of candidates. <br>
     * <b>WARNING:</b> by using this method make sure each ballot is as a separate pointer unless you do not use
     * method which requires <i>updateBallot()</i> method.
     * Make also sure that set of candidates is consistent with ballots.
     * @param ballots A HashMap of ballots (first parameter indicates ballot,
     *                second parameter indicates a number of votes).
     * @param candidates A list of candidates.
     */
    public RankedChoiceBallotDataset(HashMap<RankedChoiceBallot, Integer> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(RankedChoiceBallot ballot) {
        getCandidates().addAll(ballot.getPreferences().values());
    }

    @Override
    public boolean isConsistent() {
        for (RankedChoiceBallot ballot : getBallots().keySet()) {
            if (!getCandidates().containsAll(ballot.getPreferences().values())) {
                return false;
            }
        }
        return true;
    }

    public boolean containsTotalRankings() {
        List<RankedChoiceBallot> distinctBallots = getBallots().keySet().stream().toList();
        RankedChoiceBallot firstBallot = distinctBallots.getFirst();

        Set<CandidateEntry> firstBallotEntries = new HashSet<>(firstBallot.getPreferences().values());
        Set<Integer> firstBallotKeys = firstBallot.getPreferences().keySet();

        for (RankedChoiceBallot ballot : distinctBallots.subList(1, distinctBallots.size())) {
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
        RankedChoiceBallot firstBallot = getBallots().keySet().stream().toList().getFirst();
        Set<Integer> keys = firstBallot.getPreferences().keySet();
        return keys.stream()
                .max(Integer::compareTo)
                .get();
    }
}
