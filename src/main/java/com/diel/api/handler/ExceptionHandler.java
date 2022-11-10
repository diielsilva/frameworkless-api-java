package com.diel.api.handler;

import com.diel.common.dtos.standard.ResponseEntity;
import com.diel.domain.exceptions.HttpMethodNotSupportedException;
import com.diel.domain.exceptions.InvalidRequestException;
import com.diel.domain.exceptions.ModelNotFoundException;

public interface ExceptionHandler<T> {
    ResponseEntity<T> modelNotFoundException(ModelNotFoundException exception);

    ResponseEntity<T> httpMethodNotSupported(HttpMethodNotSupportedException exception);

    ResponseEntity<T> invalidRequestException(InvalidRequestException exception);
}
