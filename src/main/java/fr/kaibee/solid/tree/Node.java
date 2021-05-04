package fr.kaibee.solid.tree;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Node {

    private String result;
    private List<Node> nodes = Collections.emptyList();

    public Node(String result) {
        this.result = result;
    }

    public Node(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Optional<String> evaluate(Person person) {
        return nodes
                .stream()
                .map(node -> node.evaluate(person))
                .filter(Optional::isPresent)
                .reduce((s, s2) -> { throw new IllegalStateException("");})

                .orElseGet(() -> Optional.of(result));
    }
}
