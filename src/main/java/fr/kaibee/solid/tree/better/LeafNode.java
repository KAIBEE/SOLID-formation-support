package fr.kaibee.solid.tree.better;

import fr.kaibee.solid.tree.Command;
import fr.kaibee.solid.tree.CommandFactory;
import fr.kaibee.solid.tree.Node;
import fr.kaibee.solid.tree.Person;

import java.util.List;
import java.util.Optional;

public class LeafNode implements Node {

    private final CommandFactory commandFactory;

    LeafNode(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public Optional<Command> evaluate(Person person) {
        return Optional.of(commandFactory.create(person));
    }
}
