package com.example.Arena.controller;


import com.example.Arena.BodyPart;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {

        String error = "Malformed JSON request";
        ArenaApiError arenaApiError = new ArenaApiError(HttpStatus.BAD_REQUEST, error, ex.getMessage());
        return new ResponseEntity<>(arenaApiError, arenaApiError.getHttpStatus());
    }

    @ExceptionHandler(WrongRequestException.class)
    protected ResponseEntity<Object> handleWrongRequestException(WrongRequestException ex) {

        ArenaApiError arenaApiError = new ArenaApiError(HttpStatus.BAD_REQUEST, "Wrong request!", ex.getMessage());
        return new ResponseEntity<>(arenaApiError, arenaApiError.getHttpStatus());
    }
}