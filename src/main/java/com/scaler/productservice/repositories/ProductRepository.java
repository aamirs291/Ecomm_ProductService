package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long productId);

    List<Product> findAll();

    @Query("SELECT p FROM products p WHERE p.id=?1")
    Optional<Product> findProductWithGivenId(Long productId);

//    List<Product> findByTitleContainsIgnoreCase(String title);

    Page<Product> findByTitleContainsIgnoreCase(String title, Pageable pageable);

    List<Product> findByPriceBetween(Double priceAfter, Double priceBefore);

    List<Product> findByCategory(Category category);

    List<Product> findAllByCategory_Id(Long categoryId);

    List<Product> findAllByCategory_Title(String categoryTitle);

//    @Query("FROM com.scaler.ecomm_productservice.models.Product AS p WHERE p.id = :id")
//    Product findProductWithGivenId(Long id);

    Product save(Product product);

    void deleteById(Long productId);
}
