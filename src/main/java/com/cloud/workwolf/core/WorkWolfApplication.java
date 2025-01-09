package com.cloud.workwolf.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.cloud.workwolf.core" })
public class WorkWolfApplication {

    public static void main(String[] args) {

        SpringApplication.run(WorkWolfApplication.class, args);
    }

}
