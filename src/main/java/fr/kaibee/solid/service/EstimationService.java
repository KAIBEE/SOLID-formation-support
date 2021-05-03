package fr.kaibee.solid.service;

import fr.kaibee.solid.repository.DistanceRepository;

import java.time.Month;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EstimationService {
    private final DistanceRepository distanceRepository;
    private final SimilarHomeService similarHomesService;

    public EstimationService(DistanceRepository distanceRepository, SimilarHomeService similarHomesService) {
        this.distanceRepository = distanceRepository;
        this.similarHomesService = similarHomesService;
    }

    public int getCarInsuranceCostEstimation(Long familyId) {


        Map<Month, Integer> distancesOfLastYears = distanceRepository.getDistancesOfLastYears(familyId);
        EnumMap<Month, Integer> result = new EnumMap<>(distancesOfLastYears);

        if (hasHoles(distancesOfLastYears)) {
            Set<Month> MonthHoles = Arrays.stream(Month.values())
                    .filter(month -> distancesOfLastYears.get(month) == null)
                    .collect(Collectors.toSet());

            Long similarFamilyId = similarHomesService.getClosestFamily(familyId);

            // I'm lazy so I use the already implemented getDistancesOfLastYears method
            Map<Month, Integer> similarDistancesOfLastYears = distanceRepository.getDistancesOfLastYears(similarFamilyId);

            for (Month monthHole : MonthHoles) {
                result.put(monthHole, similarDistancesOfLastYears.get(monthHole));
            }
        }

        return result
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private boolean hasHoles(Map<Month, Integer> distancesOfLastYears) {
        return distancesOfLastYears.size() != 12;
    }
}
