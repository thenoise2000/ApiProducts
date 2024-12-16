package com.zara.catalog.domain.modelRules.apiResponse;

public class CategoryExceptions extends RuntimeException {

    public CategoryExceptions(String message) {
        super(message);
    }

    public CategoryExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
