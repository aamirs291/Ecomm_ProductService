package com.scaler.ecomm_productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExceptionDTO {
    private Long id;
    private String message;
    private String resolutionDetails;
}
