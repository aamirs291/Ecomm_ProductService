package com.scaler.ecomm_productservice.services;

import com.scaler.ecomm_productservice.exceptions.CategoryNotFoundException;
import com.scaler.ecomm_productservice.exceptions.ProductNotFoundException;
import com.scaler.ecomm_productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product) throws CategoryNotFoundException;

    void deleteProduct(Long productId);

    List<Product> getProductsByCategory(Long categoryId);

    Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize);
}
