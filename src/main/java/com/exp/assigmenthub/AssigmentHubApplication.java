package com.exp.assigmenthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AssigmentHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssigmentHubApplication.class, args);
    }

}
