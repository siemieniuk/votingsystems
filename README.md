# Voting Systems

[![Publish package to the Maven Central Repository and GitHub Packages](https://github.com/siemieniuk/voting-systems/actions/workflows/publish.yml/badge.svg)](https://github.com/siemieniuk/voting-systems/actions/workflows/publish.yml)

**DOCUMENTATION:**

[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://siemieniuk.github.io/votingsystems/javadoc/)

This project implements various voting systems using Java 21.

**WIP - Work In Progress**

## Available rules

- [X] Anti-Plurality
- [X] Borda Count
- [X] First-Past-The-Post (FPTP)
- [X] Highest averages methods
  - [X] Adams method
  - [X] D'Hondt method (Jefferson method)
  - [X] Huntington-Hill method
  - [X] Sainte-Lague method (Webster method)
  - [X] User-defined method (thanks to abstract class `HighestAveragesStrategy`)
- [ ] Two Round System (TRS)
- [ ] Condorcet
- [ ] Bucklin
- [ ] Approval Voting
- [ ] Single Non-Transferable Vote (SNTV)
- [ ] Single Transferable Vote (STV)
- [ ] Instant Runoff
- [ ] Copeland
- [ ] Kemeny
- [ ] Minimax
- [ ] Coombs
- [ ] Baldwin
- [ ] Hare-Clark
- [ ] Largest remainders methods
  - [ ] Hare quota (LR-Hare; Hamilton method)
  - [ ] Droop quota (LR-Droop)
  - [ ] Any other remainder

## Features

- [X] ballots
- [X] datasets
- [X] thresholds for some methods

## TODO
- [ ] More available rules
- [ ] More operations on datasets
- [ ] Easier construction of datasets

## Example Usage

Let's suppose we have a voting where the election method is FPTP
([wikipedia](https://en.wikipedia.org/wiki/First-past-the-post_voting)). 

There is one party named "Foo" and three candidates: "A", "B", "C".

Here is a table of all votes. Because FPTP is a single-choice method,
let's write the aggregated results by party.

| Candidate | Votes |
|-----------|-------|
| A         | 5     |
| B         | 3     |
| C         | 4     |

As we can see, the winner is of course the candidate A with 5 votes.

Let's model this using the libraries from this repository:

```java
import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.dataset.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.strategy.FirstPastThePost;

class AnyClass {
  public static void main(String[] args) {
    // Create a dataset which stores ballots and candidates.
    SingleChoiceBallotDataset dataset = new SingleChoiceBallotDataset();

    // Create options which should be on ballots using CandidateEntry object.
    CandidateEntry[] candidateEntries = new CandidateEntry[]{
            new CandidateEntry("Foo", "A"),
            new CandidateEntry("Foo", "B"),
            new CandidateEntry("Foo", "C")
    };

    int[] howManyVotes = new int[]{5, 3, 4};
    for (int i = 0; i < howManyVotes.length; i++) {
      for (int j = 0; j < howManyVotes[i]; j++) {
        // Create a filled Single Choice Ballot passing a CandidateEntry instance.
        SingleChoiceBallot ballot = new SingleChoiceBallot(candidateEntries[i]);

        // Add ballot to the dataset
        dataset.add(ballot);
      }
    }

    // Create an object with the strategy (voting system)
    FirstPastThePost strategy = new FirstPastThePost();

    // Load data using .fit()
    strategy.fit(dataset);

    // Retrieve winners (always as List<CandidateEntry>).
    // In this example, the winner is CandidateEntry("Foo", "A")
    List<CandidateEntry> winners = strategy.getWinners();
  }
}
```

## Where Can I Use This Library?

Anywhere where Social Choice Theory is applicable, i.e.:
- your own online electional system
- your own module using ready components from Voting Systems
- evaluating your Social Choice Theory experiments

## Unit Tests

All unit tests are available in the `src/test` folder.
