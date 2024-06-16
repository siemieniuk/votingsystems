package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.CumulativeBallot;

import java.util.List;

public interface CumulativeBallotAcceptable<T>
        extends VotingSystemStrategy<T, CumulativeBallot<T>> {

    List<T> countVotes(List<CumulativeBallot<T>> ballots);
}
