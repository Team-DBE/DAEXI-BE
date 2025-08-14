package com.example.daexi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DaexiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaexiApplication.class, args);
    }

}
