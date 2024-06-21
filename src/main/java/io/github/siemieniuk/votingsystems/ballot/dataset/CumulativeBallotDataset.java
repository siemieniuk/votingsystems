package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.CumulativeBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.CumulativeBallotEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.EntryWithNumber;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CumulativeBallotDataset
        extends BallotDataset<CumulativeBallot> {

    public CumulativeBallotDataset() {
        super();
    }

    public CumulativeBallotDataset(List<CumulativeBallot> ballots, Set<CandidateEntry> candidates) {
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
        for (CumulativeBallot ballot : getBallots()) {
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