package ua.epam.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {

    @Test(expected = IllegalArgumentException.class)
    public void createLineWithWrongPoints() {
        Line a = new Line(1,1,1,1);
    }

    @Test
    public void createLineByCoords(){
        Line b = new Line(1,1,3,3);
        boolean result = b==null;
        boolean expected = false;
        assertEquals("Помилка стровення", result, expected);
    }

    @Test
    public void createLineByPoints(){
        Line b = new Line(new Point(1,1), new Point(3,3));
        boolean result = b==null;
        boolean expected = false;
        assertEquals("Помилка стровення", result, expected);
    }

    @Test
    public void length() {
        Line a = new Line(1,1,4 ,4);
        double result = a.length();
        double expected = 4.2426;
        assertEquals("Длина линии нек верна",result,expected,0.0001);
    }

    @Test
    public void cloneLine() {
        Line l1 = new Line(1,2,3,4);
        Line l2 = (Line)l1.clone();
        boolean result   = l1.equals(l2);
        boolean expected = true;
        assertEquals("Ошибка", result,expected);

        result = l1==l2;
        assertNotEquals("Ошибка", result,expected);
    }

    @Test
    public void equals() {
        Line l1 = new Line(1,1,2,2);
        Line l2 = new Line(1,1,2,2);
        Line l3 = new Line(1,2,3,4);
        boolean result = l1.equals(l2);
        boolean expected = true;
        assertEquals("Ошибка", result,expected);

        result = l1==l2;
        assertNotEquals("Ошибка", result,expected);

        result = l1.equals(l3);
        assertNotEquals("Ошибка", result,expected);

        result = l1.hashCode()==l2.hashCode();
    }

}