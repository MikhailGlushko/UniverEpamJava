package ua.epam.weatherstation.station;

import ua.epam.weatherstation.server.DataServer;

/**
 * Клієнт погодніх даних
 */
public interface WeatherObsever {
    void update(DataServer dataServer);
}
