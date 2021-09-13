package com.malli.labs.validation.services;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ValidationServiceTestConfiguration {
    @Bean
    @Primary
    public AccountValidationService nameService() {
        return Mockito.mock(AccountValidationService.class);
    }
}
