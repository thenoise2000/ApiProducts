package com.zara.catalog.domain.modelRules.apiResponse;

public class ProductExceptions extends RuntimeException {

    public ProductExceptions(String message) {
        super(message);
    }

    public ProductExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
