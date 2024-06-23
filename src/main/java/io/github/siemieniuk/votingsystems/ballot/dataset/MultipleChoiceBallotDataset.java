package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.MultipleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public class MultipleChoiceBallotDataset
        extends BallotDataset<MultipleChoiceBallot> {

    public MultipleChoiceBallotDataset() {
        super();
    }

    /**
     * Constructs new ballot dataset using list of ballos and set of candidates. <br>
     * <b>WARNING:</b> by using this method make sure each ballot is as a separate pointer unless you do not use
     * method which requires <i>updateBallot()</i> method. Make also sure that set of candidates is consistent with ballots.
     * @param ballots A list of ballots.
     * @param candidates A list of candidates.
     */
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
