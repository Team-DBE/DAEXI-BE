package com.example.daexi;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class DaexiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaexiApplication.class, args);
    }

}
