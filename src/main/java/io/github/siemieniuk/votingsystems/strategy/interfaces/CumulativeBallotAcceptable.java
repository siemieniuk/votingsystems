package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.CumulativeBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public interface CumulativeBallotAcceptable
        extends VotingSystemStrategy<CumulativeBallot> {

    @Override
    void fit(List<CumulativeBallot> ballots, Set<CandidateEntry<?, ?>> allCandidates);
}
