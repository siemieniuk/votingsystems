package io.github.siemieniuk.votingsystems.ballot.entry;

import java.io.Serializable;
import java.util.Objects;

public record CandidateEntry(Serializable partyBlock, Serializable candidate) implements Serializable {
    @Override
    public String toString() {
        return "CandidateEntry{party=" + partyBlock + ", candidate=" + candidate + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateEntry that = (CandidateEntry) o;
        return Objects.equals(candidate, that.candidate) && Objects.equals(partyBlock, that.partyBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partyBlock, candidate);
    }
}
