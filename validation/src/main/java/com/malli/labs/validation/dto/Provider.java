package com.malli.labs.validation.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Provider {
	private String name;
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Provider(String name, String url) {
		this.setName(name);
		this.setUrl(url);
	}

}
