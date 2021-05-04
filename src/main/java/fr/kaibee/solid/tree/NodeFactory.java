package fr.kaibee.solid.tree;

public interface NodeFactory {
    // Just command result
    // Not interested in children and guard
    Node leaf(CommandFactory cmdFactory);

    // Just children and guard
    // Not interested in command result
    Node middleNode(PersonPredicate guard, Node... nodes);

    // Just children
    // not interested in command result and guard
    Node root(Node... nodes);
}
