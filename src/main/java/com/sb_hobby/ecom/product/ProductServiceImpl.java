package com.sb_hobby.ecom.product;

import com.sb_hobby.ecom.product.DTO.ProductDTO;
import com.sb_hobby.ecom.product.DTO.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Override
    public ProductDTO createProduct(Long categoryId, ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword, String category) {
        return null;
    }

    @Override
    public ProductResponse searchByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, Long categoryId) {
        return null;
    }

    @Override
    public ProductResponse searchByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        return null;
    }

    @Override
    public ProductResponse getAllProductsForAdmin(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public ProductResponse getAllProductsForSeller(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }
}
