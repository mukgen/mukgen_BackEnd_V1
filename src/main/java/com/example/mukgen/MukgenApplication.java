package com.example.mukgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MukgenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MukgenApplication.class, args);
    }

}
