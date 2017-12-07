package ua.epam.weatherstation.dao;

import ua.epam.weatherstation.entity.Weather;

import java.util.List;

/**
 * Клас для зберыгання погодних даних
 */
public interface WeatherDataStorage {

    Weather getWeather();
    List<Weather> getHistory();
    List<Weather> getHistory(int count);
    boolean isHasNewWeather();

    void setWeather(Weather weather);
    void setHasNewWeather(boolean hasNewWeather);

    Weather getForecast();
}
