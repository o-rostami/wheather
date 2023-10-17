package com.example.wheather.service;

import com.example.wheather.service.model.WeatherRequestModel;

public interface WeatherService {

	WeatherRequestModel getCurrentWeather(WeatherRequestModel model);

}
