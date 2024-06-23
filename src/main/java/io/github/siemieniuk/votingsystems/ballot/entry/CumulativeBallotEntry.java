package io.github.siemieniuk.votingsystems.ballot.entry;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public final class CumulativeBallotEntry extends EntryWithNumber<CandidateEntry, Double> {

    public CumulativeBallotEntry(CandidateEntry preference, double fraction) {
        super(preference, fraction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CumulativeBallotEntry that = (CumulativeBallotEntry) o;
        return Objects.equals(getPreference(), that.getPreference()) && Objects.equals(getScore(), that.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPreference(), getScore());
    }
}
