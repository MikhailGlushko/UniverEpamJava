package ua.epam.entity;

import java.util.Arrays;

public class ColorPoligon extends Poligon implements ColoredFigure {
    private int color;

    /**
     * Конструируем полигон за цветом и масивом точек
      * @param color
     * @param points
     * @throws IllegalArgumentException
     */
    public ColorPoligon(int color, Point... points)  throws IllegalArgumentException{
        super(points);
        if (color<0)
            throw new IllegalArgumentException("не допустимий колір");
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if (color<0)
            throw new IllegalArgumentException("не допустимий колір");
        this.color = color;
    }

    @Override
    public String toString() {
        return "ColorPoligon{" +
                "color=" + color +
                ", points=" + Arrays.toString(getPoints()) +
                '}';
    }
}
