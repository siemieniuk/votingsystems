package io.github.siemieniuk.votingsystems.threshold;

import io.github.siemieniuk.votingsystems.strategy.interfaces.ThresholdAcceptable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyThreshold {

    private final List<ThresholdAcceptable> votingSystems;
    private final List<PartyWithThreshold> partiesWithThreshold;
    private final Map<Object, Integer> votes = new HashMap<>();
    private final List<Object> partiesWithoutQuota = new ArrayList<>();
    private int totalVotes = 0;

    public PartyThreshold(List<ThresholdAcceptable> systems,
                          List<PartyWithThreshold> partiesWithThreshold) {

        this.votingSystems = systems;
        this.partiesWithThreshold = partiesWithThreshold;
    }

    public PartyThreshold() {
        this.votingSystems = new ArrayList<>();
        this.partiesWithThreshold = new ArrayList<>();
    }

    public void apply() {
        collectVotes();
        calculatePartiesWithoutQuota();
        tellSystemsWhichPartiesWithoutQuota();
    }

    private void collectVotes() {
        for (ThresholdAcceptable votingSystem : votingSystems) {
            Map<Object, Integer> vsVotes = votingSystem.collectVotesByParty();
            for (Map.Entry<Object, Integer> entry : vsVotes.entrySet()) {
                int previousValue = votes.getOrDefault(entry.getKey(), 0);
                votes.put(entry.getKey(), previousValue + entry.getValue());
            }
            totalVotes += votingSystem.getTotalVotes();
        }
    }

    private void tellSystemsWhichPartiesWithoutQuota() {
        for (ThresholdAcceptable votingSystem : votingSystems) {
            votingSystem.excludeParties(partiesWithoutQuota);
        }
    }

    private void calculatePartiesWithoutQuota() {
        for (PartyWithThreshold partyWithThreshold : partiesWithThreshold) {
            int threshold = (int) (partyWithThreshold.getThreshold() * totalVotes);
            Object party = partyWithThreshold.getParty();
            if (votes.get(party) < threshold) {
                partiesWithoutQuota.add(party);
            }
        }
    }
}
