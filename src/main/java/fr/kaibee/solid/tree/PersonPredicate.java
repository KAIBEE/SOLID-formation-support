package fr.kaibee.solid.tree;

@FunctionalInterface
public interface PersonPredicate {
    boolean canHandle(Person person);

    PersonPredicate ALWAYS = person -> true;
}
