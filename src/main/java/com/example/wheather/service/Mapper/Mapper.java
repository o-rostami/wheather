@Mapper(componentModel = "spring")
public interface Mapper {

	WeatherResponseModel toWeatherResponseModel(WeatherResponse model);

}