package com.demo.spring_demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}


// The GlobalExceptionHandler class provides a centralized way to handle exceptions
// thrown by any controller in the Spring Boot application. It uses the @RestControllerAdvice
// annotation to indicate that it will provide exception handling for REST controllers.
// The handleRuntime method is annotated with @ExceptionHandler(RuntimeException.class),
// meaning it will handle all RuntimeExceptions thrown in the application. When such an
// exception occurs, this method constructs a ResponseEntity with a 400 Bad Request
// status and includes the exception message in the response body. This allows for consistent
// error handling and messaging across the application.