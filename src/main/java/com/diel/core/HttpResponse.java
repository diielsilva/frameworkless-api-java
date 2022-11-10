package com.diel.core;

import com.diel.domain.enums.HttpStatus;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface HttpResponse {
    void generateResponse(HttpExchange exchange, String response, HttpStatus status) throws IOException;
}
