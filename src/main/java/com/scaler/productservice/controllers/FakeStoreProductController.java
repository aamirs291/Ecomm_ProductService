package com.scaler.productservice.controllers;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fakestoreproducts")
public class FakeStoreProductController {

    private ProductService fakePracticeService;

    public FakeStoreProductController(ProductService fakePracticeService) {
        this.fakePracticeService = fakePracticeService;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return fakePracticeService.getSingleProduct(id);
    }
    @GetMapping()
    public List<Product> getAllProducts(){
        return fakePracticeService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return new Product();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        return null;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@RequestBody Product product){
        return null;
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@RequestBody Product product, @PathVariable("id") Long id){
        return null;
    }


}
