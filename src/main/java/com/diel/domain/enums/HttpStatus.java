package com.diel.domain.enums;

import com.diel.domain.exceptions.EnumConvertException;

public enum HttpStatus {
    CREATED(201),
    OK(200),
    NOT_FOUND(404),
    NO_CONTENT(204),
    CONFLICT(409),
    BAD_REQUEST(400),
    METHOD_NOT_ALLOWED(405),
    SERVER_ERROR(500);
    public final Integer status;

    HttpStatus(Integer status) {
        this.status = status;
    }

    public static HttpStatus toEnum(Integer status) {
        switch (status) {
            case 200:
                return OK;
            case 201:
                return CREATED;
            case 204:
                return NO_CONTENT;
            case 400:
                return BAD_REQUEST;
            case 404:
                return NOT_FOUND;
            case 405:
                return METHOD_NOT_ALLOWED;
            case 409:
                return CONFLICT;
            case 500:
                return SERVER_ERROR;
            default:
                throw new EnumConvertException("Invalid enum received");
        }
    }
}
