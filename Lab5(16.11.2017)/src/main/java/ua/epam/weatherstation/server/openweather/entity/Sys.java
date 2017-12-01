package ua.epam.weatherstation.server.openweather.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sys {
    int type;
    int id;
    double message;
    String country;
    long sunrise;
    long sunset;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", message=" + message +
                ", country='" + country + '\'' +
                ", sunrise=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ").format(new Date(sunrise*1000L)) +
                ", sunset=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ").format(new Date(sunset*1000L))+
                '}';
    }
}
