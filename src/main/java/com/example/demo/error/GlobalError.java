package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalError {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> getError(Exception ex, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(),
                LocalDateTime.now(),request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
