package ua.epam.weatherstation.server.openweather.entity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class JWeather {
    private Coord coord;
    private Weathers weather[];
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;
    private long dt;
    private Sys sys;
    private int id;
    private String name;
    private int cod;

    public Coord getCoord() {
        return coord;
    }

    public Weathers[] getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public long getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return cod;
    }

    @Override
    public String toString() {
        return "JWeather{" +
                "coord=" + coord +
                ", weather=" + Arrays.toString(weather) +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", rain=" + rain +
                ", snow=" + snow +
                ", dt=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ").format(new Date(dt*1000L)) +
                ", sys=" + sys +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", code=" + cod +
                '}';
    }
}

