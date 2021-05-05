package fr.kaibee.solid.service;

import java.util.List;

public interface DistanceComputer {
    List<Distance> computeDistanceOfLastYear(Long familyId);
}
