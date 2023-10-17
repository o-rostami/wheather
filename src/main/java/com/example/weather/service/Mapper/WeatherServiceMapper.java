package com.example.weather.service.Mapper;

import com.example.weather.api.OpenWeatherClientResponse;
import com.example.weather.service.model.WeatherResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherServiceMapper {

	WeatherResponseModel toWeatherResponseModel(OpenWeatherClientResponse model);

}