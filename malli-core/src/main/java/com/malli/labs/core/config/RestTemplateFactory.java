package com.malli.labs.core.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateFactory {

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HttpHeaderInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HttpHeaderInterceptor("ContentType", MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HttpHeaderInterceptor("username", "user123"));
        interceptors.add(new HttpHeaderInterceptor("customHeader1", "c1"));
        interceptors.add(new HttpHeaderInterceptor("customHeader2", "c2"));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}
