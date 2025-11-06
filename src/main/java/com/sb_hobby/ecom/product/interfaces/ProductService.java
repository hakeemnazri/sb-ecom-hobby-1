package com.sb_hobby.ecom.product.interfaces;

import com.sb_hobby.ecom.product.DTO.ProductDTO;
import com.sb_hobby.ecom.product.DTO.ProductResponse;

public interface ProductService {
    ProductDTO createProduct(Long categoryId, ProductDTO productDTO);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword, String category);

    ProductResponse searchByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, Long categoryId);

    ProductResponse searchByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword);

    ProductDTO updateProduct(Long productId, ProductDTO productDTO);

    ProductDTO deleteProduct(Long productId);

    ProductResponse getAllProductsForAdmin(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse getAllProductsForSeller(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

}
