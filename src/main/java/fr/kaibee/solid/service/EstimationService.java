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

        Map<Month, Integer> drivenDistancesOfLastYear = distanceRepository.getDistancesOfLastYears(familyId);
        Map<Month, Integer> estimatedDrivenDistancesOfLastYear = drivenDistancesOfLastYear;

        if (hasHoles(drivenDistancesOfLastYear)) { // Should it be here ??
            estimatedDrivenDistancesOfLastYear = completeDrivenDistanceHoleWithSimilarHome(familyId, drivenDistancesOfLastYear);
        }

        return computeEstimationCostFrom(estimatedDrivenDistancesOfLastYear); // Does it belong to that class ?
    }

    private Map<Month, Integer> completeDrivenDistanceHoleWithSimilarHome(Long familyId, Map<Month, Integer> distancesOfLastYears) {

        Set<Month> monthsWithHole = getMonthsHoles(distancesOfLastYears);

        Long similarFamilyId = similarHomesService.getClosestFamily(familyId);

        // I'm lazy so I use the already implemented getDistancesOfLastYears method
        Map<Month, Integer> similarDistancesOfLastYears = distanceRepository.getDistancesOfLastYears(similarFamilyId);

        Map<Month, Integer> drivenDistanceOfLastYear = new EnumMap<>(distancesOfLastYears);
        monthsWithHole.forEach(month -> drivenDistanceOfLastYear.put(month, similarDistancesOfLastYears.get(month)));
        for (Month monthWithHole : monthsWithHole) {
            drivenDistanceOfLastYear.put(monthWithHole, similarDistancesOfLastYears.get(monthWithHole));
        }
        return drivenDistanceOfLastYear;
    }

    private Set<Month> getMonthsHoles(Map<Month, Integer> distancesOfLastYears) {
        Set<Month> MonthHoles = Arrays.stream(Month.values())
                .filter(month -> distancesOfLastYears.get(month) == null)
                .collect(Collectors.toSet());
        return MonthHoles;
    }

    private int computeEstimationCostFrom(Map<Month, Integer> result) {
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

