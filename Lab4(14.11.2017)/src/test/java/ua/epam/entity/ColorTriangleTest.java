package ua.epam.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorTriangleTest {
    @Test
    public void setColor() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(-2,-2);
        int color = 1;

        ColorTriangle triangle = new ColorTriangle(a,b,c,color);
        int result = triangle.getColor();
        int expected = 1;
        assertEquals("Помилка встановлення кольору", result, expected);

        triangle.setColor(color);
        result = triangle.getColor();
        expected = 1;
        assertEquals("Помилка встановлення кольору", result, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongColor() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(-2,-2);
        int color = 1;

        ColorTriangle triangle = new ColorTriangle(a,b,c,color);
        color = -1;
        triangle.setColor(color);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongColorWithException() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(-2,-2);
        int color = -1;

        ColorTriangle triangle = new ColorTriangle(a,b,c,color);
    }
}