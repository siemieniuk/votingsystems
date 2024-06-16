package io.github.siemieniuk.votingsystems.wrapper;

import io.github.siemieniuk.votingsystems.ballot.Ballot;
import io.github.siemieniuk.votingsystems.strategy.interfaces.VotingSystemStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class VotingSystemWrapper<T, S extends Ballot<?>, U extends VotingSystemStrategy<T, S>> {

    @Setter
    private U strategy;

    @Getter
    private List<T> winners;

    public VotingSystemWrapper() {
        this.strategy = null;
    }

    public VotingSystemWrapper(U strategy) {
        this.strategy = strategy;
    }

    public void fit(List<S> ballots) {
        assert strategy != null;
        winners = strategy.countVotes(ballots);
    }
}
