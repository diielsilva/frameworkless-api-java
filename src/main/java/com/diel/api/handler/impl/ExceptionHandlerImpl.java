package com.diel.api.handler.impl;

import com.diel.api.handler.ExceptionHandler;
import com.diel.common.dtos.standard.ExceptionDetails;
import com.diel.common.dtos.standard.ResponseEntity;
import com.diel.domain.enums.HttpStatus;
import com.diel.domain.exceptions.HttpMethodNotSupportedException;
import com.diel.domain.exceptions.InvalidRequestException;
import com.diel.domain.exceptions.ModelNotFoundException;

public class ExceptionHandlerImpl implements ExceptionHandler<ExceptionDetails> {

    @Override
    public ResponseEntity<ExceptionDetails> modelNotFoundException(ModelNotFoundException exception) {
        ExceptionDetails details = new ExceptionDetails(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND, details);
    }

    @Override
    public ResponseEntity<ExceptionDetails> httpMethodNotSupported(HttpMethodNotSupportedException exception) {
        ExceptionDetails details = new ExceptionDetails(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED, details);
    }

    @Override
    public ResponseEntity<ExceptionDetails> invalidRequestException(InvalidRequestException exception) {
        ExceptionDetails details = new ExceptionDetails(exception.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST, details);
    }
}
