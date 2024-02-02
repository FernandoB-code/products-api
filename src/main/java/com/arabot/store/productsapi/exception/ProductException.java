package com.arabot.store.productsapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

    public ProductException(HttpStatus httpStatus, String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ProductException(String message, String errorCode, HttpStatus httpStatus ,Throwable ex)
    {
        super(message, ex);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;

    }

}
