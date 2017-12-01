package ua.epam.weatherstation.server.openweather.entity;

public class Coord{
    double lon;
    double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coords{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
