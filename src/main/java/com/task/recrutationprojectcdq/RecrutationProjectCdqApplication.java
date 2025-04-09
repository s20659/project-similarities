package com.task.recrutationprojectcdq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RecrutationProjectCdqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecrutationProjectCdqApplication.class, args);
    }

}
