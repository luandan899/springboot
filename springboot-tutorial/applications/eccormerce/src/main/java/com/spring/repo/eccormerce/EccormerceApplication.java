package com.spring.repo.eccormerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.spring.repo.common.cqrs.*", "com.spring.repo.eccormerce.*"})
public class EccormerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EccormerceApplication.class, args);
    }

}
