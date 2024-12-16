package com.zara.catalog.application.services;

import com.zara.catalog.infraestructure.persistence.Products;

import java.util.List;

public interface ProductServicesInt {

    List<Products> getSortedProductsByCategory(Long categoryId, double salesWeight, double stockWeight);

    double getStockRatio(String stock);

}
