package fr.kaibee.solid.tree;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DefaultNodeFactory implements AbstractNodeFactory {
    // Just command result
    // Not interested in children and guard
    @Override
    public Node leaf(CommandFactory cmdFactory) {
        return new GodNode(Objects.requireNonNull(cmdFactory), Collections.emptyList(), PersonPredicate.ALWAYS);
    }

    // Just children and guard
    // Not interested in command result
    @Override
    public Node middleNode(PersonPredicate guard, Node... nodes) {
        return new GodNode(null, List.of(nodes), guard);
    }

    // Just children
    // not interested in command result and guard
    @Override
    public Node root(Node... nodes) {
        return new GodNode(null, List.of(nodes), PersonPredicate.ALWAYS);
    }
}
