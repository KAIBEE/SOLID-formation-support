package fr.kaibee.solid.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NodeTest {

    public static final String NO_RESULT = "No result";
    private NodeFactory factory;

    private Supplier<String> resultProvider;

    @Before
    public void setUp() {
        factory = getFactory();
        resultProvider = () -> NO_RESULT;
    }

    @Test
    public void should_pick_trottinette_for_minor_parisian() {
        String car = "car";
        String motorbike = "motorbike";
        String electricalTrottinette = "electrical trottinette";
        String bike = "bike";

        Node adultNode = factory.middleNode(this::isMajor,
                getCarDriverNode(car),
                getMotorbikeNode(motorbike));

        Node minorNode = factory.middleNode(p -> !isMajor(p),
                getElectricalTrottinetteNode(electricalTrottinette),
                getBikeNode(bike));

        Node root = factory.root(adultNode, minorNode);

        Person person = getPerson(17, 75, false, false);
        Optional<Command> result = root.evaluate(person);

        result.orElse(() -> {}).execute();
        assertThat(resultProvider.get()).isEqualTo(person + electricalTrottinette);
    }

    private Node getBikeNode(String bikeMsg) {
        Node leaf4 = factory.leaf(p -> makeCommand(p + bikeMsg));
        return factory.middleNode(p -> !isParisian(p), leaf4);
    }

    private Node getElectricalTrottinetteNode(String electricalTrottinetteMsg) {
        Node leaf3 = factory.leaf(p -> makeCommand(p + electricalTrottinetteMsg));
        return factory.middleNode(this::isParisian, leaf3);
    }

    private Command makeCommand(String s) {
        return () -> updateResult(s);
    }

    private boolean isParisian(Person p) {
        return p.getDept() == 75;
    }

    private boolean isMajor(Person p) {
        return p.getAge() >= 18;
    }

    private Node getCarDriverNode(String carDriverMsg) {
        Node carLeaf = factory.leaf(p -> makeCommand(p + carDriverMsg));
        return factory.middleNode(Person::hasCar, carLeaf);
    }

    private Node getMotorbikeNode(String motorbikerMsg) {
        Node motorbikeLeaf = factory.leaf(p -> makeCommand(p + motorbikerMsg));
        return factory.middleNode(p -> !p.hasCar() && p.hasMotorbike(), motorbikeLeaf);
    }

    private NodeFactory getFactory() {
        return new DefaultNodeFactory();
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

            @Override
            public String toString() {
                return new StringJoiner(", ", "Person" + "[", "]")
                        .add("age=" + getAge())
                        .add("dept=" + getDept())
                        .add("hasCar=" + hasCar())
                        .add("hasMotorbike=" + hasMotorbike())
                        .toString();
            }
        };
    }

    private void updateResult(String result) {
        resultProvider = () -> result;
    }
}
