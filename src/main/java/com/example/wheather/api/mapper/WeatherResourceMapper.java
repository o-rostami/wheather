package com.example.wheather.api.mapper;

import com.example.wheather.service.model.WeatherRequestModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherResourceMapper {

	WeatherRequestModel toWeatherRequestModel(Double lat, Double lon);

	com.weather.api.model.WeatherResponse toWeatherResponse(WeatherRequestModel result);
}