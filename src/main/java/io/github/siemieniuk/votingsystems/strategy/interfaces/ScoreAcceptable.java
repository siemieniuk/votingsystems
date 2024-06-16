package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.ScoreBallot;

import java.util.List;

public interface ScoreAcceptable<T>
        extends VotingSystemStrategy<T, ScoreBallot<T>> {

    List<T> countVotes(List<ScoreBallot<T>> ballots);
}