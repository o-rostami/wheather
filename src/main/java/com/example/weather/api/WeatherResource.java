package com.example.weather.api;

import javax.validation.constraints.NotNull;

import com.example.weather.api.mapper.WeatherResourceMapper;
import com.example.weather.api.model.WeatherResponse;
import com.example.weather.service.WeatherService;
import com.example.weather.service.model.WeatherResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherResource {

	private final WeatherService weatherService;

	private final WeatherResourceMapper mapper;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WeatherResponse> getCurrentWeather(@RequestParam @NotNull Double lat, @RequestParam @NotNull Double lon) {
		logger.info("got weather request with lat : {} and lon: {}", lat, lon);
		WeatherResponseModel result = weatherService.getCurrentWeather(mapper.toWeatherRequestModel(lat, lon));
		logger.info("successfully response is: {}", result); return ResponseEntity.ok(mapper.toWeatherResponse(result));
	}

}
