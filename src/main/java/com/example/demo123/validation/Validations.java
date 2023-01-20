package com.example.demo123.validation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Validations {
    public static void validateAll(Validation... validations) {
        List<ValidationError> errors = Arrays.stream(validations).map(Validation::validate).flatMap(Optional::stream).toList();

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public static Optional<ValidationError> notBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            return Optional.of(new ValidationError(message));
        }
        return Optional.empty();
    }

    public static Optional<ValidationError> notNull(Object value, String message) {
        if (value == null) {
            return Optional.of(new ValidationError(message));
        }
        return Optional.empty();
    }

    public interface Validation {
        Optional<ValidationError> validate();
    }

    public record ValidationError(String message) {
    }
}
