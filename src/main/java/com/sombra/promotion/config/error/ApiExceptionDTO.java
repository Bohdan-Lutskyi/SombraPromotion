package com.sombra.promotion.config.error;

import lombok.Data;

@Data
public class ApiExceptionDTO {
    private String message;
    private String code;

}
