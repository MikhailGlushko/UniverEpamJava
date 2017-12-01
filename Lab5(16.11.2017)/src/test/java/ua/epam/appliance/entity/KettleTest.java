package ua.epam.appliance.entity;

import org.junit.Before;
import org.junit.Test;
import ua.epam.appliance.criteria.CriteriaField;

import static org.junit.Assert.*;

public class KettleTest {

    private Kettle kettle;

    @Test
    @Before
    public void creareRettle(){
        kettle = new Kettle("Philips", 1500);
    }

    @Test
    public void getVaterVolume() throws Exception {
        int expected = 1500;
        kettle.setVaterVolume(expected);
        int result = kettle.getVaterVolume();
        assertEquals(result,expected);

    }

    @Test
    public void getBoilingTime() throws Exception {
        int expected = 30;
        kettle.setBoilingTime(expected);
        int result = kettle.getBoilingTime();
        assertEquals(result,expected);
    }

    @Test
    public void getCriteria(){
        kettle.getValue(CriteriaField.BOLING_TIME);
        kettle.getValue(CriteriaField.VATER_VOLUME);
        kettle.getValue(CriteriaField.MODEL_NAME);
    }

    @Test
    public void testToString(){
        kettle.toString();
    }
}