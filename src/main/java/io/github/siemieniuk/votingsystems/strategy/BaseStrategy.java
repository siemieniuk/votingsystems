package io.github.siemieniuk.votingsystems.strategy;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import lombok.Getter;

import java.util.List;

/**
 * An abstract class which all voting methods should inherit.
 */
@Getter
public abstract class BaseStrategy {
    private final int seats;

    public BaseStrategy(int seats) {
        this.seats = seats;
    }

    public abstract List<CandidateEntry> getWinners();
}
