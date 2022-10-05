package fr.kaibee.solid;

import fr.kaibee.ioc.EnergyComputer;
import fr.kaibee.ioc.EnergyType;

public class Runner {
    public static void main(String[] args) {
        long energy = new EnergyComputer().computeEnergyConsumptionOfCurrentMonth(10, EnergyType.ELEC);
        System.out.println("energy = " + energy);
    }
}
