package com.zara.catalog.application.services;

import com.zara.catalog.infraestructure.persistence.Category;

import java.util.List;

public interface CategoryServicesInt {

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(Category category);

    void deleteCategory(Long id);
}
