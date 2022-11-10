package com.diel.domain.exceptions;

public class EnumConvertException extends RuntimeException {
    private final String message;

    public EnumConvertException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
