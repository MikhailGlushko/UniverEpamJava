package ua.epam.weatherstation.dao;

import org.junit.Before;
import org.junit.Test;
import ua.epam.weatherstation.entity.Pair;
import ua.epam.weatherstation.entity.Weather;

import java.util.List;

import static org.junit.Assert.*;

public class WeatherDataImplTest {
    Weather weather;

    WeatherDataStorage weatherData;
    @Test
    @Before
    public void initData(){
        weatherData = new WeatherDataStorageImpl();
        weather = new Weather(new Pair("0","C"),new Pair("0", "%"),new Pair("0", "hPa"));
        weatherData.setWeather(weather);
    }

    @Test
    public void getWeather() throws Exception {
        weather = weatherData.getWeather();
        boolean result = weather==null;
        boolean required = false;
        assertEquals(required,required);
    }

    @Test
    public void getForecast() throws Exception {
        Weather result = new Weather(new Pair("1","C"),new Pair("1", "%"),new Pair("1", "hPa"));
        weatherData.setWeather(result);
        Weather forecast = weatherData.getForecast();
        System.out.println(forecast);

        result = new Weather(new Pair("2.5","C"),new Pair("1", "%"),new Pair("1", "hPa"));
        weatherData.setWeather(result);
        forecast = weatherData.getForecast();
        System.out.println(forecast);
    }

    @Test
    public void setWeather() throws Exception {
        Weather result = new Weather(new Pair("1","C"),new Pair("1", "%"),new Pair("1", "hPa"));
        weatherData.setWeather(weather);
    }

    @Test
    public void getHistory() throws Exception {
        List<Weather> history = weatherData.getHistory();
        int result = history.size();
        int required = 0;
        assertEquals(result,required);

        Weather newWeather = new Weather(new Pair("2","C"),new Pair("2", "%"),new Pair("2", "hPa"));
        weatherData.setWeather(newWeather);
        history = weatherData.getHistory();
        result = history.size();
        required = 1;
        assertEquals(result,required);

        weatherData.getHistory(1);
        weatherData.getHistory(2);

    }

    @Test
    public void isHasnewWeather() throws Exception {
        boolean result = weatherData.isHasNewWeather();
        boolean required = true;
        assertEquals(result,required);
    }

    @Test
    public void setHasnewWeather() throws Exception {
        weatherData.setHasNewWeather(false);
        boolean result = weatherData.isHasNewWeather();
        boolean required = false;
        assertEquals(result,required);
    }

}