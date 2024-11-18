package com.spring.repo.user;

import com.spring.repo.basemongo.mongojpa.EnableMongoJpa;
import com.spring.repo.kafka.annotation.EnableKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoJpa
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.spring.repo.user.*",
        "com.spring.repo.basemongo.*",
        "com.spring.repo.kafka.*"
})
@EnableMongoRepositories(basePackages = {
        "com.spring.repo.user.repository"
})
@EnableKafka
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

