package com.sb_hobby.ecom.category;

import com.sb_hobby.ecom.category.DTO.CategoryDTO;
import com.sb_hobby.ecom.category.DTO.CategoryResponse;
import com.sb_hobby.ecom.category.entities.Category;
import com.sb_hobby.ecom.category.repositories.CategoryRepository;
import com.sb_hobby.ecom.common.exceptions.APIException;
import com.sb_hobby.ecom.common.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();

        if(categories.isEmpty()){
            throw new APIException("No category created");
        }

        List<CategoryDTO> categoryDTOs = categories.stream().map(c -> modelMapper.map(c, CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse(categoryDTOs, categoryPage.getNumber(), categoryPage.getSize(), categoryPage.getTotalElements(), categoryPage.getTotalPages(), categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if(optionalCategory.isPresent()){
            throw  new APIException("Category with the name" + category.getCategoryName() +  "is already exists!");
        }

        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(category);

        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category categoryToUpdate = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Category category = modelMapper.map(categoryDTO, Category.class);

        category.setCategoryId(categoryToUpdate.getCategoryId());

        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
