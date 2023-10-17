package com.example.wheather.client;


import com.example.wheather.client.model.OpenWeatherClientResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherClient", url = "${open.weather.score.base.url}", configuration = OpenWeatherClientConfiguration.class)
public interface OpenWeatherClient {

	@GetMapping(path = "/data/2.5/weather", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	OpenWeatherClientResponse getCurrentWeather(@RequestParam Double lat, @RequestParam Double lon);

}
