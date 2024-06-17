package io.github.siemieniuk.votingsystems.ballot.group;

import io.github.siemieniuk.votingsystems.ballot.ScoreBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.entry.ScoreBallotEntry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ScoreBallotDataset
        extends BallotDataset<ScoreBallot> {

    public ScoreBallotDataset() {
        super();
    }

    public ScoreBallotDataset(List<ScoreBallot> ballots, Set<CandidateEntry> candidates) {
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
}
