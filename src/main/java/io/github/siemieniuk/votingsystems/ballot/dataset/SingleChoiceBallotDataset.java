package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.HashMap;
import java.util.Set;

/**
 * Represents a single choice ballot dataset;
 * A structure which stores single choice ballots.
 */
public final class SingleChoiceBallotDataset
        extends BallotDataset<SingleChoiceBallot> {

    /**
     * Constructs an empty dataset
     */
    public SingleChoiceBallotDataset() {
        super();
    }

    /**
     * Constructs a single choice dataset based on hashmap
     * @param ballots A hashmap where keys are single choice ballots,
     * and values are number of votes for a particular ballot.
     */
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
