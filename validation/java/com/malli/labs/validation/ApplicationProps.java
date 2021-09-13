package com.malli.labs.validation;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "providers")
public class ApplicationProps {
	public List<Object> getProviders() {
		return providers;
	}

	public void setProviders(List<Object> providers) {
		
		this.providers = providers;
	}

	private List<Object> providers;

}
