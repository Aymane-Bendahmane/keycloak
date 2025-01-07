package com.app.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class MyController {

    private final WebClient webClient = WebClient.builder().build();

    @GetMapping("/microservice1/home")
    @ResponseStatus(HttpStatus.OK)
    String home() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String response = webClient.get()
                .uri("http://localhost:8083/microservice2/home")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return "Hello World from microservice 1 " + response;
    }
}
