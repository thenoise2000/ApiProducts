package com.zara.catalog.controllers;


import com.zara.catalog.application.services.ProductServices;
import com.zara.catalog.infraestructure.persistence.Products;
import com.zara.catalog.infraestructure.controller.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServices productService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetSortedProductsByCategory() throws Exception {
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

        // Simulamos que el servicio devuelve los productos en el orden correcto
        when(productService.getSortedProductsByCategory(1L, 0.8, 0.2)).thenReturn(Arrays.asList(product2, product1));

        // Llamada al endpoint
        mockMvc.perform(get("/api/products/sorted")
                        .param("categoryId", "1")
                        .param("salesWeight", "0.8")
                        .param("stockWeight", "0.2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("CONTRASTING LACE T-SHIRT")) // Verificación del primer producto
                .andExpect(jsonPath("$[1].name").value("V-NECH BASIC SHIRT")); // Verificación del segundo producto
    }
}
