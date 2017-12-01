package ua.epam.weatherstation.dataprovider;

import java.io.IOException;
import java.io.InputStream;
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

}
