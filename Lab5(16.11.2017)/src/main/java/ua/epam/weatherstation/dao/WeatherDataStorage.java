package ua.epam.weatherstation.dao;

import ua.epam.weatherstation.entity.Weather;

import java.util.List;

/**
 * Клас для зберыгання погодних даних
 */
public interface WeatherDataStorage {

    public Weather getWeather();
    public List<Weather> getHistory();
    public List<Weather> getHistory(int count);
    public boolean isHasNewWeather();

    public void setWeather(Weather weather);
    public void setHasNewWeather(boolean hasNewWeather);

    public Weather getForecast();
}
