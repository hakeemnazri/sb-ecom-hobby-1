package com.sb_hobby.ecom.product;

import com.sb_hobby.ecom.category.entities.Category;
import com.sb_hobby.ecom.category.repositories.CategoryRepository;
import com.sb_hobby.ecom.common.exceptions.APIException;
import com.sb_hobby.ecom.common.exceptions.ResourceNotFoundException;
import com.sb_hobby.ecom.product.DTO.ProductDTO;
import com.sb_hobby.ecom.product.DTO.ProductResponse;
import com.sb_hobby.ecom.product.entities.Product;
import com.sb_hobby.ecom.product.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public ProductDTO createProduct(Long categoryId, ProductDTO productDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Product> products = category.getProducts();

        for(Product p: products){
            if(p.getProductName().equals(productDTO.getProductName())){
                throw new APIException("Product already exists");
            }
        }

        Product product = modelMapper.map(productDTO, Product.class);
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
        product.setImage("image.jpeg");
        product.setCategory(category);
        //TODO:set User
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword, String category) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        String cleanKeyword = (keyword == null || keyword.trim().isEmpty()) ? null : "%" + keyword + "%";
        String cleanCategory = (category == null || category.trim().isEmpty()) ? null : category;

        Page<Product> pageableProducts = productRepository.findAllByKeywordAndCategory(pageDetails, cleanKeyword, cleanCategory);

        List<Product> products = pageableProducts.getContent();

        List<ProductDTO> productList = products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).toList();

        ProductResponse productResponse = ProductResponse.builder()
                .content(productList)
                .pageSize(pageableProducts.getSize())
                .pageNumber(pageableProducts.getNumber())
                .lastPage(pageableProducts.isLast())
                .totalElements(pageableProducts.getTotalElements())
                .totalPages(pageableProducts.getTotalPages())
                .build();

        return productResponse;
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
