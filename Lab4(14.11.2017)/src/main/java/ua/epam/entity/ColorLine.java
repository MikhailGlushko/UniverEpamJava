package ua.epam.entity;

public class ColorLine extends Line implements ColoredFigure {

    private int color;

    /**
     * Конструируем линию за двумя точками и цветом
     * @param beg
     * @param end
     * @param color
     * @throws IllegalArgumentException - цвет меньше 0
     */
    public ColorLine(Point beg, Point end, int color) throws IllegalArgumentException{
        super(beg, end);
        if(color<0)
            throw new IllegalArgumentException("Спроба створити лінію колір якої <0");
        this.color = color;
    }

    /**
     * Конструируем линию за координатами и цветом
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @param color
     * @throws IllegalArgumentException - цвет меньше 0
     */
    public ColorLine(int x1, int x2, int y1, int y2, int color) throws IllegalArgumentException{
        super(x1, x2, y1, y2);
        if(color<0)
            throw new IllegalArgumentException("Спроба створити лінію колір якої <0");
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    /**
     * @param color
     * @throws IllegalArgumentException - недопустимий колір
     */
    public void setColor(int color) throws IllegalArgumentException{
        if(color<0)
            throw new IllegalArgumentException("Спроба задати колір <0");
        this.color = color;
    }

    @Override
    public String toString() {
        return "ColorLine{" + "color=" + color + ", beg=" + getBeg() + ", end=" + getEnd() + '}';
    }
}
