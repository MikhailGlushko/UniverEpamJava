package ua.epam.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

public class Poligon extends Figure implements Serializable{
    private Point[] points;

    /**
     * Конструируем полигон за массивом точек
     * @param points
     * @throws IllegalArgumentException
     */
    public Poligon(Point ... points) throws IllegalArgumentException{
        if(points.length<4)
            throw new IllegalArgumentException("У полігона повинно бути від 4 точок");
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Poligon{" +
                "points=" + Arrays.toString(points) +
                '}';
    }
}
