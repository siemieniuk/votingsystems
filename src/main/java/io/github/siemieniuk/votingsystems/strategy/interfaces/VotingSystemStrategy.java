package io.github.siemieniuk.votingsystems.strategy.interfaces;

import java.util.List;

/**
 * Classes implementing this interface are able to count votes.
 * @param <T> Type of return type
 * @param <S> Type of ballot
 */
public interface VotingSystemStrategy<T, S> {
    List<T> countVotes(List<S> ballots);
}
