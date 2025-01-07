package com.app.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/microservice2/home")
    @ResponseStatus(HttpStatus.OK)
    String home() {
        return "Hello World from microservice 2";
    }
}
