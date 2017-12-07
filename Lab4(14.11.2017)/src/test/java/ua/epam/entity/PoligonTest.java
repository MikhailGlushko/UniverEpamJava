package ua.epam.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PoligonTest {
    @Test(expected = IllegalArgumentException.class)
    public void createPoliginWithWrongNumberOfPoints(){
        Point a = new Point(1,1);
        Point b = new Point(2,2);
        Point c = new Point(3,3);
        Poligon poligon = new Poligon(a,b,c);
    }

    @Test
    public void createPoligin() {
        Point a = new Point(1,1);
        Point b = new Point(2,2);
        Point c = new Point(3,3);
        Point d = new Point(4,4);
        Poligon poligon = new Poligon(a,b,c,d);
        boolean result = poligon==null;
        boolean expected = false;
        assertEquals("Помилка створення", result, expected);
    }

}