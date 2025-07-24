package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Category;
//import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String name);


//    List<Product> findProductsByCategory(Category category);
}
