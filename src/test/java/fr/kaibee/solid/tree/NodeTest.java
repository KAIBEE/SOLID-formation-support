package fr.kaibee.solid.tree;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Test
    public void should_return_value_when_leaf() {
        String result = "send mail 1";
        assertThat(getLeaf(result)

                .evaluate(getPerson(0, 0, false, false)))

                .isEqualTo(Optional.of(result));
    }

    @Test
    public void should_return_value_of_children() {
        String result = "send mail 1";
        assertThat(getParent(List.of(getLeaf(result)))

                .evaluate(getPerson(0, 0, false, false)))

                .isEqualTo(Optional.of(result));
    }

    private Node getParent(List<Node> children) {
        return new Node(children);
    }


    private Node getLeaf(String result) {
        return new Node(result);
    }

    private Person getPerson(final int age, final int dept, final boolean hasCar, final boolean hasMotorbike) {
        return new Person() {
            @Override
            public int getAge() {
                return age;
            }

            @Override
            public int getDept() {
                return dept;
            }

            @Override
            public boolean hasCar() {
                return hasCar;
            }

            @Override
            public boolean hasMotorbike() {
                return hasMotorbike;
            }
        };
    }
}
