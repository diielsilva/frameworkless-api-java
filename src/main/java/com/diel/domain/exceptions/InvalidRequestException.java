package com.diel.domain.exceptions;

public class InvalidRequestException extends RuntimeException {
    private final String message;

    public InvalidRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
