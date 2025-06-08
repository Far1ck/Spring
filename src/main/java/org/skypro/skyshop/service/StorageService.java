package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.producttypes.DiscountedProduct;
import org.skypro.skyshop.model.product.producttypes.FixPriceProduct;
import org.skypro.skyshop.model.product.producttypes.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        products = new HashMap<>();
        articles = new HashMap<>();
        this.fill();
    }

    private void fill() {
        Product bread = new SimpleProduct(UUID.randomUUID(),"Хлеб", 50);
        Product milk = new FixPriceProduct(UUID.randomUUID(), "Молоко");
        Product eggs = new DiscountedProduct(UUID.randomUUID(), "Яйца", 120, 20);
        Product cheese = new DiscountedProduct(UUID.randomUUID(), "Сыр", 152, 30);
        Product sausage = new SimpleProduct(UUID.randomUUID(), "Колбаса", 280);
        Product cottageCheese = new SimpleProduct(UUID.randomUUID(), "Творог", 120);
        Article article1 = new Article(UUID.randomUUID(), "Хлебная мудрость", "Лучше Хлеб с водою, чем пирог с бедою");
        Article article2 = new Article(UUID.randomUUID(), "Сырная мудрость", "Бесплатный Сыр бывает только в мышеловке. " +
                "И только для второй мыши");
        products.put(bread.getId(), bread);
        products.put(milk.getId(), milk);
        products.put(eggs.getId(), eggs);
        products.put(cheese.getId(), cheese);
        products.put(sausage.getId(), sausage);
        products.put(cottageCheese.getId(), cottageCheese);
        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public List<Article> getAllArticles() {
        return new ArrayList<>(articles.values());
    }

    public List<Searchable> getAll() {
        List<Searchable> result = new ArrayList<>(products.values());
        result.addAll(articles.values());
        return result;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}
