package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String productName;
    private final UUID id;

    public Product(UUID id, String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        this.productName = productName;
        this.id = id;
    }

    public String getName() {
        return productName;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public String getSearchTerm() {
        return productName;
    }

    @JsonIgnore
    public String getTypeOfContent() {
        return "PRODUCT";
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }
}
