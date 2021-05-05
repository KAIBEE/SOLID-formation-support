package fr.kaibee.solid;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    static AtomicInteger idCounter = new AtomicInteger(0);

    private Integer id;
    private Integer price;

    public Item(Integer price) {
        id = idCounter.incrementAndGet();
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("price=" + price)
                .toString();
    }
}
