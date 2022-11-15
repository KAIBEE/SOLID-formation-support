package fr.kaibee.solid;

import fr.kaibee.ioc.*;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext.initialize();
        EnergyComputer energyComputer = ApplicationContext.getBean(EnergyComputer.class);
        long energy = energyComputer.computeEnergyConsumptionOfCurrentMonth(10, EnergyType.ELEC);
        System.out.println("energy = " + energy);
    }

    static class ApplicationContext {

        static Map<Class<?>, Object> beans = new HashMap<>();

        static void initialize() {
            registerBean(new ClientRepository());
            registerBean(Clock.systemUTC());
            registerBean(new LegacyEnergyRepository());
            registerBean(new EnergyRepositoryV2());
            EnergyRepositoryProxy energyRepository = new EnergyRepositoryProxy(
                    getBean(LegacyEnergyRepository.class), getBean(EnergyRepositoryV2.class));
            registerBean(energyRepository);

            registerBean(new HolePlugger(getBean(ClientRepository.class), getBean(EnergyRepositoryProxy.class)));

            // We can now make singletons
            // "beans" delegate instantiation to main... EnergyComputer doesn't know about proxy
            EnergyComputer energyComputer = new EnergyComputer(
                    energyRepository,
                    new HolePlugger(new ClientRepository(), energyRepository),
                    Clock.systemUTC());

            registerBean(energyComputer);
        }

        private static <T> void registerBean(T value) {
            beans.put(value.getClass(), value);
        }

        static <T> T getBean(Class<T> clasz) {
            Object o = beans.get(clasz);
            if (o.getClass().isAssignableFrom(clasz)) {
                return (T) o;
            }
            throw new IllegalArgumentException();

        }
    }


}
