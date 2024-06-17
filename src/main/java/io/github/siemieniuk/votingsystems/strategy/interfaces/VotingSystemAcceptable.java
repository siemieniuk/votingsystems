package io.github.siemieniuk.votingsystems.strategy.interfaces;

import io.github.siemieniuk.votingsystems.ballot.Ballot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.group.BallotDataset;

import java.util.List;

public interface VotingSystemAcceptable {
    List<CandidateEntry> getWinners();
}
