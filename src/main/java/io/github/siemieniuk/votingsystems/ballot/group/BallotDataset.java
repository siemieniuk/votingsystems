package io.github.siemieniuk.votingsystems.ballot.group;

import io.github.siemieniuk.votingsystems.ballot.Ballot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents an abstract ballot group;
 * the parameter of all algorithms defined in io.github.siemieniuk.votingsystems.strategy.
 * @param <T> Type of ballot
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BallotDataset<T extends Ballot<?>> {

    private List<T> ballots = new ArrayList<>();
    private Set<CandidateEntry> candidates = new HashSet<>();

    /**
     * Adds both ballot and a new candidate(s) defined in the preferences.
     * @param ballot A ballot to be inserted.
     */
    public void add(T ballot) {
        ballots.add(ballot);
        addCandidate(ballot);
    }

    /**
     * Adds both a list of ballots and new candidates defined in all preferences of ballots.
     * @param ballots A list of ballots.
     */
    public void add(List<T> ballots) {
        for (T ballot : ballots) {
            add(ballot);
        }
    }

    /**
     * Adds a single candidate (NOT ballot) to the group.
     * @param party A party of any type.
     * @param candidate A candidate of any type.
     */
    public void addCandidate(Object party, Object candidate) {
        candidates.add(new CandidateEntry(party, candidate));
    }

    /**
     * Adds all missing candidates based on ballot.
     * @param ballot A ballot of type T
     */
    protected abstract void addCandidate(T ballot);

    /**
     * Updates the whole ballot list and adds new candidates defined in all preferences of ballots.<br>
     * <b>WARNING!</b> List of candidates is NOT truncated before updating!
     * @param ballots A list of ballots.
     */
    public void setBallots(List<T> ballots) {
        this.ballots = ballots;
        for (T ballot : ballots) {
            addCandidate(ballot);
        }
    }
}
