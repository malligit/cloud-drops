package com.malli.labs.controllers;

import com.malli.labs.models.DataModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class TestRestController {

    @GetMapping("/get-user")
    public DataModel getUser() {
        System.out.println("get-user method------------------------");
        List<String> skills = Arrays.asList("Java","PCF","Database");
        DataModel user =  new DataModel("ID001","Malli",new Date(),"Chicago","M",40,skills);
        return user;
    }
}
