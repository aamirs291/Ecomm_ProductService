package com.scaler.productservice.controllers;

import com.scaler.productservice.exceptions.CategoryNotFoundException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final RestTemplate restTemplate;

    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {

        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        //return
//        Product product = null;
//        ResponseEntity<Product> responseEntity = null;
//        try{
//            product = productService.getSingleProduct(id);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            e.printStackTrace();
////            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }catch(ProductNotFoundException e){
//            e.printStackTrace();
//            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return responseEntity;
//        //return productService.getSingleProduct(id);
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        //return new ArrayList<>();
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) throws CategoryNotFoundException {
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ProductNotFoundExceptionDTO> ProductNotFoundExceptionHandler(ProductNotFoundException e){
//        ProductNotFoundExceptionDTO productNotFoundExceptionDTO = new ProductNotFoundExceptionDTO();
//        productNotFoundExceptionDTO.setId(e.getId());
////        System.out.println(e.getId());
//        productNotFoundExceptionDTO.setMessage("Product Not Found");
//        productNotFoundExceptionDTO.setResolutionDetails("Add the product first and then search");
//
//        return new ResponseEntity<>(productNotFoundExceptionDTO, HttpStatus.UNAUTHORIZED);
//    }

}
