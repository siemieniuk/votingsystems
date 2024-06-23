package io.github.siemieniuk.votingsystems.threshold;

import lombok.Getter;

@Getter
public class PartyWithThreshold {
    private final Object party;
    private final Double threshold;

    public PartyWithThreshold(Object party, Double threshold) {
        this.party = party;
        this.threshold = threshold;
    }

    private PartyWithThreshold() {
        this.party = null;
        this.threshold = null;
    }
}
