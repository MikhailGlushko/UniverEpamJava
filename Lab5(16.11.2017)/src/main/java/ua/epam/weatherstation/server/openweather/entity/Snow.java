package ua.epam.weatherstation.server.openweather.entity;

import com.google.gson.annotations.SerializedName;

public class Snow{
    @SerializedName("3h")
    int _3h;

    public int get_3h() {
        return _3h;
    }

    public void set_3h(int _3h) {
        this._3h = _3h;
    }

    @Override
    public String toString() {
        return "Snow{" +
                "3h=" + _3h +
                '}';
    }
}
