package com.example.weather;

import java.util.List;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public ResponseEntity<WeatherResponse> getCurrentWeather(@RequestParam @NotNull Double lat, @RequestParam @NotNull Double lon) throws BusinessException {
		logger.info("got weather request with lat : {} and lon: {}", lat, lon);
		WeatherResponseModel result = service.getCurrentweather(mapper.toWeatherRequestModel(lat, lon));
		logger.info("successfully respone is: {}", result); return ResponseEntity.ok(mapper.toWeatherResponse(result));
	}

}
