package fr.kaibee.solid.tree;

import java.util.Optional;

public class Node {

    private String result;

    public Node(String result) {
        this.result = result;
    }

    public Optional<String> evaluate(Person person) {
        return Optional.of(result);
    }
}
