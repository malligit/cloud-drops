package com.malli.labs.validation.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.malli.labs.validation.ApplicationProps;
import com.malli.labs.validation.dto.ValidationServiceRequest;
import com.malli.labs.validation.dto.ValidationServiceResponse;
import com.malli.labs.validation.services.AccountValidationService;


@RestController
public class Controller {

	@Autowired
	private AccountValidationService accountValidationService;
	
	@Autowired
	ApplicationProps props;
	
	@GetMapping("/test")
	public @ResponseBody String getSampleData() throws Exception {
		System.out.println("providers : "+ props.getProviders());
		return "Hello World";
	}
	
	@PostMapping("/validate")
	public ResponseEntity<List<ValidationServiceResponse>> validateAccounts(@RequestBody List<ValidationServiceRequest> accounts) {
	
		List<ValidationServiceResponse> lst = new ArrayList();
		
		lst = accountValidationService.validateAccounts(accounts);
		
		ValidationServiceResponse response =  new ValidationServiceResponse();
		
		return new ResponseEntity<List<ValidationServiceResponse>>(lst,HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<List<ValidationServiceResponse>> updateAccounts(@RequestBody List<ValidationServiceRequest> accounts) {
	
		List<ValidationServiceResponse> lst = new ArrayList();
		
		lst = accountValidationService.validateAccounts(accounts);
		
		ValidationServiceResponse response =  new ValidationServiceResponse();
		
		return new ResponseEntity<List<ValidationServiceResponse>>(lst,HttpStatus.OK);
	}
	
}
