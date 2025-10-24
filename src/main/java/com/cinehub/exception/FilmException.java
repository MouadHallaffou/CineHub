package com.cinehub.exception;

public class FilmException extends RuntimeException {
    public FilmException(String message) {
        super(message);
    }

    public FilmException(Long id) {
        super("Film not found with id: " + id);
    }

    public FilmException(String message, Throwable cause) {
        super(message, cause);
    }
}
