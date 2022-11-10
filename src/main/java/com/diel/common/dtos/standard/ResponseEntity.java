package com.diel.common.dtos.standard;

import com.diel.domain.enums.HttpStatus;

public class ResponseEntity<T> {
    private final HttpStatus status;
    private final T body;


    public ResponseEntity(HttpStatus status, T body) {
        this.status = status;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }


}
