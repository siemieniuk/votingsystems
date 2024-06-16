package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.ScoreBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

public interface ScoreBallotAcceptable
        extends VotingSystemStrategy<ScoreBallot> {

    @Override
    void fit(List<ScoreBallot> ballots, Set<CandidateEntry<?, ?>> allCandidates);
}