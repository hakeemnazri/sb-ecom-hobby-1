package com.sb_hobby.ecom.category;

import com.sb_hobby.ecom.category.DTO.CategoryDTO;
import com.sb_hobby.ecom.category.DTO.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        return null;
    }
}
