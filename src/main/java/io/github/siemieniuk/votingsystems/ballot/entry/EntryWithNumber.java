package io.github.siemieniuk.votingsystems.ballot.entry;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public abstract class EntryWithNumber<T extends Serializable, S extends Number> {

    private T preference;
    private S score;

    public EntryWithNumber(T preference, S score) {
        this.preference = preference;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryWithNumber<?, ?> that = (EntryWithNumber<?, ?>) o;
        return Objects.equals(preference, that.preference) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preference, score);
    }
}
