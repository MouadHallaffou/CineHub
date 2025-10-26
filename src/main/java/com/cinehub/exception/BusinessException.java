package com.cinehub.exception;

public class BusinessException extends CineHubException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}