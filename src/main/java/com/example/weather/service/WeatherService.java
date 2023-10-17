package com.example.weather.service;

import com.example.weather.service.model.WeatherRequestModel;
import com.example.weather.service.model.WeatherResponseModel;

public interface WeatherService {

	WeatherResponseModel getCurrentWeather(WeatherRequestModel model);

}
