package io.github.siemieniuk.votingsystems.ballot.dataset;

import io.github.siemieniuk.votingsystems.ballot.Ballot;
import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;

/**
 * Represents an abstract ballot group;
 * the parameter of all algorithms defined in io.github.siemieniuk.votingsystems.strategy.
 * @param <T> Type of ballot
 */
@Getter
public abstract class BallotDataset<T extends Ballot<?>>
        implements Iterable<Map.Entry<T, Integer>> {

    private HashMap<T, Integer> ballots;
    private final Set<CandidateEntry> candidates;
    private int totalVotes;

    public BallotDataset() {
        this.ballots = new HashMap<>();
        this.candidates = new HashSet<>();
        totalVotes = 0;
    }

    /**
     * Constructs new ballot dataset using list of ballots and set of candidates. <br>
     * <b>WARNING:</b> by using this method make sure each ballot is as a separate pointer unless you do not use
     * method which requires <i>updateBallot()</i> method.
     * Make also sure that set of candidates is consistent with ballots.
     * @param ballots A HashMap of ballots (first parameter indicates ballot,
     *                second parameter indicates a number of votes).
     * @param candidates A list of candidates.
     */
    public BallotDataset(HashMap<T, Integer> ballots, Set<CandidateEntry> candidates) {
        this.ballots = ballots;
        this.candidates = candidates;
        totalVotes = ballots.size();
    }

    public BallotDataset(HashMap<T, Integer> ballots) {
        this.ballots = ballots;
        this.candidates = new HashSet<>();

        T firstBallot = ballots.keySet().iterator().next();

        if (firstBallot instanceof SingleChoiceBallot) {
            for (T ballot : ballots.keySet()) {
                SingleChoiceBallot scballot = (SingleChoiceBallot) ballot;
                candidates.add(scballot.getPreferences());
            }
        } else {
            throw new IllegalArgumentException("This constructor requires HashMap<SingleChoiceBallot, Integer>");
        }
    }

    /**
     * Adds both ballot and a new candidate(s) defined in the preferences.
     * @param ballot A ballot to be inserted.
     */
    public void add(T ballot) {
        add(ballot, 1);
    }

    /**
     * Adds both ballot and a new candidate(s) defined in the preference. <br>
     * Repeats this procedure <em>repeats</em> times.
     * @param ballot A ballot to be inserted.
     * @param repeats A number of repetitions of inserts.
     */
    public void add(T ballot, int repeats) {
        addCandidate(ballot);
        int previousCount = ballots.getOrDefault(ballot, 0);
        ballots.put(ballot, previousCount + repeats);
        totalVotes += repeats;
    }

    /**
     * Adds both a list of ballots and new candidates defined in all preferences of ballots.
     * @param ballots A list of ballots.
     */
    public void add(List<T> ballots) {
        for (T ballot : ballots) {
            add(ballot, 1);
        }
    }

    /**
     * Adds a single candidate (NOT ballot) to the group.
     * @param party A party of type Serializable.
     * @param candidate A candidate of type Serializable.
     */
    public void addCandidate(Serializable party, Serializable candidate) {
        candidates.add(new CandidateEntry(party, candidate));
    }

    /**
     * Adds all missing candidates based on ballot.
     * @param ballot A ballot of type T
     */
    protected abstract void addCandidate(T ballot);

    /**
     * Removes all ballots from the dataset.
     */
    public void clearBallots() {
        this.ballots = new HashMap<>();
        this.totalVotes = 0;
    }

    /**
     * Checks if two datasets contain exactly the same candidates.
     * @param other A dataset to compare to
     * @return <code>true</code> if two datasets contain exactly the same candidates, <code>false</code> otherwise.
     */
    public boolean hasSameCandidatesAs(BallotDataset<?> other) {
        if (this.candidates.size() != other.candidates.size()) {
            return false;
        }
        return this.candidates.containsAll(other.candidates);
    }

    /**
     * Updates the whole ballot list and adds new candidates defined in all preferences of ballots.<br>
     * <b>WARNING!</b> List of candidates is NOT truncated before updating!
     * @param ballots A list of ballots.
     */
    @Deprecated
    public void setBallots(List<T> ballots) {
        this.ballots = ballots;
        for (T ballot : ballots) {
            addCandidate(ballot);
        }
    }

    /**
     * Iterates over ballots in the dataset.
     * @return An iterator for ballots
     */
    @Override
    public Iterator<Map.Entry<T, Integer>> iterator() {
        return ballots.entrySet().iterator();
    }

    /**
     * Checks consistency between ballots and candidates
     * @return <code>true</code> if consistent, <code>false</code> otherwise
     */
    public abstract boolean isConsistent();
}
