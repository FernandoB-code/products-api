package com.arabot.store.productsapi.handler;

import com.arabot.store.productsapi.dto.ApiError;
import com.arabot.store.productsapi.exception.ProductException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {


        String message = ex.getBindingResult().getFieldErrors().stream()
                    .map(objectError -> objectError.getField() + " " + objectError.getDefaultMessage())
                    .collect(Collectors.joining(" - "));

            return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "", "Validation Failed: " + message));
        }

    @ExceptionHandler(ProductException.class)
    protected ResponseEntity<Object> handleTransactionError(ProductException ex)
    {
        ApiError apiError = new ApiError(ex.getHttpStatus(), ex.getErrorCode(), ex.getMessage(), ex);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "", ex.getMessage(), ex);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInternalError(Exception ex)
    {
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage(), ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError)
    {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
