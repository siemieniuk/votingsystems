package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.Ballot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;
import java.util.Set;

/**
 * Classes implementing this interface are able to count votes.
 * @param <T> Type of ballot
 */
public interface VotingSystemStrategy<T extends Ballot<?>> {
    void fit(List<T> ballots, Set<CandidateEntry<?, ?>> allCandidates);
    List<CandidateEntry<?, ?>> getWinners();
}
