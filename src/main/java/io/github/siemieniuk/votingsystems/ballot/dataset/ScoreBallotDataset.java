package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.ScoreBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.EntryWithNumber;
import io.github.siemieniuk.votingsystems.ballot.entry.ScoreBallotEntry;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class ScoreBallotDataset
        extends BallotDataset<ScoreBallot> {

    public ScoreBallotDataset() {
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
    public ScoreBallotDataset(Hashtable<ScoreBallot, Integer> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(ScoreBallot ballot) {
        getCandidates().addAll(ballot
                .getPreferences()
                .stream()
                .map(ScoreBallotEntry::getPreference)
                .toList());
    }

    @Override
    public boolean isConsistent() {
        for (ScoreBallot ballot : getBallots().keySet()) {
            List<CandidateEntry> candidatesInBallot = ballot.getPreferences()
                    .stream()
                    .map(EntryWithNumber::getPreference)
                    .toList();
            if (!getCandidates().containsAll(candidatesInBallot)) {
                return false;
            }
        }
        return true;
    }
}
