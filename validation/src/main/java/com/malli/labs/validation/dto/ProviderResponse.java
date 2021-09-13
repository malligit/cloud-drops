package com.malli.labs.validation.dto;

import lombok.Data;

@Data
public class ProviderResponse {
	private String providerName;
	private boolean isValid;
	
	public ProviderResponse(String providerName, boolean isValid) {
		this.setProviderName(providerName);
		this.setValid(isValid);
		
	}

}
