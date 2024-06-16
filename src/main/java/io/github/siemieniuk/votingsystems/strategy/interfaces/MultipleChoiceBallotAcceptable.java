package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.MultipleChoiceBallot;

import java.util.List;

public interface MultipleChoiceBallotAcceptable<T>
    extends VotingSystemStrategy<T, MultipleChoiceBallot<T>> {

    List<T> countVotes(List<MultipleChoiceBallot<T>> ballots);
}
