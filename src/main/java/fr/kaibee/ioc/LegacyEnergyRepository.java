package fr.kaibee.ioc;

import java.time.YearMonth;
import java.util.List;

public class LegacyEnergyRepository implements EnergyRepository {
    @Override
    public List<Consumption> getConsumptions(long idClient, EnergyType energyType, YearMonth yearMonth) {
        return null;
    }
}
