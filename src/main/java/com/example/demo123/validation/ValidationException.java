package com.example.demo123.validation;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends IllegalArgumentException {

    private final List<Validations.ValidationError> errors;

    public ValidationException(List<Validations.ValidationError> errors) {
        this.errors = errors;
    }

    public List<Validations.ValidationError> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        return errors.stream().map(Validations.ValidationError::message).collect(Collectors.joining("\n"));
    }
}