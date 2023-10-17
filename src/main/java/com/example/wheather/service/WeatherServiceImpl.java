package com.example.wheather.service;

import com.example.wheather.client.OpenWeatherClient;
import com.example.wheather.service.Mapper.WeatherServiceMapper;
import com.example.wheather.service.model.WeatherRequestModel;
import com.example.wheather.service.model.WeatherResponseModel;
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
