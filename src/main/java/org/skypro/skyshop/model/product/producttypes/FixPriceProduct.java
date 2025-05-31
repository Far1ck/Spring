package org.skypro.skyshop.model.product.producttypes;

import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 50;

    public FixPriceProduct(UUID id, String productName) {
        super(id, productName);
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getName() + ": " + FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}

