package com.sb_hobby.ecom.product.repositories;

import com.sb_hobby.ecom.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
