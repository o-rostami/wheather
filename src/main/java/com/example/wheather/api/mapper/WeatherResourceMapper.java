@Mapper(componentModel = "spring")
public interface Mapper {

	WeatherResponse toWeatherResponse(WeatherResponseModel model);

}