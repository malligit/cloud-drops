package com.malli.labs.controllers;

import com.malli.labs.models.DataModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class TestRestController {

    @GetMapping("/get-user")
    public List<DataModel> getUser() {
        System.out.println("get-user API called");
        List<String> skills = Arrays.asList("Java","PCF","Database");
        DataModel user =  new DataModel("ID001","Malli",new Date(),"Chicago","M",40,skills);
        List users = new ArrayList<DataModel>();
        users.add(user);
        users.add(new DataModel("ID005","Malli5",new Date(),"Chicago","M",40,skills));

        return users;
    }

    @GetMapping("/msg")
    public String getMsg() {
        return "Malli";
    }
}
