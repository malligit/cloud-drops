package com.malli.labs.validation.dto;

import java.util.List;

import lombok.Data;

@Data
public class ValidationServiceRequest {

	private long acountNumber;	
	private List<Provider> providers;
}
