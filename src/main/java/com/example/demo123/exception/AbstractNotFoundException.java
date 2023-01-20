package com.example.demo123.exception;

public abstract class AbstractNotFoundException extends RuntimeException {
  public AbstractNotFoundException(String message) {
    super(message);
  }
}
