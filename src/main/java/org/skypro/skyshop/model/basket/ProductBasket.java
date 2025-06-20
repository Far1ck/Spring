package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productBasket;

    public ProductBasket(Map<UUID, Integer> productBasket) {
        this.productBasket = productBasket;
    }

    public void addProduct(UUID id) {
        if (productBasket.containsKey(id)) {
            productBasket.put(id, productBasket.get(id) + 1);
        } else {
            productBasket.put(id, 1);
        }
    }

    public Map<UUID, Integer> getAllProductsInBasket() {
        return Collections.unmodifiableMap(productBasket);
    }
}
