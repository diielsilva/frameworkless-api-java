package com.diel.domain.exceptions;

public class HttpMethodNotSupportedException extends RuntimeException {
    private final String message;

    public HttpMethodNotSupportedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
