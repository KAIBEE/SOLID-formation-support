package fr.kaibee.solid.tree.better;

import fr.kaibee.solid.tree.Command;
import fr.kaibee.solid.tree.Node;
import fr.kaibee.solid.tree.Person;
import fr.kaibee.solid.tree.PersonPredicate;

import java.util.Optional;

public class FilterPersonNodeDecorator implements Node {


    private PersonPredicate guard;
    private Node targetNode;

    public FilterPersonNodeDecorator(PersonPredicate guard, Node targetNode) {
        this.guard = guard;
        this.targetNode = targetNode;
    }

    @Override
    public Optional<Command> evaluate(Person person) {
        if (guard.canHandle(person)) {
            return targetNode.evaluate(person);
        }
        return Optional.empty();
    }
}
