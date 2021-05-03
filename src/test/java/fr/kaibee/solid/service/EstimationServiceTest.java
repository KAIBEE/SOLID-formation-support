package fr.kaibee.solid.service;

import fr.kaibee.solid.repository.DistanceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


public class EstimationServiceTest {

    private DistanceRepository distanceRepository;
    private EstimationService estimationService;

    @Before
    public void setUp() {
        distanceRepository = Mockito.mock(DistanceRepository.class);
        estimationService = getEstimationService(distanceRepository);
    }

    @Test
    public void should_get_zero_estimation_when_no_distance() {
        // Given
        Long familyId = 1L;
        when(distanceRepository.getDistancesOfLastYears(familyId)).thenReturn(createDistances(0));

        // When
        int estimation = estimationService.getCarInsuranceCostEstimation(familyId);

        // Then
        assertThat(estimation).isEqualTo(0);
    }

    @Test
    public void should_get_right_estimation_when_linear_distance() {
        // Given
        Long familyId = 1L;
        int monthlyDistance = 1;
        when(distanceRepository.getDistancesOfLastYears(familyId)).thenReturn(createDistances(monthlyDistance));

        // When
        int estimation = estimationService.getCarInsuranceCostEstimation(familyId);

        // Then
        assertThat(estimation).isEqualTo(monthlyDistance * 12);
    }


    private Map<Month, Integer> createDistances(int monthlyDistance) {
        EnumMap<Month, Integer> result = new EnumMap<>(Month.class);
        for (Month month : Month.values()) {
            result.put(month, monthlyDistance);
        }
        return result;
    }

    private EstimationService getEstimationService(DistanceRepository distanceRepository) {
        return new EstimationService(distanceRepository);
    }

}
