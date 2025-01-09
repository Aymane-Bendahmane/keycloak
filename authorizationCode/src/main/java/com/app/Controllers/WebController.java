package com.app.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/hello")
    public String showMessages() {
        return "Hello World";
    }
    @GetMapping
    public String getHome() {
        return "Home Page";
    }
}
