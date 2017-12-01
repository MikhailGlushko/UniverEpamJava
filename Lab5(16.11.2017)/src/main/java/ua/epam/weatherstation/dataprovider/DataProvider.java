package ua.epam.weatherstation.dataprovider;

import java.io.InputStream;

public interface DataProvider {

    public InputStream getInputStream();
    public String get();
}
