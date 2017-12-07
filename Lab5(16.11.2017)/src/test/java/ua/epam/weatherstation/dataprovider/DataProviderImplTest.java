package ua.epam.weatherstation.dataprovider;

import org.junit.Test;
import ua.epam.weatherstation.server.DataServer;
import ua.epam.weatherstation.server.openweather.OpenWeatherDataServerJSON;

import java.io.InputStream;

import static org.junit.Assert.*;

public class DataProviderImplTest {
    @Test
    public void getInputStream() {

        DataServer dataServer = new OpenWeatherDataServerJSON();

        final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?"+
                "q="+dataServer.CITY+
                "&appid="+dataServer.APIKey+
                "&mode="+dataServer.MODE+
                "&units="+dataServer.UNITS+
                "&lang="+dataServer.LANG;

        DataProvider dataProvider = new DataProviderImpl(WEATHER_BASE_URL);
        InputStream inputStream = dataProvider.getInputStream();
        assertNotNull(inputStream);

    }

}