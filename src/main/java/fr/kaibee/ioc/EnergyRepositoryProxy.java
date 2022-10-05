package fr.kaibee.ioc;

import java.time.YearMonth;
import java.util.List;

public class EnergyRepositoryProxy implements EnergyRepository {
    public static final YearMonth CHANGING_PERIOD = YearMonth.of(2020, 3);

    private EnergyRepository legacyEnergyRepository = new LegacyEnergyRepository();
    private EnergyRepository energyRepositoryV2 = new EnergyRepositoryV2();

    @Override
    public List<Consumption> getConsumptions(long idClient, EnergyType energyType, YearMonth yearMonth) {
        if (yearMonth.isBefore(CHANGING_PERIOD)) {
            return legacyEnergyRepository.getConsumptions(idClient, energyType, yearMonth);
        }
        return energyRepositoryV2.getConsumptions(idClient, energyType, yearMonth);
    }
}
