package com.scaler.ecomm_productservice.repositories;

import com.scaler.ecomm_productservice.models.Category;
//import org.apache.el.stream.Optional;
import com.scaler.ecomm_productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String name);


//    List<Product> findProductsByCategory(Category category);
}
