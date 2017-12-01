package ua.epam.entity;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class ColorPoligonTest {
    @Test()
    public void CreatePoligonWintWronnPointNumber(){
        boolean expected = true;
        boolean result;
        try {
            ColorPoligon cp3 = new ColorPoligon(1,
                    new ColorPoint(1,1,1),
                    new ColorPoint(2,2,2),
                    new ColorPoint(3,3,3));
            fail("Ошибок не возникло!");
            ColorPoligon cp2 = new ColorPoligon(1,
                    new ColorPoint(1, 1, 1),
                    new ColorPoint(2,2,2));
            fail("Ошибок не возникло!");
            ColorPoligon cp1 = new ColorPoligon(1, new ColorPoint(1, 1, 1));
            fail("Ошибок не возникло!");
        } catch (IllegalArgumentException ex){
            //System.out.printf("Полигон не создан");
        }
    }

    @Test
    public void createPoligon() {
        ColorPoligon cp = new ColorPoligon(1,
                new ColorPoint(1, 1, 1),
                new ColorPoint(2, 2, 2),
                new ColorPoint(3, 3, 3),
                new ColorPoint(4, 4, 4));
        boolean result = cp == null;
        boolean expected = false;
        assertEquals(result, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWrongColor(){
        ColorPoligon cp = new ColorPoligon(1,
                new ColorPoint(1, 1, 1),
                new ColorPoint(2, 2, 2),
                new ColorPoint(3, 3, 3),
                new ColorPoint(4, 4, 4));
        cp.setColor(-1);
        fail("Ошибка");
    }

    @Test
    public void setColor(){
        ColorPoligon cp = new ColorPoligon(1,
                new ColorPoint(1, 1, 1),
                new ColorPoint(2, 2, 2),
                new ColorPoint(3, 3, 3),
                new ColorPoint(4, 4, 4));
        try {
            cp.setColor(1);
        } catch (IllegalArgumentException ex){
            fail("Ошибка");
        }
    }
}