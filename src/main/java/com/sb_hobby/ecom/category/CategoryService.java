package com.sb_hobby.ecom.category;

import com.sb_hobby.ecom.category.DTO.CategoryDTO;
import com.sb_hobby.ecom.category.DTO.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories (Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory (CategoryDTO categoryDTO);

    CategoryDTO deleteCategory (Long categoryId);

    CategoryDTO updateCategory (CategoryDTO categoryDTO, Long categoryId);
}
