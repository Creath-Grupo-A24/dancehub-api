package br.com.dancehub.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DanceHubApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanceHubApiApplication.class, args);
    }

}
