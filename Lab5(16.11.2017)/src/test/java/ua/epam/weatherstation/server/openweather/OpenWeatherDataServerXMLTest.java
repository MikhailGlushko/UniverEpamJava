package ua.epam.weatherstation.server.openweather;

import org.junit.Before;
import org.junit.Test;
import ua.epam.weatherstation.dao.WeatherDataStorageImpl;
import ua.epam.weatherstation.entity.Pair;
import ua.epam.weatherstation.entity.Weather;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OpenWeatherDataServerXMLTest {

    OpenWeatherDataServerXML dataProvider;

    @Before
    public void init(){
        dataProvider = new OpenWeatherDataServerXML(new WeatherDataStorageImpl());
    }


    @Test
    //@Ignore
    public void start() throws Exception {
        dataProvider = mock(OpenWeatherDataServerXML.class);

        Weather tmp = new Weather(new Pair("2","C"),new Pair("2", "%"),new Pair("2", "hPa"));
        when(dataProvider.getWeather()).thenReturn(tmp);
        dataProvider.getWeather();
        when(dataProvider.getForecast()).thenReturn(tmp);
        dataProvider.getForecast();
        when(dataProvider.getWeatherData()).thenReturn(new WeatherDataStorageImpl());
        dataProvider.getWeather();

        dataProvider.getWeatherData().setWeather(tmp);

        InputStream stream = new ByteArrayInputStream("q".getBytes());
    }

    @Test
    public void weatherParse() throws Exception {
        String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<current><city id=\"703448\" name=\"Kiev\"><coord lon=\"30.52\" lat=\"50.43\"></coord><country>UA</country><sun rise=\"2017-11-29T05:34:22\" set=\"2017-11-29T13:58:06\"></sun></city><temperature value=\"-1\" min=\"-1\" max=\"-1\" unit=\"metric\"></temperature><humidity value=\"100\" unit=\"%\"></humidity><pressure value=\"1015\" unit=\"hPa\"></pressure><wind><speed value=\"3\" name=\"Light breeze\"></speed><gusts></gusts><direction value=\"110\" code=\"ESE\" name=\"East-southeast\"></direction></wind><clouds value=\"90\" name=\"хмарно\"></clouds><visibility value=\"1900\"></visibility><precipitation mode=\"no\"></precipitation><weather number=\"600\" value=\"легкий снігопад\" icon=\"13d\"></weather><lastupdate value=\"2017-11-29T09:30:00\"></lastupdate></current>";
        InputStream inputStream = new ByteArrayInputStream(line.getBytes());
       // Weather weather = dataProvider.weatherParse(inputStream);
        //System.out.println(weather);
    }

    @Test(expected = NullPointerException.class)
    public void extractData() throws Exception {
        String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<current><city id=\"703448\" name=\"Kiev\"><coord lon=\"30.52\" lat=\"50.43\"></coord><country>UA</country><sun rise=\"2017-11-29T05:34:22\" set=\"2017-11-29T13:58:06\"></sun></city><temperature value=\"-1\" min=\"-1\" max=\"-1\" unit=\"metric\"></temperature><humidity value=\"100\" unit=\"%\"></humidity><pressure value=\"1015\" unit=\"hPa\"></pressure><wind><speed value=\"3\" name=\"Light breeze\"></speed><gusts></gusts><direction value=\"110\" code=\"ESE\" name=\"East-southeast\"></direction></wind><clouds value=\"90\" name=\"хмарно\"></clouds><visibility value=\"1900\"></visibility><precipitation mode=\"no\"></precipitation><weather number=\"600\" value=\"легкий снігопад\" icon=\"13d\"></weather><lastupdate value=\"2017-11-29T09:30:00\"></lastupdate></current>";
        InputStream inputStream = new ByteArrayInputStream(line.getBytes());
        Pair pair = new Pair();
        dataProvider.extractData(pair,null);
    }


    @Test
    public void stop() throws Exception {

    }


}