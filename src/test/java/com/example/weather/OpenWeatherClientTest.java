package com.example.weather;


import org.springframework.test.context.TestPropertySource;

//@FeignTest(basePackages = { "com.example.weather.**.client." })
@TestPropertySource({ "classpath:application-test.properties" })
public class OpenWeatherClientTest {}
