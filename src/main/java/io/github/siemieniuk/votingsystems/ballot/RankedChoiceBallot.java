package io.github.siemieniuk.votingsystems.ballot;

import java.util.Map;

public class RankedChoiceBallot<T> extends Ballot<Map<Integer, T>> {

    public RankedChoiceBallot(Map<Integer, T> preferences) {
        super(preferences);
    }

    @Override
    public boolean isValid() {
        int minRanking = 1;
        int maxRanking = getPreferences().keySet().stream().max(Integer::compareTo).get();

        for (int i=minRanking; i<=maxRanking; i++) {
            if (!getPreferences().containsKey(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "RankedChoiceBallot<" + getPreferences().getClass().getCanonicalName() + ">";
    }
}
