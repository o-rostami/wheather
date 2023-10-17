package com.weather.client;

import feign.RequestInterceptor;
import org.springframework.web.client.RequestTemplate;



public class ApiKeyRequestInterceptor implements RequestInterceptor {

	private final String apiKey;

	public ApiKeyRequestInterceptor(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.header("X-API-KEY", apiKey);
	}
}