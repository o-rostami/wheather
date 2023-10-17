package com.weather.service;

import java.util.List;

import com.weather.srvice.model.*;


public interface WeatherService {

	WeatherResponseModel getCurrentweather(WeatherRequestModel model);

}
