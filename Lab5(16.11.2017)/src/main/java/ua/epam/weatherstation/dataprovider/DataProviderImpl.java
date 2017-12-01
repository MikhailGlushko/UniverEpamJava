package ua.epam.weatherstation.dataprovider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.epam.weatherstation.entity.Weather;
import ua.epam.weatherstation.server.openweather.entity.JWeather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataProviderImpl implements DataProvider {

    private String addr;

    public DataProviderImpl(String addr) {
        this.addr = addr;
    }

    public InputStream getInputStream() {
        InputStream inputStream = null;

        try {
            URL url = new URL(addr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            inputStream = connection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    @Override
    public String get() {
        StringBuffer buffer = new StringBuffer();

        String line = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream()))) {
            while ((line=reader.readLine())!=null)
                buffer.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }
}
