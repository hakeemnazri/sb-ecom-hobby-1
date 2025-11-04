package com.sb_hobby.ecom.product.repositories;

import com.sb_hobby.ecom.product.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            "WHERE (:keyword IS NULL OR LOWER(p.productName) LIKE LOWER(:keyword)) " +
            "AND (:category IS NULL OR p.category.categoryName = :category)")
    Page<Product> findAllByKeywordAndCategory(Pageable pageable, @Param("keyword") String keyword, @Param("category") String category);

    @Query("SELECT p FROM Product p " +
    "WHERE p.category.categoryId = :categoryId")
    Page<Product> findAllByCategory(Pageable pageable, @Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p " +
    "WHERE LOWER(p.productName) LIKE LOWER(:keyword)")
    Page<Product> findAllByKeyword(Pageable pageable, @Param("keyword") String keyword);
}
