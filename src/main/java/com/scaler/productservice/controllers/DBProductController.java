package com.scaler.ecomm_productservice.controllers;

import com.scaler.ecomm_productservice.dtos.ProductDTO;
import com.scaler.ecomm_productservice.exceptions.CategoryNotFoundException;
import com.scaler.ecomm_productservice.exceptions.ProductNotFoundException;
import com.scaler.ecomm_productservice.models.Category;
import com.scaler.ecomm_productservice.models.Product;
import com.scaler.ecomm_productservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db")
public class DBProductController {

    private ProductService dbProductService;

    public DBProductController(ProductService dbProductService) {
        this.dbProductService = dbProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        Product product = dbProductService.getSingleProduct(productId);
        System.out.println("DEBUG");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(dbProductService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}/products")
    public ResponseEntity<List<Product>> getProductsUsingCategory(@PathVariable("id") Long categoryId) {
        return new ResponseEntity<>(dbProductService.getProductsByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/title/{title}/{pageNumber}/{pageSize}")
    public Page<Product> getProductByTitle(@PathVariable("title") String title, @PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        return dbProductService.getProductsByTitle(title, pageNumber, pageSize);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDto) throws CategoryNotFoundException {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());

        Category category = new Category();
//        category.setId(1L);
        category.setTitle(productDto.getCategory());
        product.setCategory(category);

        Product productObject = dbProductService.createProduct(product);

        return new ResponseEntity<>(productObject,HttpStatus.OK);
    }

}
