package com.example.demo123.model;

import lombok.Builder;

import static com.example.demo123.validation.Validations.*;

@Builder
public record CreateFeatureDto(String name) {
    public CreateFeatureDto {
        validateAll(
                () -> notBlank(name, "name cannot be blank"),
                () -> notNull(name, "name cannot be null")
        );
    }
}
