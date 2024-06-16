package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.MultipleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public interface MultipleChoiceBallotAcceptable
    extends VotingSystemStrategy<MultipleChoiceBallot> {

    @Override
    void fit(List<MultipleChoiceBallot> ballots, Set<CandidateEntry<?, ?>> allCandidates);
}
