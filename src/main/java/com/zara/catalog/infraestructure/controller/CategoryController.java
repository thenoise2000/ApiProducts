package com.zara.catalog.infraestructure.controller;

import com.zara.catalog.application.services.CategoryServices;
import com.zara.catalog.domain.modelRules.apiResponse.CategoryExceptions;
import com.zara.catalog.infraestructure.persistence.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;
    //public String msg;

    @GetMapping
    public List<Category> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        if (categories == null || categories.isEmpty()) {
            throw new CategoryExceptions("No se encontraron categorías.");
        }

        return categories;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new CategoryExceptions("No se encontro la categoría");
        }
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
