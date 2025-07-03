package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.producttypes.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    void search_NoObjects() {
        String pattern = "хлеб";
        List<Searchable> searchableList = new ArrayList<>();
        Mockito.when(storageService.getAll()).thenReturn(searchableList);

        List<SearchResult> resultList = searchService.search(pattern);

        assertEquals(0, resultList.size());
        verify(storageService, times(1)).getAll();
    }

    @Test
    void search_NoSuitableObjectAmongTheObjects() {
        String pattern = "хлеб";
        List<Searchable> searchableList = new ArrayList<>();
        Product product = new SimpleProduct(UUID.randomUUID(), "Молоко", 80);
        searchableList.add(product);
        Mockito.when(storageService.getAll()).thenReturn(searchableList);

        List<SearchResult> resultList = searchService.search(pattern);

        assertEquals(0, resultList.size());
        verify(storageService, times(1)).getAll();
    }

    @Test
    void search_SuitableObjectFound() {
        String pattern = "молоко";
        List<Searchable> searchableList = new ArrayList<>();
        Product product = new SimpleProduct(UUID.randomUUID(), "Молоко", 80);
        searchableList.add(product);
        Mockito.when(storageService.getAll()).thenReturn(searchableList);

        List<SearchResult> resultList = searchService.search(pattern);

        assertEquals("Молоко", resultList.get(0).getName());
        assertEquals(1, resultList.size());
        verify(storageService, times(1)).getAll();
    }
}
