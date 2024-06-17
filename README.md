# Voting Systems

This project implements various voting systems using Java 21.

**WIP - Work In Progress**

## Available rules

- [X] Anti-Plurality
- [X] First-Past-The-Post (FPTP)
- [ ] Two Round System (TRS)
- [ ] Borda Count
- [ ] Condorcet
- [ ] Bucklin
- [ ] Approval Voting
- [ ] Single Non-Transferable Vote
- [ ] Instant Runoff
- [ ] Copeland
- [ ] Kemeny
- [ ] Minimax
- [ ] Coombs
- [ ] Baldwin
- [ ] D'Hondt
- [ ] Sainte-Lague
- [ ] Hamilton
- [ ] Hare-Clark

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

| Party | Votes |
|-------|-------|
| A     | 5     |
| B     | 3     |
| C     | 4     |

As we can see, the winner is of course the candidate A with 5 votes.

Let's model this using the libraries from this repository:

```java
import io.github.siemieniuk.votingsystems.ballot.SingleChoiceBallot;
import io.github.siemieniuk.votingsystems.ballot.entry.CandidateEntry;
import io.github.siemieniuk.votingsystems.ballot.group.SingleChoiceBallotDataset;
import io.github.siemieniuk.votingsystems.strategy.FirstPastThePost;
import io.github.siemieniuk.votingsystems.strategy.interfaces.SingleChoiceBallotAcceptable;

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
        // In this example, the winner is CandidateEntry(1, "A")
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