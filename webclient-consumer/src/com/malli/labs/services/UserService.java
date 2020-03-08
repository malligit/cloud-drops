package com.malli.labs.services;

import com.malli.labs.models.DataModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class UserService {
    public UserService() {
    }

    public Flux<DataModel> callUserRestAPI() {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8888").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

        return webClient.get().uri("/get-user").retrieve().bodyToFlux(DataModel.class);

    }
}
