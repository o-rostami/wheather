package com.weather.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import com.weather.srvice.model.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {


	private final OpenWeatherClient openWeatherClient;

	private final Mapper mapper;

	@Override
	public WeatherResponseModel getCurrentweather(WeatherRequestModel model) {
		return mapper.toWeatherResponseModel(openWeatherClient.getCurrentWeather(model.getLan(), model.getLon()));

	}

}
