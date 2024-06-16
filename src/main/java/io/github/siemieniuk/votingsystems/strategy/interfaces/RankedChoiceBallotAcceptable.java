package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public interface RankedChoiceBallotAcceptable
        extends VotingSystemStrategy<RankedChoiceBallot> {

    @Override
    void fit(List<RankedChoiceBallot> ballots, Set<CandidateEntry<?, ?>> allCandidates);
}
