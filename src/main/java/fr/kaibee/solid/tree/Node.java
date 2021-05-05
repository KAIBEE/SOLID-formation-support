package fr.kaibee.solid.tree;

import java.util.Optional;

public interface Node {
    Optional<Command> evaluate(Person person);
}
