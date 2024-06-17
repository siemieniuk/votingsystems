package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;

import java.util.List;

public interface VotingSystemAcceptable {
    List<CandidateEntry> getWinners();
}
