package ua.epam.entity;

public class Point extends Figure implements Cloneable {
    private int x;
    private int y;

    /**
     * Конструируем точку за координатами
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean threePointsOnSameeLine(Point a, Point b, Point c){
        return (c.getX()-a.getX())*(b.getY()-a.getY())== (b.getX()-a.getX())*(c.getY()-a.getY());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + getX() + ", y=" + getY() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
