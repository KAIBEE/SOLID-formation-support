package fr.kaibee.solid.tree;

import java.util.List;
import java.util.Optional;

public class GodNode implements Node {

    private CommandFactory commandFactory;
    private List<Node> nodes;
    private PersonPredicate guard;

    GodNode(CommandFactory commandFactory, List<Node> nodes, PersonPredicate guard) {
        this.commandFactory = commandFactory;
        this.nodes = nodes;
        this.guard = guard;
    }

    /**
     * OK, guard can vary independently but....
     *
     * 1. Here we have a node which tests he can handle,
     * 2. then iterate over his children
     * And if no child gives an evaluation (3. no compatibility or 4.no child),
     * 5. then return current command...
     *
     * It doesn't match what we wanted...
     * What we want :
     *
     * root = delegate
     * middle = canHandle ? delegate : return empty
     * leaf = return value
     *
     */
    @Override
    public Optional<Command> evaluate(Person person) {

        if (!guard.canHandle(person)) {
            return Optional.empty();
        }

        return nodes
                .stream()
                .map(node -> node.evaluate(person))
                .filter(Optional::isPresent)
                .reduce((s, s2) -> {
                    throw new IllegalStateException("");
                })
                .orElseGet(() -> Optional.of(commandFactory.create(person)));
    }
}
