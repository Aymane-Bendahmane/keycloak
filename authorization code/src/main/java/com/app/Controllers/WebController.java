package com.app.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class WebController {
    @GetMapping("/hello")
    public String showMessages() {
        return "Hello World";
    }
}
