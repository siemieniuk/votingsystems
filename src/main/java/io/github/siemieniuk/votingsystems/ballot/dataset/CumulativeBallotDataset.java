package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.CumulativeBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.CumulativeBallotEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.EntryWithNumber;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CumulativeBallotDataset
        extends BallotDataset<CumulativeBallot> {

    public CumulativeBallotDataset() {
        super();
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
    public CumulativeBallotDataset(HashMap<CumulativeBallot, Integer> ballots, Set<CandidateEntry> candidates) {
        super(ballots, candidates);
    }

    @Override
    protected void addCandidate(CumulativeBallot ballot) {
        Set<CandidateEntry> candidates = getCandidates();
        candidates.addAll(ballot
                .getPreferences()
                .stream()
                .map(CumulativeBallotEntry::getPreference)
                .toList());
    }

    @Override
    public boolean isConsistent() {
        for (CumulativeBallot ballot : getBallots().keySet()) {
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