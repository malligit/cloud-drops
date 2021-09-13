package com.malli.labs.validation.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.malli.labs.validation.ApplicationProps;
import com.malli.labs.validation.dto.Provider;
import com.malli.labs.validation.dto.ProviderResponse;
import com.malli.labs.validation.dto.ValidationServiceRequest;
import com.malli.labs.validation.dto.ValidationServiceResponse;

@SpringBootTest@ActiveProfiles("test")
@RunWith(SpringRunner.class)
//@SpringApplicationConfiguration(classes = ValidationApplication.class)
public class AccountValidationServiceTest {
	
	@Autowired
	AccountValidationService accountValidationService;

	@Autowired
	ApplicationProps props;
	
	@SuppressWarnings("deprecation")
	@Test
	public void validateAccountsWithProviders() {	
		ValidationServiceRequest req = new ValidationServiceRequest();
		req.setAcountNumber(1234);
		List<Provider> providers = new ArrayList<>();
		providers.add(new Provider("provider1","http://provider1/validate"));
		providers.add(new Provider("provider2","http://provider2/validate"));
		req.setProviders(providers);
		
		ValidationServiceResponse res = new ValidationServiceResponse();
		List<ProviderResponse> resList = new ArrayList<ProviderResponse>();
		
		resList.add(new ProviderResponse("provider1", true));
		resList.add(new ProviderResponse("provider2", false));
		res.setResult(resList);
	    Mockito.when(accountValidationService.validateAccounts(req)).thenReturn(res);
	    ValidationServiceResponse res1 = accountValidationService.validateAccounts(req);
	    
	    Assert.assertEquals(res.getResult().size(), res1.getResult().size());
	
	}
	
	@Test
	public void validateAccountsWithOutProviders() {
		ValidationServiceRequest req = new ValidationServiceRequest();
		req.setAcountNumber(1234);
		
		req.setProviders(props.getProviders());
		
		ValidationServiceResponse res = new ValidationServiceResponse();
		List<ProviderResponse> resList = new ArrayList<ProviderResponse>();
		
		resList.add(new ProviderResponse("provider1", true));
		resList.add(new ProviderResponse("provider2", false));
		res.setResult(resList);
	    Mockito.when(accountValidationService.validateAccounts(req)).thenReturn(res);
	    ValidationServiceResponse res1 = accountValidationService.validateAccounts(req);
	    
	    Assert.assertEquals(res.getResult().get(0).getProviderName(), res1.getResult().get(0).getProviderName());
	}

}
