package com.JobApplication.reviewms.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.JobApplication.reviewms.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiError err = ApiError.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message(exception.getMessage())
                            .build();

        return new  ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
}
