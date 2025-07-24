package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product) throws CategoryNotFoundException;

    void deleteProduct(Long productId);

    List<Product> getProductsByCategory(Long categoryId);

    Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize);
}
