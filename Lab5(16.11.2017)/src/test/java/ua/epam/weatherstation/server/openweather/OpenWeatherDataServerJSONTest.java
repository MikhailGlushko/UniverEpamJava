package ua.epam.weatherstation.server.openweather;

import org.junit.Before;
import org.junit.Test;
import ua.epam.weatherstation.dao.WeatherDataStorage;
import ua.epam.weatherstation.dataprovider.DataProvider;
import ua.epam.weatherstation.dataprovider.DataProviderImpl;
import ua.epam.weatherstation.entity.Pair;
import ua.epam.weatherstation.entity.Weather;
import ua.epam.weatherstation.server.DataServer;
import ua.epam.weatherstation.station.WeatherObsever;
import ua.epam.weatherstation.station.WeatherStation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OpenWeatherDataServerJSONTest {
    private OpenWeatherDataServerJSON dataProvider;

    @Before
    public void init(){
        dataProvider = new OpenWeatherDataServerJSON();
    }

    @Test
    public void weatherParse() throws Exception {
        String line = "{\"coord\":{\"lon\":30.52,\"lat\":50.43},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"туман\",\"icon\":\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":0,\"pressure\":1015,\"humidity\":100,\"temp_min\":0,\"temp_max\":0},\"visibility\":1400,\"wind\":{\"speed\":4,\"deg\":140},\"clouds\":{\"all\":90},\"dt\":1511973180,\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0025,\"country\":\"UA\",\"sunrise\":1511933684,\"sunset\":1511963876},\"id\":703448,\"name\":\"Kiev\",\"cod\":200}";
        InputStream inputStream = new ByteArrayInputStream(line.getBytes());
        Weather weather = dataProvider.weatherParse(inputStream);
        System.out.println(weather);
    }


    @Test
    public void registerObserver() throws Exception {
        DataServer dataServer = new OpenWeatherDataServerJSON();
        WeatherObsever weatherObsever = new WeatherStation();
        dataServer.registerObserver(weatherObsever);
        int size = dataServer.getObseverList().size();
        assertEquals(1,size);
    }

    @Test
    public void removeObserver() throws Exception {
        DataServer dataServer = new OpenWeatherDataServerJSON();
        WeatherObsever weatherObsever = new WeatherStation();
        dataServer.registerObserver(weatherObsever);
        dataServer.removeObserver(weatherObsever);
        int size = dataServer.getObseverList().size();
        assertEquals(0,size);
    }

    @Test
    public void notifyObservers() throws Exception {
        DataServer dataServer = new OpenWeatherDataServerJSON();
        WeatherObsever weatherObsever = new WeatherStation();
        dataServer.registerObserver(weatherObsever);
        dataServer.notifyObservers();
        boolean hasNewWeather = dataServer.getWeatherData().isHasNewWeather();
        assertEquals(false, hasNewWeather);
    }


    @Test
    public void dowloadWeather() throws Exception {

        DataServer dataServer = new OpenWeatherDataServerJSON();

        final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?"+
                "q="+ dataServer.CITY+
                "&appid="+ dataServer.APIKey+
                "&mode="+ dataServer.MODE+
                "&units="+ dataServer.UNITS+
                "&lang="+ dataServer.LANG;

        //DataProvider dataProvider = new DataProviderImpl(WEATHER_BASE_URL);
        DataProvider dataProvider = mock(DataProviderImpl.class);

        String line = "{\"coord\":{\"lon\":30.52,\"lat\":50.43},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"туман\",\"icon\":\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":0,\"pressure\":1015,\"humidity\":100,\"temp_min\":0,\"temp_max\":0},\"visibility\":1400,\"wind\":{\"speed\":4,\"deg\":140},\"clouds\":{\"all\":90},\"dt\":1511973180,\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0025,\"country\":\"UA\",\"sunrise\":1511933684,\"sunset\":1511963876},\"id\":703448,\"name\":\"Kiev\",\"cod\":200}";

        when(dataProvider.getInputStream()).thenReturn(new ByteArrayInputStream(line.getBytes()));
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

        InputStream stream = new ByteArrayInputStream("q".getBytes());
        //InputStream stream = System.in;
        dataServer.start(stream);

    }


    @Test
    public void getForecast() throws Exception {

        DataServer dataServer = new OpenWeatherDataServerJSON();
        String line  = "{\"coord\":{\"lon\":30.52,\"lat\":50.43},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"легкий снігопад\",\"icon\":\"13d\"},{\"id\":701,\"main\":\"Mist\",\"description\":\"туман\",\"icon\":\"50d\"},{\"id\":300,\"main\":\"Drizzle\",\"description\":\"легка мряка\",\"icon\":\"09d\"}],\"base\":\"stations\",\"main\":{\"temp\":0.34,\"pressure\":1016,\"humidity\":99,\"temp_min\":0,\"temp_max\":1},\"visibility\":4400,\"wind\":{\"speed\":3,\"deg\":130},\"clouds\":{\"all\":90},\"dt\":1512027000,\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0438,\"country\":\"UA\",\"sunrise\":1512020137,\"sunset\":1512050252},\"id\":703448,\"name\":\"Kiev\",\"cod\":200}";
        InputStream stream = new ByteArrayInputStream(line.getBytes());
        dataServer.setTimeout(1);
        dataServer.dowloadWeather(stream);
        Weather forecast = dataServer.getForecast();
        assertNotNull(forecast);
    }

    @Test
    public void getWeather() throws Exception {
        DataServer dataServer = new OpenWeatherDataServerJSON();
        Weather weather = dataServer.getWeather();
        assertNull(weather);
        Weather expected = new Weather(new Pair("2","C"),new Pair("2", "%"),new Pair("2", "hPa"));
        WeatherDataStorage weatherData = dataServer.getWeatherData();
        weatherData.setWeather(expected);
        weather = dataServer.getWeather();
        assertNotNull(weather);
    }

    @Test
    public void getWeatherData() throws Exception {
        DataServer dataServer = new OpenWeatherDataServerJSON();
        WeatherDataStorage weatherData = dataServer.getWeatherData();
        assertNotNull(weatherData);
    }

    @Test
    public void setWeatherData() throws Exception {
        DataServer dataServer = new OpenWeatherDataServerJSON();
        WeatherDataStorage weatherData = dataServer.getWeatherData();
        dataServer.setWeatherData(null);
        weatherData = dataServer.getWeatherData();
        assertNull(weatherData);
    }


    @Test
    public void start(){
        DataServer dataServer = new OpenWeatherDataServerJSON();

        final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?"+
                "q="+ dataServer.CITY+
                "&appid="+ dataServer.APIKey+
                "&mode="+ dataServer.MODE+
                "&units="+ dataServer.UNITS+
                "&lang="+ dataServer.LANG;

        // #1 client
        WeatherStation weatherStation = new WeatherStation();

        DataProvider dataProvider = new DataProviderImpl(WEATHER_BASE_URL);
        dataServer.setDataProvider(dataProvider);

        dataServer.registerObserver(weatherStation);

        InputStream stream = new ByteArrayInputStream("u\nq".getBytes());

        dataServer.start(stream);
        boolean running = dataServer.running;
        System.out.println(running);
    }

    @Test
    public void stop() throws Exception {
    }
}