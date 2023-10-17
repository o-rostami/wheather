package com.example.weather.api.mapper;

import com.example.weather.api.model.WeatherResponse;
import com.example.weather.service.model.WeatherRequestModel;
import com.example.weather.service.model.WeatherResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherResourceMapper {

	WeatherRequestModel toWeatherRequestModel(Double lat, Double lon);

	WeatherResponse toWeatherResponse(WeatherResponseModel result);
}