package ua.epam.string.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataProvider{

    public String getContent(String addr) {
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(addr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine()) != null)
                stringBuilder.append(line);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.print(" ( Не вдалось підключитись до сервера: "+addr+" )");
            return null;
            //e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
