package fr.kaibee.solid.service;

import java.util.List;

public interface TariffByDistanceCalculator {
    int computeEstimationCostFrom(List<Distance> distances);
}
