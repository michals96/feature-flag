package com.example.demo123.exception;

public class FeatureAlreadyExistsException extends AbstractAlreadyExistsException {
    public FeatureAlreadyExistsException(final String featureName) {
        super(String.format("Feature named %s already exists", featureName));
    }
}
