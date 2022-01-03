package com.malli.labs.crdb.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenericServiceTest {

    @Autowired
    GenericService genericService;

    @Test
    void getNewsTest() {

        Object result = genericService.getNews();
        assertNotNull(result);
    }
}