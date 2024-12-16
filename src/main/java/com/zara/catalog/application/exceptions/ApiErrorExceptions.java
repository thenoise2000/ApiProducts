package com.zara.catalog.application.exceptions;

import org.springframework.http.HttpHeaders;
import com.zara.catalog.domain.modelRules.apiResponse.ProductExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiErrorExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProductExceptions.class})
    public ResponseEntity<Object> handleNotFoundException(
            Exception e,
            WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = (e instanceof ProductExceptions) ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(e, null, headers, status, request);
    }
}
