package fr.kaibee.solid.tree.better;

import fr.kaibee.solid.tree.Command;
import fr.kaibee.solid.tree.Node;
import fr.kaibee.solid.tree.Person;

import java.util.List;
import java.util.Optional;

public class ParentNode implements Node {

    private List<Node> nodes;

    ParentNode(List<Node> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("ParentNode should have children");
        }
        this.nodes = nodes;
    }

    @Override
    public Optional<Command> evaluate(Person person) {
        return nodes
                .stream()
                .map(node -> node.evaluate(person))
                .filter(Optional::isPresent)
                .reduce((s, s2) -> {
                    throw new IllegalStateException("Tree issue : several commands have been created");
                })
                .orElseGet(Optional::empty);
    }
}
