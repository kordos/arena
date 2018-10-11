package com.example.Arena.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping("/hello")
    String helloWorld() {
        return "Hello world!!!";
    }

    @RequestMapping("/exception")
    void testException() throws WrongRequestException {
        throw new WrongRequestException("Wrong request data!");
    }
}
