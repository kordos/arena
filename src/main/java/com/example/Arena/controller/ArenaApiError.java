package com.example.Arena.controller;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ArenaApiError {

    private HttpStatus httpStatus;
    private String message;
    private String debugMessage;
}
