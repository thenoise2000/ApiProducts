package com.zara.catalog.services;

import com.zara.catalog.application.services.ProductServices;
import com.zara.catalog.infraestructure.persistence.Products;
import com.zara.catalog.infraestructure.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductServices productService;

    @Mock
    private ProductsRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSortedProductsByCategory() {
        // Datos de prueba
        Products product1 = new Products();
        product1.setId(1L);
        product1.setName("V-NECH BASIC SHIRT");
        product1.setSalesUnits(100);
        product1.setStock("S: 4 / M: 9 / L: 0");

        Products product2 = new Products();
        product2.setId(2L);
        product2.setName("CONTRASTING LACE T-SHIRT");
        product2.setSalesUnits(650);
        product2.setStock("S: 0 / M: 1 / L: 0");

        when(productRepository.findAllByCategoryId(1L)).thenReturn(Arrays.asList(product1, product2));

        // Llamada al método
        List<Products> sortedProducts = productService.getSortedProductsByCategory(1L, 0.8, 0.2);

        // Verificación
        assertEquals(2, sortedProducts.size());
        assertEquals("CONTRASTING LACE T-SHIRT", sortedProducts.get(0).getName()); // Debe ser el primero
    }
}
