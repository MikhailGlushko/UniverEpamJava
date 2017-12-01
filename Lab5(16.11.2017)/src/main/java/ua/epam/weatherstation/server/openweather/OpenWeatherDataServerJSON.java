package ua.epam.weatherstation.server.openweather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.epam.weatherstation.server.DataServer;
import ua.epam.weatherstation.server.openweather.entity.JWeather;
import ua.epam.weatherstation.entity.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * реалізація доступу до конкретного погоднього сервера http://openweathermap.org та опрацювання отриманих даних
 */

/**
 city
    city.id City ID
    city.name City name
    city.coord
        city.coord.lon City geo location, longitude
        city.coord.lat City geo location, latitude
    city.country Country code (GB, JP etc.)
    city.sun
        city.sun.rise Sunrise time
        city.sun.set Sunset time
 temperature
    temperature.value Temperature
    temperature.min Minimum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and megalopolises geographically expanded (use these parameter optionally).
    temperature.max Maximum temperature at the moment of calculation. This is deviation from 'temp' that is possible for large cities and megalopolises geographically expanded (use these parameter optionally).
    temperature.unit Unit of measurements. Possilbe valure is Celsius, Kelvin, Fahrenheit.
 humidity
    humidity.value Humidity value
    humidity.unit %
 pressure
    pressure.value Pressure value
    pressure.unit hPa
 wind
    wind.speed
        wind.speed.value Wind speed, mps
        wind.speed.name Type of the wind
    wind.direction
        wind.direction.value Wind direction, degrees (meteorological)
        wind.direction.code Code of the wind direction. Possilbe value is WSW, N, S etc.
        wind.direction.name Full name of the wind direction.
 clouds
    clouds.value Cloudiness
    clouds.name Name of the cloudiness
 visibility
    visibility.value Visibility, meter
 precipitation
    precipitation.value Precipitation, mm
    precipitation.mode Possible values are 'no", name of weather phenomena as 'rain', 'snow'
 weather
    weather.number Weather condition id
    weather.value Weather condition name
    weather.icon Weather icon id
 lastupdate
    lastupdate.value Last time when data was updated
 */

public class OpenWeatherDataServerJSON extends DataServer {
    public final String MODE   = "json";

    public OpenWeatherDataServerJSON() {
    }

    @Override
    protected Weather weatherParse(InputStream stream) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            while ((line=reader.readLine()) !=null)
                buffer.append(line);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        line = buffer.toString();
        System.out.println(line);

        Gson gson = new GsonBuilder().create();
        JWeather JWeather = (JWeather) gson.fromJson(line, JWeather.class);
        Weather weather = new Weather(JWeather);

        return weather;
    }
}
