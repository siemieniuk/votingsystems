package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * An abstract ballot
 * @param <T> A type of the preference
 */
public abstract class Ballot<T extends Serializable> implements Serializable {
    private T preferences;

    public Ballot(T preferences) {
        validate(preferences);
        this.preferences = preferences;
    }

    /**
     * Creates a new instance based on a deep copy of other ballot
     * @param other A ballot to be deeply copied.
     */
    public Ballot(Ballot<T> other) {
        this.preferences = SerializationUtils.clone(other.preferences);
    }

    protected abstract void validate(T preferences);

    public abstract boolean isValidTo(Set<CandidateEntry> setOfAllCandidates);

    public final T getPreferences() {
        return preferences;
    }

    public final void updateBallot(T newPreferences) {
        validate(newPreferences);
        this.preferences = newPreferences;
    }
}