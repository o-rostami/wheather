package com.example.weather.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

	private String main;
	private String name;

	private String description;

	private Double temp;

	private Double feelsLike;

	private Double windSpeed;

}