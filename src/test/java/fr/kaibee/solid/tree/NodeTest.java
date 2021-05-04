package fr.kaibee.solid.tree;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Test
    public void should_return_value_when_leaf() {
        String result = "send mail 1";
        assertThat(getLeaf(result).evaluate(getPerson())).isEqualTo(Optional.of(result));
    }

    private Node getLeaf(String result) {
        return new Node(result);
    }

    private Person getPerson() {
        return new Person();
    }
}
