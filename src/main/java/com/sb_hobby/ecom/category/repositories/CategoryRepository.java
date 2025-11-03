package com.sb_hobby.ecom.category.repositories;

import com.sb_hobby.ecom.category.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
