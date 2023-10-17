package com.example.wheather.service;

import com.example.wheather.service.model.WeatherRequestModel;
import com.example.wheather.service.model.WeatherResponseModel;

public interface WeatherService {

	WeatherResponseModel getCurrentWeather(WeatherRequestModel model);

}
