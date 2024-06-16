package io.github.siemieniuk.votingsystems.ballot;

public class SingleChoiceBallot<T> extends Ballot<T> {

    public SingleChoiceBallot(T preferences) {
        super(preferences);
    }

    @Override
    public String toString() {
        return "SingleChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
