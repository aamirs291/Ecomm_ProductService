package com.scaler.ecomm_productservice.services;

import com.scaler.ecomm_productservice.exceptions.CategoryNotFoundException;
import com.scaler.ecomm_productservice.exceptions.ProductNotFoundException;
import com.scaler.ecomm_productservice.models.Category;
import com.scaler.ecomm_productservice.models.Product;
import com.scaler.ecomm_productservice.repositories.CategoryRepository;
import com.scaler.ecomm_productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException{
        Category category = product.getCategory();

        if (category == null) {
            throw new CategoryNotFoundException("Add a category to the product first");
        }

        Optional<Category> optionalCategory = categoryRepository.findByTitle(product.getCategory().getTitle());

        if (optionalCategory.isEmpty()) {
            category = categoryRepository.save(product.getCategory());
        } else {
            category = optionalCategory.get();
        }

        product.setCategory(category);

        return productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize) {
        return null;
    }
}
