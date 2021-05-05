package fr.kaibee.solid;

import java.util.concurrent.atomic.AtomicInteger;

public class BeginnerShopService extends ShopService {

    static AtomicInteger nbAddStocks = new AtomicInteger(0);

    @Override
    public void add(Item item) {
        if (nbAddStocks.incrementAndGet() % 2 == 0) {
            super.add(item);
        }
    }
}
