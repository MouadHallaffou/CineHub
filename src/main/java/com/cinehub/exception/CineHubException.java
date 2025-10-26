package com.cinehub.exception;

public class CineHubException extends RuntimeException {
    public CineHubException(String message) {
        super(message);
    }

    public CineHubException(String message, Throwable cause) {
        super(message, cause);
    }
}