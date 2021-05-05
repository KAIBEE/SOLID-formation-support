package fr.kaibee.solid.tree.better;

import fr.kaibee.solid.tree.Command;
import fr.kaibee.solid.tree.Node;
import fr.kaibee.solid.tree.Person;

import java.util.*;

public class ParentNode implements Node {

    private final Collection<Node> nodes;

    ParentNode(Collection<Node> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("ParentNode should have children");
        }
        this.nodes = nodes;
    }

    @Override
    public Optional<Command> evaluate(Person person) {
        List<Command> commands = new ArrayList<>();
        for (Iterator<Node> iterator = getNodeIterator(nodes); iterator.hasNext(); ) {
            Node node = iterator.next();
            node.evaluate(person).ifPresent(commands::add);
        }

        if (commands.isEmpty()) {
            return Optional.empty();
        }

        if (commands.size() > 1) {
            throw new IllegalStateException("Tree issue : several commands have been created");
        }

        return Optional.of(commands.get(0));
    }

    protected Iterator<Node> getNodeIterator(Collection<Node> nodes) {
        return nodes.iterator();
    }
}
