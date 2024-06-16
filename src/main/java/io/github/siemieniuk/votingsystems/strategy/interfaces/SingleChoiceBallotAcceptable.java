package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public interface SingleChoiceBallotAcceptable
        extends VotingSystemStrategy<SingleChoiceBallot> {

    @Override
    void fit(List<SingleChoiceBallot> ballots, Set<CandidateEntry<?, ?>> allCandidates);
}