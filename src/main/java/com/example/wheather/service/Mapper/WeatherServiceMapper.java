package com.example.wheather.service.Mapper;

import com.example.wheather.client.model.OpenWeatherClientResponse;
import com.example.wheather.service.model.WeatherResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherServiceMapper {

	WeatherResponseModel toWeatherResponseModel(OpenWeatherClientResponse model);

}