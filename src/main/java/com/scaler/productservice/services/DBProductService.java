package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class DBProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private RedisTemplate<String, Object> productRedisTemplate;

    public DBProductService(ProductRepository productRepository, CategoryRepository categoryRepository, RedisTemplate<String, Object> productRedisTemplate) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productRedisTemplate = productRedisTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
//        Optional<Product> product = productRepository.findById(productId);
//        if (product.isEmpty()) {
//            throw new ProductNotFoundException(productId);
//        }
//        return product.get();
//        we can use the below lambda expression instead of the lines above and it will do the same thing.
//        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
//          Object product = productRedisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_"+productId);
//          System.out.println(product);
        Product product = (Product) productRedisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_"+productId);
//
        if (product != null) {
            return product;
        }

        Optional<Product> productJPQL = productRepository.findProductWithGivenId(productId);
        if (productJPQL.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

//        List<Product> productList = productRepository.findByCategory(productJPQL.get().getCategory());
//        productJPQL.get().getCategory().setProductList(productList);

        Product productObj = productJPQL.get();

        productRedisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_"+productId, productObj);

        return productObj;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

//    public List<Product> getProductsUsingCategory(){
//        return null;
//    }

    @Override
    public Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize) {
        return productRepository.findByTitleContainsIgnoreCase(
                title,
                PageRequest.of(pageNumber,
                        pageSize,
                        Sort.by(Sort.Direction.DESC, "price"))
        );
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        if (product == null) {
            throw new RuntimeException("Provide a product");
        }
        Category category = product.getCategory();
        if (category.getTitle() == null) {
            throw new CategoryNotFoundException("Category missing. Provide the category");
        }

        Optional<Category> categoryOptional = categoryRepository.findByTitle(category.getTitle());
        if (categoryOptional.isEmpty()) {
            categoryRepository.save(category);
        }
        else{
            product.setCategory(categoryOptional.get());
        }

//        categoryRepository.save(category);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    public List<Product> getProductsByCategory(Long categoryId){
        return productRepository.findAllByCategory_Id(categoryId);
    }


}
