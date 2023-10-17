package com.digipay.merchantcredit.iranvenezuela.config;

import feign.auth.BasicAuthRequestInterceptor;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class IranVenezuelaFeignClientConfiguration {

	private final IranVenezuelaProperties iranVenezuelaProperties;

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(iranVenezuelaProperties.getApiUsername(),
				iranVenezuelaProperties.getApiPassword());
	}

}
