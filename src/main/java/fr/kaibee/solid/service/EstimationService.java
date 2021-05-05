package fr.kaibee.solid.service;

import java.util.List;

public class EstimationService {
    private final DistanceComputer distanceComputer;
    private final TariffByDistanceCalculator tariffByDistanceCalculator;

    public EstimationService(DistanceComputer distanceComputer, TariffByDistanceCalculator tariffByDistanceCalculator) {
        this.distanceComputer = distanceComputer;
        this.tariffByDistanceCalculator = tariffByDistanceCalculator;
    }

    public int getCarInsuranceCostEstimation(Long familyId) {
        List<Distance> computedDrivenDistancesOfLastYear = distanceComputer.computeDistanceOfLastYear(familyId);
        return tariffByDistanceCalculator.computeEstimationCostFrom(computedDrivenDistancesOfLastYear);
    }
}

