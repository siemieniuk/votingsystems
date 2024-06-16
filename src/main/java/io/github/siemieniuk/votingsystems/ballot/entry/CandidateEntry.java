package io.github.siemieniuk.votingsystems.ballot.entry;

import java.util.Objects;

/**
 * @param <T> Variable type representing a party
 * @param <S> Variable type representing a candidate
 */
public record CandidateEntry<T, S>(T partyBlock, S candidate) {
    @Override
    public String toString() {
        return "CandidateEntry{party=" + partyBlock + ", candidate=" + candidate + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateEntry<?, ?> that = (CandidateEntry<?, ?>) o;
        return Objects.equals(candidate, that.candidate) && Objects.equals(partyBlock, that.partyBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partyBlock, candidate);
    }
}
