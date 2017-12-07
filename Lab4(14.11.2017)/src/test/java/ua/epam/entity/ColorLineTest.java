package ua.epam.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorLineTest {

    @Test(expected = IllegalArgumentException.class)
    public void createLineWithWrongColor() {
        ColorLine line = new ColorLine(1,2,3,4,-1);
        int result = line.getColor();
        int expected = 5;
        assertEquals("Помилка задання кольору", result,expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongColor() {
        ColorLine line = new ColorLine(1,2,3,4,5);
        line.setColor(-1);
    }

    @Test
    public void setColor() {
        ColorLine line = new ColorLine(1,2,3,4,5);
        int result = line.getColor();
        int expected = 5;
        assertEquals("Помилка задання кольору", result,expected);
    }

}