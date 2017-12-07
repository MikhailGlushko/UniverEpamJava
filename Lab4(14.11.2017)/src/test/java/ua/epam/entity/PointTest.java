package ua.epam.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void threePointsOnSameeLine() {
        Point a = new Point(1,1);
        Point b = new Point(2,2);
        Point c = new Point(-1,-1);
        boolean result = Point.threePointsOnSameeLine(a,b,c);
        boolean expected = true;
        assertEquals("Три точки не на одной линии",result,expected);

        Point d = new Point(-1,1);
        result = result = Point.threePointsOnSameeLine(a,b,d);
        expected = false;
        assertEquals("Три точки на одной линии",result,expected);
    }

    @Test
    public void clonePoint() {
        Point a = new Point(1,1);
        Point b = (Point) a.clone();
        boolean expected = true;
        boolean result;

        result = a.equals(b);
        assertEquals("Координати точок різні", result,expected);
        result = a==b;
        assertNotEquals("Адреси точок рівзні", result,expected);
    }

    @Test
    public void equals() {
        Point a = new Point(1,1);
        Point b = (Point) a.clone();
        boolean expected = true;
        boolean result;

        Point c = new Point(1,1);
        result = a.equals(c);
        assertEquals("Координати точок різні", result,expected);
        result = a.hashCode()==b.hashCode();


        Point d = new Point(2,2);
        result = a.equals(d);
        assertNotEquals("Координати точок однакові", result,expected);
    }

}