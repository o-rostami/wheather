package com.example.wheather.client;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;


@RequiredArgsConstructor
public class OpenWeatherFeignClientConfiguration {

	private final com.weather.client.OpenWeatherProperties openWeatherProperties;


	@Bean
	public RequestInterceptor apiKeyRequestInterceptor() {
		return new ApiKeyRequestInterceptor(openWeatherProperties.getApikey());
	}

}
