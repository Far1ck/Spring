package org.skypro.skyshop.model.product.producttypes;

import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int productPrice;

    public SimpleProduct(UUID id, String productName, int productPrice) {
        super(id, productName);
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        this.productPrice = productPrice;
    }

    @Override
    public int getPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
