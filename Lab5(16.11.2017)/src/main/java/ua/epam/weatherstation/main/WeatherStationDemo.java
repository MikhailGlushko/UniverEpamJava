package ua.epam.weatherstation.main;

import ua.epam.weatherstation.dataprovider.DataProvider;
import ua.epam.weatherstation.dataprovider.DataProviderImpl;
import ua.epam.weatherstation.server.DataServer;
import ua.epam.weatherstation.server.openweather.OpenWeatherDataServerJSON;
import ua.epam.weatherstation.station.WeatherStation;

import java.io.InputStream;

/**
 * фримен page 71  реализовать патерн наблюдатель(Weather Station) + присоеденить считываение данных
 * по интернет для датчиков
 */

public class WeatherStationDemo {

    public static void main(String[] args) {

        DataServer dataServer = new OpenWeatherDataServerJSON();

        final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?"+
                "q="+ dataServer.CITY+
                "&appid="+ dataServer.APIKey+
                "&mode="+ dataServer.MODE+
                "&units="+ dataServer.UNITS+
                "&lang="+ dataServer.LANG;

        DataProvider dataProvider = new DataProviderImpl(WEATHER_BASE_URL);
        dataServer.setDataProvider(dataProvider);

        // #1 client
        WeatherStation weatherStation = new WeatherStation();
        dataServer.registerObserver(weatherStation);

        // #2 client
        weatherStation = new WeatherStation();
        dataServer.registerObserver(weatherStation);

        // #3 client
        weatherStation = new WeatherStation();
        dataServer.registerObserver(weatherStation);

        //InputStream stream = new ByteArrayInputStream("q".getBytes());
        InputStream stream = System.in;
        dataServer.start(stream);
    }
}
