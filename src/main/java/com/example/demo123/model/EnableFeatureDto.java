package com.example.demo123.model;

import lombok.Builder;

import static com.example.demo123.validation.Validations.*;

@Builder
public record EnableFeatureDto(String featureName, String userName) {
    public EnableFeatureDto {
                validateAll(
                () -> notBlank(featureName, "featureName cannot be blank"),
                () -> notNull(featureName, "featureName cannot be null"),
                () -> notNull(userName, "userName cannot be null"),
                () -> notBlank(userName, "userName cannot be null")
        );
    }
}
