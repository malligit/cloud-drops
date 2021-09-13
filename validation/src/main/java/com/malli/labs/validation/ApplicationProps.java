package com.malli.labs.validation;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.malli.labs.validation.dto.Provider;

@Component
@ConfigurationProperties("application")
public class ApplicationProps {
	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		
		this.providers = providers;
	}

	private List<Provider> providers = new ArrayList<>();

}
