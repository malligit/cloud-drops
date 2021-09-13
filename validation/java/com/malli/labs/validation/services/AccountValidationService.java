package com.malli.labs.validation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.malli.labs.validation.ApplicationProps;
import com.malli.labs.validation.dto.ValidationServiceRequest;
import com.malli.labs.validation.dto.ValidationServiceResponse;

@Service
@EnableConfigurationProperties(value=ApplicationProps.class)
public class AccountValidationService {

	@Value("${provider.urls: test}")
	private String providerURLs;
	
	
	@Autowired(required=true)
	private RestTemplate restTemplate;
	
	@Autowired
	ApplicationProps props;
	
	public List<ValidationServiceResponse> validateAccounts(List<ValidationServiceRequest> accounts) {

		//List<String> urls = providerURLs;
		System.out.println(""+props.getProviders());
		return null;
	}
}
