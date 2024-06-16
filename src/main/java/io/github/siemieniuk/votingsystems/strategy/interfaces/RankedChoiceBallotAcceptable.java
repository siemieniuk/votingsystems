package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.RankedChoiceBallot;

import java.util.List;

public interface RankedChoiceBallotAcceptable<T>
        extends VotingSystemStrategy<T, RankedChoiceBallot<T>> {

    List<T> countVotes(List<RankedChoiceBallot<T>> ballots);
}
