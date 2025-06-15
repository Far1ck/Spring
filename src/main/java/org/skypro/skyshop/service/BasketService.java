package org.skypro.skyshop.service;

import org.skypro.skyshop.model.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductInBasket(UUID id) throws NoSuchProductException{
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException("Нет такого продукта");
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> userBasketList = productBasket.getAllProductsInBasket().entrySet().stream()
                .map(item -> new BasketItem(storageService.getProductById(item.getKey())
                        .orElseThrow(() -> new NoSuchProductException("Нет такого продукта")), item.getValue()))
                .toList();
        return new UserBasket(userBasketList);
    }
}
