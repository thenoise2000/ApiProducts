package com.zara.catalog.domain.modelRules.resProducts;

import com.zara.catalog.infraestructure.persistence.Products;

public class ResProducts {

    private Long id;
    private String name;
    private int salesUnits;
    private String stock;
    private Long categoryId;

public ResProducts(final Products products) {
        this.id = products.getId();
        this.name = products.getName();
        this.salesUnits = products.getSalesUnits();
        this.stock = products.getStock();
        this.categoryId = products.getCategory().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalesUnits() {
        return salesUnits;
    }

    public void setSalesUnits(int salesUnits) {
        this.salesUnits = salesUnits;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
