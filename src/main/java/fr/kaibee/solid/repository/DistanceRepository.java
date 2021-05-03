package fr.kaibee.solid.repository;

import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

public class DistanceRepository {

    public static final Integer MONTHLY_DISTANCE = 1;

    public Map<Month, Integer> getDistancesOfLastYears(Long familyId) {
        return createFakeDistances();
    }

    private Map<Month, Integer> createFakeDistances() {
        EnumMap<Month, Integer> result = new EnumMap<>(Month.class);
        for (Month month : Month.values()) {
            result.put(month, MONTHLY_DISTANCE);
        }
        return result;
    }
}
