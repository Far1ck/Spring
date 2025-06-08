package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private List<BasketItem> basketItemList;
    private int total;

    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
        this.total = basketItemList.stream().mapToInt(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }

    public List<BasketItem> getBasketItemList() {
        return basketItemList;
    }

    public int getTotal() {
        return total;
    }
}
