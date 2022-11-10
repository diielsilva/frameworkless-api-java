package com.diel.domain.exceptions;

public class ModelNotFoundException extends RuntimeException {
    private final String message;

    public ModelNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
