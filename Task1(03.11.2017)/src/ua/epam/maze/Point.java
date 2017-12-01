package ua.epam.maze;

/**
 * Координати клітинки
 */
public class Point {
    private final int i;
    private final int j;

    public Point(int i, int j){
        this.i = i;
        this.j=j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (i != point.i) return false;
        return j == point.j;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }

    @Override
    public String toString() {
        return "[" +
                 i +
                "," + j +
                ']';
    }
}
