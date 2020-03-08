package com.malli.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.malli.labs.services.UserService;
import com.malli.labs.models.DataModel;
import reactor.core.publisher.Flux;

@RestController
public class MasterController {


    @Autowired
    private UserService userService;


    @GetMapping("/try-me")
    public String testMethod() {
        return "Hello World";
    }

    @GetMapping("/user")
    public DataModel getUserById(String id) {
        Flux<DataModel> flux = userService.callUserRestAPI();
        DataModel user = flux.blockFirst();
        if (user.getId().equals(id)) {
            return user;
        }
        return null;
    }
}
