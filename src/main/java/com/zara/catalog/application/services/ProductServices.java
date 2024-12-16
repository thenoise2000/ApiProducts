package com.zara.catalog.application.services;

import com.zara.catalog.infraestructure.persistence.Products;
import com.zara.catalog.infraestructure.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServices implements ProductServicesInt {

    @Autowired
    private ProductsRepository productRepository;

    @Override
    public List<Products> getSortedProductsByCategory(Long categoryId, double salesWeight, double stockWeight) {
        List<Products> products = productRepository.findAllByCategoryId(categoryId);

        return products.stream()
                .sorted((p1, p2) -> {
                    double score1 = (p1.getSalesUnits() * salesWeight) + (getStockRatio(p1.getStock()) * stockWeight);
                    double score2 = (p2.getSalesUnits() * salesWeight) + (getStockRatio(p2.getStock()) * stockWeight);
                    return Double.compare(score2, score1);
                })
                .collect(Collectors.toList());
    }

    @Override
    public double getStockRatio(String stock) {
        String[] sizes = stock.split(" / ");
        int totalSizes = sizes.length;
        int inStockSizes = 0;

        for (String size : sizes) {
            String[] parts = size.split(":");
            int quantity = Integer.parseInt(parts[1].trim());
            if (quantity > 0) {
                inStockSizes++;
            }
        }

        return totalSizes > 0 ? (double) inStockSizes / totalSizes : 0;
    }
}
