package com.example.weather.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;


public class ApiKeyRequestInterceptor implements RequestInterceptor {

	private final String apiKey;

	public ApiKeyRequestInterceptor(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.query( "appid", apiKey);
	}
}