package com.example.weather.client;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;


@RequiredArgsConstructor
public class OpenWeatherClientConfiguration {

	private final OpenWeatherClientProperties openWeatherClientProperties;


	@Bean
	public RequestInterceptor apiKeyRequestInterceptor() {
		return new ApiKeyRequestInterceptor(openWeatherClientProperties.getApikey());
	}

}
