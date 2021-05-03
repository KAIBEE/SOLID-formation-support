package fr.kaibee.solid.service;

import fr.kaibee.solid.repository.DistanceRepository;
import org.assertj.core.util.Maps;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class EstimationServiceTest {

    private DistanceRepository distanceRepository;
    private EstimationService estimationService;
    private SimilarHomeService similarHomeService;

    @Before
    public void setUp() {
        distanceRepository = mock(DistanceRepository.class);
        similarHomeService = mock(SimilarHomeService.class);
        estimationService = getEstimationService(distanceRepository, similarHomeService);
    }

    @Test
    public void should_get_zero_insurance_estimation_cost_when_no_distance() {
        // Given
        Long familyId = 1L;
        when(distanceRepository.getDistancesOfLastYears(familyId)).thenReturn(createDistances(0));

        // When
        int estimation = estimationService.getCarInsuranceCostEstimation(familyId);

        // Then
        assertThat(estimation).isEqualTo(0);
    }

    @Test
    public void should_get_right_insurance_estimation_cost_when_linear_distance() {
        // Given
        Long familyId = 1L;
        int monthlyDistance = 1;
        when(distanceRepository.getDistancesOfLastYears(familyId)).thenReturn(createDistances(monthlyDistance));

        // When
        int estimation = estimationService.getCarInsuranceCostEstimation(familyId);

        // Then
        assertThat(estimation).isEqualTo(monthlyDistance * 12);
    }

    @Test
    public void should_plug_distance_holes_with_similar_families() {
        // Given
        Long familyId = 1L;
        int monthlyDistance = 2;
        int similarFamilyFebruaryDistance = 4;
        long closestFamilyId = 2L;
        Map<Month, Integer> distances = getDistancesWithoutFebruary(monthlyDistance);
        Map<Month, Integer> similarDistances = Maps.newHashMap(Month.FEBRUARY, similarFamilyFebruaryDistance);

        when(distanceRepository.getDistancesOfLastYears(familyId)).thenReturn(distances);
        when(similarHomeService.getClosestFamily(familyId)).thenReturn(closestFamilyId);
        when(distanceRepository.getDistancesOfLastYears(closestFamilyId)).thenReturn(similarDistances);

        // When
        int estimation = estimationService.getCarInsuranceCostEstimation(familyId);

        // Then
        assertThat(estimation).isEqualTo(monthlyDistance * 11 + similarFamilyFebruaryDistance);
    }

    private Map<Month, Integer> getDistancesWithoutFebruary(int monthlyDistance) {
        Map<Month, Integer> distances = createDistances(monthlyDistance);
        distances.remove(Month.FEBRUARY);
        return distances;
    }

    private Map<Month, Integer> createDistances(int monthlyDistance) {
        EnumMap<Month, Integer> result = new EnumMap<>(Month.class);
        for (Month month : Month.values()) {
            result.put(month, monthlyDistance);
        }
        return result;
    }

    private EstimationService getEstimationService(DistanceRepository distanceRepository, SimilarHomeService similarHomesService) {
        return new EstimationService(distanceRepository, similarHomesService);
    }

}
