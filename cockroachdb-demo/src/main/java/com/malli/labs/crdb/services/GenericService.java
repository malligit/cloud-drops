package com.malli.labs.crdb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenericService {

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    public Object getNews(){

        String newsUrl = "https://newsapi.org/v2/everything?q=tesla&sortBy=publishedAt&apiKey=ab71cc3040914008b291b5824be1e935";
        String result = restTemplate.getForObject(newsUrl,String.class);
        return result;
    }
}
