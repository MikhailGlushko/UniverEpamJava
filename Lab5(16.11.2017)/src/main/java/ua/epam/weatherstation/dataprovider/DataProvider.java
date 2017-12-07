package ua.epam.weatherstation.dataprovider;

import java.io.InputStream;

public interface DataProvider {

    InputStream getInputStream();
    String get();
}
