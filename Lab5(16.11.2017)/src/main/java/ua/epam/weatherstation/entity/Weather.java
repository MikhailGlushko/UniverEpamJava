package ua.epam.weatherstation.entity;

import ua.epam.weatherstation.server.openweather.entity.JWeather;

import java.time.LocalDateTime;

/**
 * Інформація про погоду
 */
public class Weather implements Cloneable {
    private Pair temperature;
    private Pair humidity;
    private Pair pressure;
    private LocalDateTime dateTime;

    public Weather(Pair temperature, Pair humidity, Pair pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.dateTime = LocalDateTime.now();
    }

    public Weather(JWeather JWeather){
        this.temperature = new Pair(Double.toString(JWeather.getMain().getTemp()),"Celsius");
        this.humidity = new Pair(Integer.toString(JWeather.getMain().getHumidity()),"%");
        this.pressure  = new Pair(Integer.toString(JWeather.getMain().getPressure()),"hPa");
        this.dateTime = LocalDateTime.now();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Pair getTemperature() {
        return temperature;
    }

    public void setTemperature(Pair temperature) {
        this.temperature = temperature;
    }

    public Pair getHumidity() {
        return humidity;
    }

    public void setHumidity(Pair humidity) {
        this.humidity = humidity;
    }

    public Pair getPressure() {
        return pressure;
    }

    public void setPressure(Pair pressure) {
        this.pressure = pressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weather)) return false;

        Weather weather = (Weather) o;

        if (getTemperature() != null ? !getTemperature().equals(weather.getTemperature()) : weather.getTemperature() != null)
            return false;
        if (getHumidity() != null ? !getHumidity().equals(weather.getHumidity()) : weather.getHumidity() != null)
            return false;
        return getPressure() != null ? getPressure().equals(weather.getPressure()) : weather.getPressure() == null;
    }

    @Override
    public int hashCode() {
        int result = getTemperature() != null ? getTemperature().hashCode() : 0;
        result = 31 * result + (getHumidity() != null ? getHumidity().hashCode() : 0);
        result = 31 * result + (getPressure() != null ? getPressure().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "Temperature=" + temperature +
                ", Humidity=" + humidity +
                ", Pressure=" + pressure +
                ", DateTime=" + dateTime +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Weather tmp = (Weather) super.clone();
        tmp.temperature = (Pair) this.temperature.clone();
        tmp.humidity = (Pair) this.humidity.clone();
        tmp.pressure = (Pair) this.pressure.clone();
        return tmp;
    }
}
