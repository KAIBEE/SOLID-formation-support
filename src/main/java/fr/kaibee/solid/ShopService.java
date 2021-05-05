package fr.kaibee.solid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShopService {

    private Map<Item, Integer> itemStock = new HashMap<>(); // For example only. Not threadsafe

    /**
     * Sells an item if in stock. Decrement stock for this item if present and return price of the sold item.
     *
     * @param item : item to sale
     * @return price of sold item
     */
    public Optional<Integer> sell(Item item) {
        Integer cardItem = itemStock.getOrDefault(item, 0);
        if (cardItem > 0) {
            itemStock.put(item, --cardItem);
            return Optional.of(item.getPrice());
        }

        return Optional.empty();
    }

    public void add(Item item) {
        itemStock.merge(item, 1, Integer::sum);
    }
}
