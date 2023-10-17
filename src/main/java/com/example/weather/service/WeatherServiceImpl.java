package com.example.weather.service;

import com.example.weather.client.OpenWeatherClient;
import com.example.weather.service.Mapper.WeatherServiceMapper;
import com.example.weather.service.model.WeatherRequestModel;
import com.example.weather.service.model.WeatherResponseModel;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

	private final OpenWeatherClient openWeatherClient;

	private final WeatherServiceMapper mapper;

	@Override
	public WeatherResponseModel getCurrentWeather(WeatherRequestModel model) {
		return mapper.toWeatherResponseModel(openWeatherClient.getCurrentWeather(model.getLat(), model.getLon()));
	}

}
