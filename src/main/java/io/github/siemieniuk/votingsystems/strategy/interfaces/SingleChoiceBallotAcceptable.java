package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;

import java.util.List;

public interface SingleChoiceBallotAcceptable<T>
        extends VotingSystemStrategy<T, SingleChoiceBallot<T>> {

    List<T> countVotes(List<SingleChoiceBallot<T>> ballots);
}