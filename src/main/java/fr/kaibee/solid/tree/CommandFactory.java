package fr.kaibee.solid.tree;

@FunctionalInterface
public interface CommandFactory {
    Command create(Person person);
}
