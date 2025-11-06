package com.sb_hobby.ecom.product;

import com.sb_hobby.ecom.category.entities.Category;
import com.sb_hobby.ecom.category.repositories.CategoryRepository;
import com.sb_hobby.ecom.common.exceptions.APIException;
import com.sb_hobby.ecom.common.exceptions.ResourceNotFoundException;
import com.sb_hobby.ecom.product.DTO.ProductDTO;
import com.sb_hobby.ecom.product.DTO.ProductResponse;
import com.sb_hobby.ecom.product.entities.Product;
import com.sb_hobby.ecom.product.interfaces.ProductService;
import com.sb_hobby.ecom.product.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

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

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

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

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Product> pageableProducts = productRepository.findAllByCategory(pageDetails, categoryId);

        List<Product> products = pageableProducts.getContent();

        List<ProductDTO> productDtos = products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).toList();

        ProductResponse productResponse = ProductResponse.builder()
                .totalPages(pageableProducts.getTotalPages())
                .totalElements(pageableProducts.getTotalElements())
                .lastPage(pageableProducts.isLast())
                .pageNumber(pageableProducts.getNumber())
                .pageSize(pageableProducts.getSize())
                .content(productDtos)
                .build();

        return productResponse;
    }

    @Override
    public ProductResponse searchByKeyword(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String keyword) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Product> pageableProducts = productRepository.findAllByKeyword(pageDetails, "%" + keyword + "%");

        List<Product> products = pageableProducts.getContent();

        List<ProductDTO> productDtos = products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).toList();

        ProductResponse productResponse = ProductResponse.builder()
                .lastPage(pageableProducts.isLast())
                .totalPages(pageableProducts.getTotalPages())
                .totalElements(pageableProducts.getTotalElements())
                .pageSize(pageableProducts.getSize())
                .pageNumber(pageableProducts.getNumber())
                .content(productDtos)
                .build();

        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product productFromDb = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        Product product = modelMapper.map(productDTO, Product.class);

        productFromDb.setProductName(product.getProductName());
        productFromDb.setCategory(product.getCategory());
        productFromDb.setDiscount(product.getDiscount());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setSpecialPrice(product.getSpecialPrice());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setQuantity(product.getQuantity());

        Product savedProduct = productRepository.save(productFromDb);

        //TODO: Add Cart

        ProductDTO savedProductDto = modelMapper.map(savedProduct, ProductDTO.class);

        return savedProductDto;
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        //TODO: CART

        productRepository.delete(product);

        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProductsForAdmin(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        //TODO: add authUtils
        return null;
    }

    @Override
    public ProductResponse getAllProductsForSeller(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }
}
