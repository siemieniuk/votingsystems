package io.github.siemieniuk.votingsystems.ballot;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.Set;

/**
 * An abstract ballot
 * @param <T> A type of the preference
 */
public abstract class Ballot<T> {
    private T preferences;

    public Ballot(T preferences) {
        validate(preferences);
        this.preferences = preferences;
    }

    protected abstract void validate(T preferences);

    public abstract boolean isValidTo(Set<CandidateEntry> setOfAllCandidates);

    public final T getPreferences() {
        return preferences;
    }

    public void updateBallot(T newPreferences) {
        validate(newPreferences);
        this.preferences = newPreferences;
    }
}