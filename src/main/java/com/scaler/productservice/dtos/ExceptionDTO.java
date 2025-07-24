package com.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {
    private String message;
    private String resolutionDetails;
}
