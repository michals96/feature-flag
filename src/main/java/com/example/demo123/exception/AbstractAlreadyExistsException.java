package com.example.demo123.exception;

public class AbstractAlreadyExistsException extends RuntimeException {
    public AbstractAlreadyExistsException(final String message) {
        super(message);
    }
}
