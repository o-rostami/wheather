package com.example.weather;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.weather.WeatherResponse;

@FeignClient(name = "openWeatherClient", url = "${open.weather.score.base.url}", configuration = OpenWeatherFeignClientConfiguration.class)
public interface OpenWeatherClient {

	@GetMapping(path = "/data/2.5/weather", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	WeatherResponse getCurrentWeather(@RequestParam String lat, @RequestParam String lon);

}
