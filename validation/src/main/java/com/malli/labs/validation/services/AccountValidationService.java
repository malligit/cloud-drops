package com.malli.labs.validation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.malli.labs.validation.ApplicationProps;
import com.malli.labs.validation.dto.Provider;
import com.malli.labs.validation.dto.ProviderRequest;
import com.malli.labs.validation.dto.ProviderResponse;
import com.malli.labs.validation.dto.ValidationServiceRequest;
import com.malli.labs.validation.dto.ValidationServiceResponse;


@Service
public class AccountValidationService {

	@Value("${provider.urls: test}")
	private String providerURLs;
	
	
	@Autowired(required=true)
	private RestTemplate restTemplate;
	
	@Autowired
	ApplicationProps props;
	
	private static List<ValidationServiceResponse> responses;
	private static List<ProviderResponse> providerResponse;
	
	public ValidationServiceResponse validateAccounts(ValidationServiceRequest account) {

		//List<String> urls = providerURLs;
		
		if(account.getProviders().isEmpty() ) {
			account.setProviders(props.getProviders());
		}
		account.getProviders().stream().forEach(p -> validateAccount(p,account.getAcountNumber()));
		ValidationServiceResponse validationServiceResponse = new ValidationServiceResponse();
		validationServiceResponse.setResult(providerResponse);
		
		System.out.println(""+props.getProviders());
		return null;
	}
	private ProviderResponse validateAccount(Provider provider,long accNum) {
		
		ProviderRequest request = new ProviderRequest();
		request.setAccountNumber(accNum);
			ResponseEntity<ProviderResponse> res = restTemplate.postForEntity(provider.getUrl(), request, ProviderResponse.class);
			ProviderResponse presponse = res.getBody();
			presponse.setProviderName(provider.getName());
			providerResponse.add(presponse);
		
		return presponse;
	}
}
