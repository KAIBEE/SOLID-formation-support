package fr.kaibee.ioc;

import java.time.YearMonth;
import java.util.List;

public interface EnergyRepository {
    List<Consumption> getConsumptions(long idClient, EnergyType energyType, YearMonth yearMonth);
}
