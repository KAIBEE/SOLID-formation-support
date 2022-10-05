package fr.kaibee.ioc;

import java.time.Clock;
import java.time.Instant;
import java.time.YearMonth;
import java.util.List;

import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

public class EnergyComputer {
    private EnergyRepository energyRepository = new EnergyRepositoryProxy();
    private HolePlugger holePlugger = new HolePlugger();
    private Clock clock = Clock.systemUTC();

    public long computeEnergyConsumptionOfCurrentMonth(long idClient, EnergyType energyType) {
        Instant now = clock.instant();
        YearMonth yearMonth = YearMonth.of(now.get(MONTH_OF_YEAR), now.get(YEAR));
        List<Consumption> consumptions = energyRepository.getConsumptions(idClient, energyType, yearMonth);
        List<Consumption> completedConsumptions = holePlugger.plugHoles(consumptions, idClient, energyType, yearMonth);
        return sumEnergyConsumptions(completedConsumptions);
    }

    private long sumEnergyConsumptions(List<Consumption> completedConsumptions) {
        return 0;
    }
}
