package com.malli.labs.validation.dto;

import java.util.List;

import lombok.Data;

@Data
public class ValidationServiceResponse {
	private List<ProviderResponse> result;

}
