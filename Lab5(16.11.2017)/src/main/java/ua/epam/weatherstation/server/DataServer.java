package ua.epam.weatherstation.server;

import ua.epam.weatherstation.dao.WeatherDataStorage;
import ua.epam.weatherstation.dao.WeatherDataStorageImpl;
import ua.epam.weatherstation.dataprovider.DataProvider;
import ua.epam.weatherstation.entity.Weather;
import ua.epam.weatherstation.station.WeatherObsever;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Отримання погодніх даних від датчиків (з сервера погоди)
 */
abstract public class DataServer {
    public final String APIKey = "76f37caefb4a0de9cade877a11f5138e";
    public final String CITY   = "Kiev";
    public final String UNITS  = "metric";
    public final String LANG   = "ua";
    // must be initialized in child class
    public String MODE;

    private URL url;
    private int timeout = 30;

    public volatile boolean running;
    private List<WeatherObsever> obseverList;
    private WeatherDataStorage weatherData;

    private DataProvider dataProvider;


    public DataServer() {
        this.weatherData = new WeatherDataStorageImpl();
    }
    public DataServer(WeatherDataStorage weatherData) {
        this.weatherData = weatherData;
    }

    /**
     * Запускає погодній сервіс
     * @param stream звідки читати інтерактивні дані
     */
    public void start(InputStream stream){
        System.out.println("Стартуємо");
        System.out.println("допустимі команди:");
        System.out.println("q -вихід");
        System.out.println("u -примусова нотифікація");
        running = true;
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("Weather server started");
                while (running) {
                    dowloadWeather();
                }
                System.out.println("Weather server stoped");
            }
        };
        thread.setDaemon(true);
        thread.start();

        Scanner scanner = new Scanner(stream);

        while (running){
            try {
                if (scanner.hasNext()){
                    String st = scanner.nextLine();
                    if(st.equals("q")) {
                        System.out.println("Отримано команду завершення роботи");
                        running = false;
                    }
                    if(st.equals("u")) {
                        System.out.println("Примусова нотифікація всім клієнтам");
                        getWeatherData().setHasNewWeather(true);
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * реєстрація нового клієнта на сервері
     * @param client
     */
    public void registerObserver(WeatherObsever client){
        if (obseverList ==null)
            obseverList = new ArrayList<>();
        obseverList.add(client);
    }

    /**
     * Видалення клієнта з сервера
     * @param client
     */
    public void removeObserver(WeatherObsever client){
        int i = obseverList.indexOf(client);
        if(i>=0)
            obseverList.remove(i);
    }

    /**
     * Повідомити клієнтів, що є нові дані - передати їм дані
     */
    public void notifyObservers(){
        if(obseverList!=null && obseverList.size()>0) {
            System.out.println("Відправка нотифікацій клієнтам");
            for (int i = 0; i < obseverList.size(); i++) {
                WeatherObsever weatherObsever = (WeatherObsever) obseverList.get(i);
                System.out.println("Відрпавляємо дані клієнту "+i+" "+weatherObsever);
                weatherObsever.update(this);
            }
            weatherData.setHasNewWeather(false);
        }
    }

    /**
     * Зупиняє роботу сервера
     */
    public void stop() {
        running = false;
    }


    /**
     * Отримує погоді від погоднього сервера
     * @return
     */
    public Weather dowloadWeather(){
        Weather weather = null;

        weather = weatherParse();

        if (weather!=null)
            getWeatherData().setWeather(weather);

        if(getWeatherData().isHasNewWeather())
            notifyObservers();

        try {
            Thread.sleep(timeout*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return weather;
    }

    /**
     * Чиає дані з потоку і перетворює їх в погодній формат. Реалізовує конкретний провайдер
     * @param stream
     * @return
     */
    abstract protected Weather weatherParse();

    /**
     * Повертає погоду
     * @return
     */
    public Weather getForecast(){
        return weatherData.getForecast();
    }

    /**
     * Повертає прогноз
     * @return
     */
    public Weather getWeather(){
        return  weatherData.getWeather();
    }

    public WeatherDataStorage getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherDataStorage weatherData) {
        this.weatherData = weatherData;
    }

    public List<WeatherObsever> getObseverList() {
        return obseverList;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }
}
