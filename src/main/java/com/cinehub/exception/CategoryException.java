package com.cinehub.exception;

public class CategoryException extends RuntimeException {
    // Constructors for different exception scenarios
    public CategoryException(String message) {
        super(message);
    }

    // Exception for not found category by ID
    public CategoryException(Long id) {
        super("Category not found with id: " + id);
    }

    // Constructor with message and cause
    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
