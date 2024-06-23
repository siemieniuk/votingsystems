package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.HashMap;
import java.util.Set;

public class SingleChoiceBallotDataset
        extends BallotDataset<SingleChoiceBallot> {

    public SingleChoiceBallotDataset() {
        super();
    }

    public SingleChoiceBallotDataset(HashMap<SingleChoiceBallot, Integer> ballots) {
        super(ballots);
    }

    /**
     * Constructs new ballot dataset using list of ballots and set of candidates. <br>
     * <b>WARNING:</b> by using this method make sure each ballot is as a separate pointer unless you do not use
     * method which requires <i>updateBallot()</i> method.
     * Make also sure that set of candidates is consistent with ballots.
     * @param ballots A hashmap of ballots (first parameter indicates ballot,
     *                second parameter indicates a number of votes).
     * @param candidates A list of candidates.
     */
    public SingleChoiceBallotDataset(HashMap<SingleChoiceBallot, Integer> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(SingleChoiceBallot ballot) {
        getCandidates().add(ballot.getPreferences());
    }

    @Override
    public boolean isConsistent() {
        for (SingleChoiceBallot ballot : getBallots().keySet()) {
            if (!getCandidates().contains(ballot.getPreferences())) {
                return false;
            }
        }
        return true;
    }
}
