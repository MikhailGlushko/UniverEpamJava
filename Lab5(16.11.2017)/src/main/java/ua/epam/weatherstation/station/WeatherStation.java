package ua.epam.weatherstation.station;

import ua.epam.weatherstation.server.DataServer;

import java.time.LocalDateTime;

/**
 * Реалізація клієнта погодніх даних
 */
public class WeatherStation implements WeatherObsever {

    @Override
    public void update(DataServer dataServer) {
        new Thread() {
            @Override
            public void run() {
                showWeather(dataServer);
            }
        }.start();
    }

    public void showWeather(DataServer dataServer){
        LocalDateTime t = LocalDateTime.now();
        System.out.println(t+" "+this+"Отнимано нові дані: "+ dataServer.getWeather());
        System.out.println(t+" "+this+"Завантажуємо прогнозні дані");
        System.out.println(t+" "+this+"Прогноз : "+ dataServer.getForecast());
    }
}
