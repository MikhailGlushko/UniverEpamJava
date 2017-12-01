package ua.epam.entity;

public class ColorTriangle extends Triangle implements ColoredFigure{
    private int color;

    /**
     * Конструируем триугольник за тремя точками и цветом
     * @param apexA
     * @param apexB
     * @param apexC
     * @param color
     * @throws IllegalArgumentException
     */
    public ColorTriangle(Point apexA, Point apexB, Point apexC, int color) throws IllegalArgumentException {
        super(apexA, apexB, apexC);
        if(color<0)
            throw new IllegalArgumentException("не допустимий колір");
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if(color<0)
            throw new IllegalArgumentException("не допустимий колір");
        this.color = color;
    }

    @Override
    public String toString() {
        return "ColorTriangle{" + "color=" + color + ", apexA=" + getApexA() + ", apexB=" + getApexB() + ", apexC=" + getApexC() + '}';
    }
}
