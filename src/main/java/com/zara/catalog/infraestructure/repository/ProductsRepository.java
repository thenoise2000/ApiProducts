package com.zara.catalog.infraestructure.repository;

import com.zara.catalog.infraestructure.persistence.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findAllByCategoryId(Long categoryId);
}
