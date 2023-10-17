package com.weather.client;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "open-weather")
public class OpenWeatherProperties {


	private String apikey;

}
