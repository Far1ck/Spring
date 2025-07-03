package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.error.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.producttypes.SimpleProduct;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    void addNonExistendProductInBasket_ExceptionIsThrown() {
        UUID id = UUID.randomUUID();
        Optional<Product> optionalProduct = Optional.empty();
        when(storageService.getProductById(any(UUID.class))).thenReturn(optionalProduct);

        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProductInBasket(id));
        Mockito.verify(storageService, Mockito.times(1)).getProductById(id);
    }

    @Test
    void addAnExistingProduct_Success() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct(id, "Молоко", 80);
        Optional<Product> optionalProduct = Optional.of(product);
        when(storageService.getProductById(any(UUID.class))).thenReturn(optionalProduct);
        doNothing().when(productBasket).addProduct(any(UUID.class));

        basketService.addProductInBasket(id);

        Mockito.verify(storageService, times(1)).getProductById(id);
        Mockito.verify(productBasket, times(1)).addProduct(id);
    }

    @Test
    void getUserBasketWhenProductBasketIsEmpty_ReturnEmptyBasket() {
        when(productBasket.getAllProductsInBasket()).thenReturn(new HashMap<>());

        UserBasket result = basketService.getUserBasket();

        assertEquals(0, result.getTotal());
        Mockito.verify(productBasket, times(1)).getAllProductsInBasket();
        Mockito.verify(storageService, times(0)).getProductById(any(UUID.class));
    }

    @Test
    void getUserBasketWhenThereAreProductsInProductBasket_ReturnSuitableBasket() {
        Map<UUID, Integer> productsInBasket = new HashMap<>();
        UUID id = UUID.randomUUID();
        productsInBasket.put(id, 2);
        Product product = new SimpleProduct(id, "Молоко", 80);
        when(productBasket.getAllProductsInBasket()).thenReturn(productsInBasket);
        when(storageService.getProductById(any(UUID.class))).thenReturn(Optional.of(product));

        UserBasket result = basketService.getUserBasket();

        assertEquals(160, result.getTotal());
        verify(productBasket, times(1)).getAllProductsInBasket();
        verify(storageService, times(1)).getProductById(id);
    }
}
