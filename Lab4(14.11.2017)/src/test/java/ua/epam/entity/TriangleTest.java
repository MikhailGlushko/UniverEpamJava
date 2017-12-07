package ua.epam.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void createTriangle(){
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle = new Triangle(a,b,c);
        boolean result = triangle==null;
        boolean expected = false;
        assertEquals("Помилка створення", result,expected);

    }

    @Test(expected = IllegalArgumentException.class)
    public void createTriangleWithWrongPoints(){
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(1,1);
        Triangle triangle = new Triangle(a,b,c);
    }

    @Test
    public void getPerimetr() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle = new Triangle(a,b,c);
        double perimetr = triangle.getPerimetr();
        double expected = 5.6568;
        assertEquals("Помилка периметра", perimetr, expected,0.0001);
    }

    @Test
    public void getArea() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle = new Triangle(a,b,c);
        double area = triangle.getArea();
        double expected = 0;
        assertEquals("Помилка периметра", area, expected,0.0001);
    }

    @Test
    public void setApexA() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle = new Triangle(a,b,c);
        triangle.setApexA(new Point(2,2));
    }

    @Test
    public void setApexB() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle = new Triangle(a,b,c);
        triangle.setApexB(new Point(2,3));
    }

    @Test
    public void setApexC() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle = new Triangle(a,b,c);
        triangle.setApexC(new Point(2,2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setApexAWithWrongPoint() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle = new Triangle(a,b,c);
        triangle.setApexA(new Point(0,0));
    }

    @Test
    public void cloneTriangle() {
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle1 = new Triangle(a,b,c);
        Triangle triangle2 = (Triangle) triangle1.clone();

        boolean result = triangle1.equals(triangle2);
        boolean expected = true;
        assertEquals("Помилка клонування", result, expected);

        result = triangle1==triangle2;
        expected = false;
        assertEquals("Помилка клонування", result, expected);
    }

    @Test
    public void equals(){
        Point a = new Point(1,1);
        Point b = new Point(1,-1);
        Point c = new Point(0,0);
        Triangle triangle1 = new Triangle(a,b,c);
        Triangle triangle2 = new Triangle(a,b,c);

        boolean result = triangle1.equals(triangle2);
        boolean expected = true;
        assertEquals("Обєкти не рівні", result, expected);

        Point d = new Point(-1,-1);
        Triangle triangle3 = new Triangle(a,b,d);
        result = triangle1.equals(triangle3);
        expected = false;
        assertEquals("Обєкти не рівні", result, expected);

        result = a.hashCode()!=b.hashCode();
    }

}