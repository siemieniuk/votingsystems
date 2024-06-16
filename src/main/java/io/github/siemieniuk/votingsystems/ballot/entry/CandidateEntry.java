package io.github.siemieniuk.votingsystems.ballot.entry;

/**
 * @param <T> Variable type representing a party
 * @param <S> Variable type representing a candidate
 */
public record CandidateEntry<T, S>(T partyBlock, S candidate) {
    @Override
    public String toString() {
        return "CandidateEntry{" + ", party=" + partyBlock + "candidate=" + candidate + '}';
    }
}
