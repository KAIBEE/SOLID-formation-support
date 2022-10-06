package fr.kaibee.solid;

import fr.kaibee.ioc.*;

import java.time.Clock;

public class Runner {
    public static void main(String[] args) {
        // We can now make singletons
        EnergyRepositoryProxy energyRepository = new EnergyRepositoryProxy(new LegacyEnergyRepository(), new EnergyRepositoryV2());
        // "beans" delegate instantiation to main... EnergyComputer doesn't know about proxy
        EnergyComputer energyComputer = new EnergyComputer(
                energyRepository,
                new HolePlugger(new ClientRepository(), energyRepository),
                Clock.systemUTC());

        long energy = energyComputer.computeEnergyConsumptionOfCurrentMonth(10, EnergyType.ELEC);
        System.out.println("energy = " + energy);
    }
}
