package fr.kaibee.ioc;

import java.time.YearMonth;
import java.util.List;

public class HolePlugger {
    private ClientRepository clientRepository;
    private EnergyRepository energyRepository;

    public HolePlugger(ClientRepository clientRepository, EnergyRepositoryProxy energyRepository) {
        this.clientRepository = clientRepository;
        this.energyRepository = energyRepository;
    }

    public List<Consumption> plugHoles(List<Consumption> consumptions, long idClient, EnergyType energyType, YearMonth period) {
        if (hasHoles(consumptions)) {
            long similarClient = clientRepository.findClientWithSimilarProfile(idClient);
            List<Consumption> consumptionsOfSimilarClient = energyRepository.getConsumptions(similarClient, energyType, period);
            return merge(consumptions, consumptionsOfSimilarClient);
        }
        return consumptions;
    }

    private List<Consumption> merge(List<Consumption> consumptions, List<Consumption> consumptionsOfSimilarClient) {
        return null;
    }

    private boolean hasHoles(List<Consumption> consumptions) {
        return false;
    }
}
