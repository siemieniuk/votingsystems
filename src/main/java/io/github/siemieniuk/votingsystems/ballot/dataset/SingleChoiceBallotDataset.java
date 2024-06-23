package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public class SingleChoiceBallotDataset
        extends BallotDataset<SingleChoiceBallot> {

    public SingleChoiceBallotDataset() {
        super();
    }

    /**
     * Constructs new ballot dataset using list of ballos and set of candidates. <br>
     * <b>WARNING:</b> by using this method make sure each ballot is as a separate pointer unless you do not use
     * method which requires <i>updateBallot()</i> method. Make also sure that set of candidates is consistent with ballots.
     * @param ballots A list of ballots.
     * @param candidates A list of candidates.
     */
    public SingleChoiceBallotDataset(List<SingleChoiceBallot> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(SingleChoiceBallot ballot) {
        getCandidates().add(ballot.getPreferences());
    }

    @Override
    public boolean isConsistent() {
        for (SingleChoiceBallot ballot : getBallots()) {
            if (!getCandidates().contains(ballot.getPreferences())) {
                return false;
            }
        }
        return true;
    }
}
