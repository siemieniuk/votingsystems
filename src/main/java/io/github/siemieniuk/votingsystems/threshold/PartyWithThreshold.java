package io.github.siemieniuk.votingsystems.threshold;

import java.io.Serializable;

public class PartyWithThreshold {
    private final Serializable party;
    private final Double threshold;

    public PartyWithThreshold(Serializable party, Double threshold) {
        this.party = party;
        this.threshold = threshold;
    }

    private PartyWithThreshold() {
        this.party = null;
        this.threshold = null;
    }

    public Serializable getParty() {
        return party;
    }

    public Double getThreshold() {
        return threshold;
    }
}
