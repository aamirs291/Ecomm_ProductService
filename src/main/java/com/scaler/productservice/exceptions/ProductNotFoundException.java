package com.scaler.ecomm_productservice.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception {

    private Long id;

    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found");
        this.id = id;
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
