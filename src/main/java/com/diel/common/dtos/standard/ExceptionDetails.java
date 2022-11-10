package com.diel.common.dtos.standard;

public class ExceptionDetails {

    private final String message;

    public ExceptionDetails(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
