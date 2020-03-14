package com.malli.labs.controllers;

import com.malli.labs.models.DataModel;
import com.malli.labs.jpa.UserRepository;
import com.malli.labs.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class TestRestController {
    @Autowired
    private UserRepository userRepository;
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

    @GetMapping("/db-all-users")
    public @ResponseBody Iterable<User> getAllDBUsers() {
              return userRepository.findAll();
    }

    @PostMapping("/add-db-user")
    public User addDBUser(@RequestBody User user) {
        userRepository.save(user);
        System.out.println("User Added succesfully");

        return user;
    }

    @GetMapping("/msg")
    public String getMsg() {
        return "Malli";
    }

}
