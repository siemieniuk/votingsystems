package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.MultipleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.Hashtable;
import java.util.Set;

public class MultipleChoiceBallotDataset
        extends BallotDataset<MultipleChoiceBallot> {

    public MultipleChoiceBallotDataset() {
        super();
    }

    /**
     * Constructs new ballot dataset using list of ballots and set of candidates. <br>
     * <b>WARNING:</b> by using this method make sure each ballot is as a separate pointer unless you do not use
     * method which requires <i>updateBallot()</i> method.
     * Make also sure that set of candidates is consistent with ballots.
     * @param ballots A hashtable of ballots (first parameter indicates ballot,
     *                second parameter indicates a number of votes).
     * @param candidates A list of candidates.
     */
    public MultipleChoiceBallotDataset(Hashtable<MultipleChoiceBallot, Integer> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(MultipleChoiceBallot ballot) {
        Set<CandidateEntry> candidates = getCandidates();
        candidates.addAll(ballot.getPreferences());
    }

    @Override
    public boolean isConsistent() {
        for (MultipleChoiceBallot ballot : getBallots().keySet()) {
            if (!getCandidates().containsAll(ballot.getPreferences())) {
                return false;
            }
        }
        return true;
    }
}
