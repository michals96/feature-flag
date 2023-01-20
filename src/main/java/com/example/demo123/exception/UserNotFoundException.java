package com.example.demo123.exception;

public class UserNotFoundException extends AbstractNotFoundException {
    public UserNotFoundException(String username) {
        super(String.format("User with username: %s not found", username));
    }
}
