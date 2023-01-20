package com.example.demo123.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ValidationErrorControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    ResponseEntity<ApiErrorResponse> handleValidationException(ValidationException ex) {
        List<String> errors = ex.getErrors().stream().map(Validations.ValidationError::message).toList();

        return ResponseEntity.badRequest().body(new ApiErrorResponse(errors));
    }

    public record ApiErrorResponse(List<String> errors) {
    }
}
