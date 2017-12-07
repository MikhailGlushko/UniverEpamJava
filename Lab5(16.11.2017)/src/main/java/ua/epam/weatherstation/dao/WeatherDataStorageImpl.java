package ua.epam.weatherstation.dao;

import ua.epam.weatherstation.entity.Pair;
import ua.epam.weatherstation.entity.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас для зберыгання погодних даних
 */
public class WeatherDataStorageImpl implements WeatherDataStorage {
    private Weather weather;
    private List<Weather> history;
    private boolean hasNewWeather;

    public WeatherDataStorageImpl() {
    }

    public Weather getWeather() {
        return weather;
    }

    /**
     * Перевіряємо, чи нові дані відрізняються від попередніх
     * І тільки якщо вони дійсно нові - зберігаємо їх
     * Попередні дані переносимо в архів
     * @param weather
     */
    public void setWeather(Weather weather) {
        if (this.weather==null || !this.weather.equals(weather)){
            if(history==null)
                history = new ArrayList<>();
            else
                history.add(this.weather);
            this.weather = weather;
            hasNewWeather = true;
        }
    }

    /**
     * Повернути історію погодніх даних
     * @return
     */
    public List<Weather> getHistory() {
        return history;
    }

    public List<Weather> getHistory(int count) {
        if(count<=0 || history!=null && count>history.size())
            return history;
        List<Weather> tmp = new ArrayList<>();
        for (int i=0; i<count; i++){
            if(history.size()>0)
                tmp.add(history.get(i));
            else break;
        }
        return tmp;
    }

    public Weather getForecast(){
        Weather result = null;
        Weather tmp = null;
        if(history==null || history.size()==0) {
            return this.weather;
        }
        try {
            result = (Weather) this.weather.clone();
            tmp = (Weather) (history.get(history.size()-1).clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Pair pair;
        Pair oldPair;

        // Temperature
        pair = result.getTemperature();
        oldPair = tmp.getTemperature();
        forecastPair(pair, oldPair);
        // Hummanity
        pair = result.getHumidity();
        oldPair = tmp.getHumidity();
        forecastPair(pair, oldPair);
        //Pressure
        pair = result.getPressure();
        oldPair = tmp.getPressure();
        forecastPair(pair, oldPair);

        return result;
    }

    /**
     * Розраховуємо прогноз для погодніх даних
     * @param newPair
     * @param oldPair
     */
    private void forecastPair(Pair newPair, Pair oldPair) {
        String newValue = newPair.getValue();
        String oldValue = oldPair.getValue();

        try{
            int newIntValue = Integer.parseInt(newValue);
            int oldIntValue = Integer.parseInt(oldValue);
            int delta = oldIntValue-newIntValue;
            newPair.setValue(Integer.toString(newIntValue-delta));

        } catch (Exception e){
            double newDobleValue = Double.parseDouble(newValue);
            double oldDoubleValue = Double.parseDouble(oldValue);
            double delta = oldDoubleValue-newDobleValue;

            newPair.setValue(Double.toString(((int)((newDobleValue-delta)*100))/100.0));
        }
    }

    /**
     * перевірити, чи змінилися погодні дані
     * @return
     */
    public boolean isHasNewWeather() {
        return hasNewWeather;
    }

    /**
     * Онулити статус нових даних
     * @param hasNewWeather
     */
    public void setHasNewWeather(boolean hasNewWeather) {
        this.hasNewWeather = hasNewWeather;
    }
}
