package com.diel.core.impl;

import com.diel.core.HttpResponse;
import com.diel.domain.enums.HttpStatus;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponseImpl implements HttpResponse {
    @Override
    public void generateResponse(HttpExchange exchange, String response, HttpStatus status) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        OutputStream stream = exchange.getResponseBody();
        exchange.sendResponseHeaders(status.status, response.getBytes().length);
        stream.write(response.getBytes());
        stream.flush();
        stream.close();
    }
}
