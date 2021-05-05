package fr.kaibee.solid;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShopServiceTest {

    @Test
    public void forDemo() {
        ShopService shopService = getShopService();

        int priceItem1 = 5;
        Item item1 = new Item(priceItem1);
        shopService.add(item1);
        shopService.add(item1);

        int priceItem2 = 3;
        Item item2 = new Item(priceItem2);
        shopService.add(item2);

        assertThat(shopService.sell(item1)).isEqualTo(Optional.of(priceItem1));
        assertThat(shopService.sell(item1)).isEqualTo(Optional.of(priceItem1));
        assertThat(shopService.sell(item1)).isEmpty();

        assertThat(shopService.sell(item2)).isEqualTo(Optional.of(priceItem2));
        assertThat(shopService.sell(item2)).isEmpty();

    }

    private ShopService getShopService() {
        return new ShopService();
    }
}
