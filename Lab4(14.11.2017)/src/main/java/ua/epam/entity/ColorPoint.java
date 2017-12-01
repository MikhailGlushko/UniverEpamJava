package ua.epam.entity;

public class ColorPoint extends Point implements ColoredFigure{
    private int color;

    /**
     * Конструируем цветную точку за координатами и цветом
     * @param x
     * @param y
     * @param color
     * @throws IllegalArgumentException - когда цвет меньше 0
     */
    public ColorPoint(int x, int y, int color) throws IllegalArgumentException{
        super(x, y);
        if(color<0)
            throw new IllegalArgumentException("Спроба створити точку колір якої <0");

        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if(color<0)
            throw new IllegalArgumentException("Спроба задати колір <0");

        this.color = color;
    }

    @Override
    public String toString() {
        return "ColorPoint{" + "color=" + color + ", X=" + getX() + ", Y=" + getY() + '}';
    }
}
