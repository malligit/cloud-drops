package com.malli.labs.crdb.controllers;


import com.malli.labs.crdb.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class ExternalController {

    @Autowired
    GenericService genericService;

    @GetMapping("/news")
    public Object articles(){
        return genericService.getNews();
    }
}
