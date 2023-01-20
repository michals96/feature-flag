package com.example.demo123.exception;

public class FeatureNotFoundException extends AbstractNotFoundException {
    public FeatureNotFoundException(String featureName) {
        super(String.format("Cannot find feature named: %s", featureName));
    }
}
