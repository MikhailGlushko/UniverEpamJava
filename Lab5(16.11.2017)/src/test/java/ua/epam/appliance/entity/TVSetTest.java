package ua.epam.appliance.entity;

import org.junit.Before;
import org.junit.Test;
import ua.epam.appliance.criteria.CriteriaField;

import static org.junit.Assert.*;

public class TVSetTest {

    private TVSet tvSet;

    @Test
    @Before
    public void createTVSet()  {
        tvSet = new TVSet("LG",10);
    }

    @Test
    public void toStringTest(){
        String string = tvSet.toString();
        String expected = "TVSet[modelName='LG', inc=0.0, screenResolution='null', screenRefreshRate='null', digitalTuner='null', power=10]";
        assertEquals(string,expected);
    }

    @Test
    public void setInc() {
        tvSet.setInches(32);
        double inc = tvSet.getInches();
        double expected = 32d;
        assertEquals(inc,expected,0);
    }

    @Test
    public void setScreenResolution()  {
        tvSet.setScreenResolution("4000*4200");
        String screen = tvSet.getScreenResolution();
        String expected = "4000*4200";
        assertEquals(screen,expected);
    }

    @Test
    public void setScreenRefreshRate() {
        tvSet.setScreenRefreshRate("800");
        String screen = tvSet.getScreenRefreshRate();
        String expected = "800";
        assertEquals(screen,expected);
    }

    @Test
    public void setDigitalTuner()  {
        tvSet.setDigitalTuner("DVB-T");
        String screen = tvSet.getDigitalTuner();
        String expected = "DVB-T";
        assertEquals(screen,expected);

    }

    @Test
    public void getCriteria(){
        tvSet.getValue(CriteriaField.MODEL_NAME);
        tvSet.getValue(CriteriaField.INCHES);
        tvSet.getValue(CriteriaField.SCREEN_RESOLUTION);
        tvSet.getValue(CriteriaField.SCREEN_REFRESH_RATE);
        tvSet.getValue(CriteriaField.DIGITAL_TUNER);
    }

}