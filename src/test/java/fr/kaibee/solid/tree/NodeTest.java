package fr.kaibee.solid.tree;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Test
    public void should_return_value_when_leaf() {
        String result = "send mail 1";
        assertThat(getLeaf(result).evaluate(getPerson(0, 0, false, false)))
                .isEqualTo(Optional.of(result));
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
