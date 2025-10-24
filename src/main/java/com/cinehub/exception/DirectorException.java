package com.cinehub.exception;

public class DirectorException extends RuntimeException {
    // Constructor with message
    public DirectorException(String message) {
        super(message);
    }

    // Constructor with director ID
    public DirectorException(Long id) {
        super("Director not found with id: " + id);
    }

    // Constructor with message and cause
    public DirectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
