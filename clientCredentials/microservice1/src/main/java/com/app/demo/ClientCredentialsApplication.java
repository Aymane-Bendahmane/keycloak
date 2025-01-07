package com.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class ClientCredentialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientCredentialsApplication.class, args);
    }

}
