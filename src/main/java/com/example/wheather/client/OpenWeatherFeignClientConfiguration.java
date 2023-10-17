package com.weather.client;

import lombok.RequiredArgsConstructor;
import feign.RequestInterceptor;


@RequiredArgsConstructor
public class OpenWeatherFeignClientConfiguration {

	private final OpenWeatherProperties openWeatherProperties;


	@Bean
	public RequestInterceptor apiKeyRequestInterceptor() {
		return new ApiKeyRequestInterceptor(openWeatherProperties.getApikey());
	}

}
