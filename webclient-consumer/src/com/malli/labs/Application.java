package com.malli.labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.malli.labs.services.UserService;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static  void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

}
