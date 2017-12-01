package ua.epam.mazeold;

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
}
