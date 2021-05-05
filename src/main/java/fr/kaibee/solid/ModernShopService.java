package fr.kaibee.solid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModernShopService extends ShopService {

    private List<String> stockTransactions = new ArrayList<>();

    @Override
    public void add(Item item) {
        super.add(item);
        sendAddToStockTransaction(item);
    }

    @Override
    public Optional<Integer> sell(Item item) {
        Optional<Integer> someSalePrice = super.sell(item);
        if (someSalePrice.isPresent()) {
            sendSaleTransaction(item);
        }
        return someSalePrice;
    }

    private boolean sendSaleTransaction(Item item) {
        return stockTransactions.add(item + " sold");
    }

    private boolean sendAddToStockTransaction(Item item) {
        return stockTransactions.add(item + " added");
    }
}

