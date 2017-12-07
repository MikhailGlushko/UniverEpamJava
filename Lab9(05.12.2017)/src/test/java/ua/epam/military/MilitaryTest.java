package ua.epam.military;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MilitaryTest {

    Military military;

    @Before
    public void init(){
        military = new Military();
    }

    @Test
    public void makeDepot() {
        military.makeDepot();
    }

    @Test
    public void steal(){
        military.steal();
    }

    @Test
    public void load(){
        military.steal();
        military.load();
    }

    @Test
    public void calculate() {
        military.steal();
        military.load();
        military.calculate();
    }

    @Test
    public void work(){
        military.work();
    }
}