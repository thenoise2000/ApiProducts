package com.zara.catalog.infraestructure.controller;

import com.zara.catalog.application.services.ProductServices;
import com.zara.catalog.infraestructure.persistence.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServices productService;

    @GetMapping("/sorted")
    public List<Products> getSortedProductsByCategory(
            @RequestParam Long categoryId,
            @RequestParam double salesWeight,
            @RequestParam double stockWeight) {
        return productService.getSortedProductsByCategory(categoryId, salesWeight, stockWeight);
    }
}
