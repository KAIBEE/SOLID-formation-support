package fr.kaibee.solid.service;

import fr.kaibee.solid.repository.DistanceRepository;

import java.time.Month;
import java.util.Map;

public class EstimationService {
    private final DistanceRepository distanceRepository;

    public EstimationService(DistanceRepository distanceRepository) {
        this.distanceRepository = distanceRepository;
    }

    public int getCarInsuranceCostEstimation(Long familyId) {
        Map<Month, Integer> distancesOfLastYears = distanceRepository.getDistancesOfLastYears(familyId);

        return distancesOfLastYears.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
