package com.sparta.vicky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<RestApiException> handleException(IllegalArgumentException e) {

        RestApiException restApiException = RestApiException.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .msg(e.getMessage())
                .build();

        return new ResponseEntity<>(restApiException, HttpStatus.BAD_REQUEST);
    }


}
