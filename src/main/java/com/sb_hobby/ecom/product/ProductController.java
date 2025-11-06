package com.sb_hobby.ecom.product;

import com.sb_hobby.ecom.common.constants.AppConstants;
import com.sb_hobby.ecom.product.DTO.ProductDTO;
import com.sb_hobby.ecom.product.DTO.ProductResponse;
import com.sb_hobby.ecom.product.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    //Public

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "category", required = false) String category
    ){
        ProductResponse allProducts = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder, keyword, category);

        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder,
            @PathVariable Long categoryId
    ){
        ProductResponse productResponse = productService.searchByCategory(pageNumber, pageSize, sortBy, sortOrder, categoryId);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder,
            @PathVariable String keyword
    ){
        ProductResponse productResponse = productService.searchByKeyword(pageNumber, pageSize, sortBy, sortOrder, keyword);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    //CRUD - Admin

    @GetMapping("/admin/products")
    public ResponseEntity<ProductResponse> getAllProductsForAdmin(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        ProductResponse allProductsForAdmin = productService.getAllProductsForAdmin(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(allProductsForAdmin, HttpStatus.OK);
    }

    @PostMapping("/admin/categories/{categoriesId}/products")
    public ResponseEntity<ProductDTO> createProductAdmin(
            @Valid @RequestBody ProductDTO productDTO,
            @PathVariable Long categoryId
            ){
        ProductDTO product = productService.createProduct(categoryId, productDTO);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProductForAdmin(
            @PathVariable Long productId,
            @Valid @RequestBody ProductDTO productDto
    ){
        ProductDTO productDTO = productService.updateProduct(productId, productDto);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProductForAdmin(
            @PathVariable Long productId
    ){
        ProductDTO productDTO = productService.deleteProduct(productId);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    // CRUD - Seller

    @GetMapping("/seller/products")
    public ResponseEntity<ProductResponse> getAllProductsForSeller(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        ProductResponse allProductsForAdmin = productService.getAllProductsForSeller(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(allProductsForAdmin, HttpStatus.OK);
    }

    @PostMapping("/seller/categories/{categoriesId}/products")
    public ResponseEntity<ProductDTO> createProductSeller(
            @Valid @RequestBody ProductDTO productDTO,
            @PathVariable Long categoryId
    ){
        ProductDTO product = productService.createProduct(categoryId, productDTO);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/seller/products/{productId}")
    public ResponseEntity<ProductDTO> updateProductForSeller(
            @PathVariable Long productId,
            @Valid @RequestBody ProductDTO productDto
    ){
        ProductDTO productDTO = productService.updateProduct(productId, productDto);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/seller/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProductForSeller(
            @PathVariable Long productId
    ){
        ProductDTO productDTO = productService.deleteProduct(productId);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }








}
