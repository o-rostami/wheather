package com.example.wheather.client;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "open-weather")
public class OpenWeatherClientProperties {


	private String apikey;

}
