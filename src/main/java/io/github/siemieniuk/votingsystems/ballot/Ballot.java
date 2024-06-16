package io.github.siemieniuk.votingsystems.ballot;

/**
 * An abstract ballot
 * @param <T> A type of the preference
 */
public abstract class Ballot<T> implements Verifiable {
    private T preferences;

    public Ballot(T preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public final T getPreferences() {
        return preferences;
    }

    public void updateBallot(T newPreferences) {
        this.preferences = newPreferences;
    }
}