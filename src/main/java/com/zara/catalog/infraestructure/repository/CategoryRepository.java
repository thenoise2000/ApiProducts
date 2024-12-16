package com.zara.catalog.infraestructure.repository;

import com.zara.catalog.infraestructure.persistence.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
