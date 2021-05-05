package fr.kaibee.solid.tree.better;

import fr.kaibee.solid.tree.AbstractNodeFactory;
import fr.kaibee.solid.tree.CommandFactory;
import fr.kaibee.solid.tree.Node;
import fr.kaibee.solid.tree.PersonPredicate;

import java.util.List;

public class BetterNodeFactory implements AbstractNodeFactory {
    @Override
    public Node leaf(CommandFactory cmdFactory) {
        return new LeafNode(cmdFactory);
    }

    @Override
    public Node middleNode(PersonPredicate guard, Node... nodes) {
        return new FilterPersonNodeDecorator(guard, new ParentNode(List.of(nodes)));
    }

    @Override
    public Node root(Node... nodes) {
        return new ParentNode(List.of(nodes));
    }
}
