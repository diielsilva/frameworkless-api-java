package com.diel;

import com.diel.api.controller.GenericController;
import com.diel.api.controller.impl.EmployeeController;
import com.diel.api.handler.ExceptionHandler;
import com.diel.api.handler.impl.ExceptionHandlerImpl;
import com.diel.common.dtos.employee.DisplayEmployee;
import com.diel.common.dtos.employee.SaveEmployee;
import com.diel.common.dtos.standard.ExceptionDetails;
import com.diel.common.dtos.standard.ResponseEntity;
import com.diel.common.mappers.ModelMapper;
import com.diel.common.mappers.impl.ModelMapperImpl;
import com.diel.core.HttpResponse;
import com.diel.core.impl.HttpResponseImpl;
import com.diel.domain.exceptions.HttpMethodNotSupportedException;
import com.diel.domain.exceptions.InvalidRequestException;
import com.diel.domain.exceptions.ModelNotFoundException;
import com.diel.domain.models.Employee;
import com.diel.domain.repositories.GenericRepository;
import com.diel.domain.repositories.impl.EmployeeRepository;
import com.diel.domain.services.GenericService;
import com.diel.domain.services.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper jackson = new ObjectMapper();
        GenericRepository<Employee> repository = new EmployeeRepository();
        ModelMapper modelMapper = new ModelMapperImpl();
        GenericService<DisplayEmployee, SaveEmployee, Employee> service = new EmployeeServiceImpl(repository, modelMapper);
        GenericController<DisplayEmployee, SaveEmployee, Employee> controller = new EmployeeController(service);
        ExceptionHandler<ExceptionDetails> handler = new ExceptionHandlerImpl();
        HttpResponse httpResponse = new HttpResponseImpl();
        final int port = 8080;
        final String api = "/api/employees";
        final HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext(api, exchange -> {
            String[] verifiedUrl = verifyUrl(exchange.getRequestURI().getRawPath());
            String requestMethod = exchange.getRequestMethod();
            String response;
            switch (requestMethod) {
                case "GET":
                    if (haveNotUrlParam(verifiedUrl)) {
                        ResponseEntity<List<DisplayEmployee>> responseEntity = controller.findAll();
                        response = jackson.writeValueAsString(responseEntity);
                        httpResponse.generateResponse(exchange, response, responseEntity.getStatus());
                    } else {
                        try {
                            Long id = Long.parseLong(verifiedUrl[3]);
                            ResponseEntity<DisplayEmployee> responseEntity = controller.findById(id);
                            response = jackson.writeValueAsString(responseEntity);
                            httpResponse.generateResponse(exchange, response, responseEntity.getStatus());
                        } catch (ModelNotFoundException exception) {
                            ResponseEntity<ExceptionDetails> details = handler.modelNotFoundException(exception);
                            response = jackson.writeValueAsString(details);
                            httpResponse.generateResponse(exchange, response, details.getStatus());
                        }
                    }
                    break;
                case "POST":
                    try {
                        InputStream requestBody = exchange.getRequestBody();
                        if (requestBody == null || requestBody.available() == 0) {
                            throw new InvalidRequestException("Missing request body");
                        }
                        SaveEmployee dto = jackson.readValue(requestBody, SaveEmployee.class);
                        ResponseEntity<DisplayEmployee> employee = controller.save(dto);
                        response = jackson.writeValueAsString(employee);
                        httpResponse.generateResponse(exchange, response, employee.getStatus());
                    } catch (InvalidRequestException exception) {
                        ResponseEntity<ExceptionDetails> details = handler.invalidRequestException(exception);
                        response = jackson.writeValueAsString(details);
                        httpResponse.generateResponse(exchange, response, details.getStatus());
                    }
                    break;
                case "PUT":
                    try {
                        if (haveNotUrlParam(verifiedUrl)) {
                            throw new InvalidRequestException("Missing URL param");
                        }
                        InputStream requestBody = exchange.getRequestBody();
                        if (requestBody == null || requestBody.available() == 0) {
                            throw new InvalidRequestException("Missing request body");
                        }
                        Long id = Long.parseLong(verifiedUrl[3]);
                        SaveEmployee dto = jackson.readValue(requestBody, SaveEmployee.class);
                        ResponseEntity<DisplayEmployee> employee = controller.update(id, dto);
                        response = jackson.writeValueAsString(employee);
                        httpResponse.generateResponse(exchange, response, employee.getStatus());
                    } catch (InvalidRequestException exception) {
                        ResponseEntity<ExceptionDetails> details = handler.invalidRequestException(exception);
                        response = jackson.writeValueAsString(details);
                        httpResponse.generateResponse(exchange, response, details.getStatus());
                    } catch (ModelNotFoundException exception) {
                        ResponseEntity<ExceptionDetails> details = handler.modelNotFoundException(exception);
                        response = jackson.writeValueAsString(details);
                        httpResponse.generateResponse(exchange, response, details.getStatus());
                    }
                    break;
                case "DELETE":
                    try {
                        if (haveNotUrlParam(verifiedUrl)) {
                            throw new InvalidRequestException("Missing URL param");
                        }
                        Long id = Long.parseLong(verifiedUrl[3]);
                        ResponseEntity<String> result = controller.delete(id);
                        response = jackson.writeValueAsString(result);
                        httpResponse.generateResponse(exchange, response, result.getStatus());
                    } catch (InvalidRequestException exception) {
                        ResponseEntity<ExceptionDetails> details = handler.invalidRequestException(exception);
                        response = jackson.writeValueAsString(details);
                        httpResponse.generateResponse(exchange, response, details.getStatus());
                    } catch (ModelNotFoundException exception) {
                        ResponseEntity<ExceptionDetails> details = handler.modelNotFoundException(exception);
                        response = jackson.writeValueAsString(details);
                        httpResponse.generateResponse(exchange, response, details.getStatus());
                    }
                    break;
                default:
                    try {
                        throw new HttpMethodNotSupportedException("Http method not supported");
                    } catch (HttpMethodNotSupportedException exception) {
                        ResponseEntity<ExceptionDetails> details = handler.httpMethodNotSupported(exception);
                        response = jackson.writeValueAsString(details);
                        httpResponse.generateResponse(exchange, response, details.getStatus());
                    }
                    break;
            }
        });
        server.start();
    }

    public static boolean haveNotUrlParam(String[] verifiedUrl) {
        return verifiedUrl.length == 3;
    }

    public static String[] verifyUrl(String url) {
        return url.split("/");
    }
}