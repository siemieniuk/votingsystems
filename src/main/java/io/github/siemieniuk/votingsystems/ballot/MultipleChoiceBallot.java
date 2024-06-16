package io.github.siemieniuk.votingsystems.ballot;

import java.util.List;

public class MultipleChoiceBallot<T> extends Ballot<List<T>> {

    public MultipleChoiceBallot(List<T> preferences) {
        super(preferences);
    }

    @Override
    public boolean isValid() {
        return !getPreferences().isEmpty();
    }

    @Override
    public String toString() {
        return "MultipleChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
