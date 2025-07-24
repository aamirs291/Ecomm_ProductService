package com.scaler.productservice.controlleradvices;

import com.scaler.productservice.dtos.ProductNotFoundExceptionDTO;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ExceptionDTO> RuntimeExceptionHandler(){
//        ExceptionDTO exceptionDTO = new ExceptionDTO();
//        exceptionDTO.setMessage("Runtime Exception");
//        exceptionDTO.setResolutionDetails("Fix your code");
//        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDTO> ProductNotFoundExceptionHandler(ProductNotFoundException e){
        ProductNotFoundExceptionDTO productNotFoundExceptionDTO = new ProductNotFoundExceptionDTO();
        productNotFoundExceptionDTO.setId(e.getId());
//        System.out.println(e.getId());
        productNotFoundExceptionDTO.setMessage("Product Not Found");
        productNotFoundExceptionDTO.setResolutionDetails("Add the product first and then search");

        return new ResponseEntity<>(productNotFoundExceptionDTO, HttpStatus.UNAUTHORIZED);
    }
}
