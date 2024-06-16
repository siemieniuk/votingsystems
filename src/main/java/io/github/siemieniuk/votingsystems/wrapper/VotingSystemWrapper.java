package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.Ballot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.strategy.interfaces.VotingSystemStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * An abstract wrapper containing Voting System Strategy and type of ballot; creating objects using this class is
 * discouraged.
 * @param <T> Type of ballot
 * @param <S> Type of voting system
 */
public abstract class VotingSystemWrapper<
        T extends Ballot<?>,
        S extends VotingSystemStrategy<T>> {

    @Setter
    private S strategy;

    @Getter
    private List<CandidateEntry<?, ?>> winners;

    public VotingSystemWrapper() {
        this.strategy = null;
    }

    public VotingSystemWrapper(S strategy) {
        this.strategy = strategy;
    }

    public void fit(List<T> ballots, Set<CandidateEntry<?, ?>> allCandidates) {
        assert strategy != null;

        strategy.fit(ballots, allCandidates);
        winners = strategy.getWinners();
    }
}
