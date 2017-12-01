package ua.epam.appliance.entity;

import org.junit.Before;
import org.junit.Test;
import ua.epam.appliance.criteria.CriteriaField;

import static org.junit.Assert.*;

public class MobilePhoneTest {

    private MobilePhone mobilePhone;

    @Test
    @Before
    public void createMobilePhone()  {
        mobilePhone = new MobilePhone("Lenovo S660",10);
    }

    @Test
    public void toStringTest(){
        String string = mobilePhone.toString();
        String expected = "MobilePhone[modelName='Lenovo S660', inches=0.0, CPU='null', batteryCapacity=0, CPUFrequency='null', memoryCapacity=0, driveCapacity=0, camera='null', OS='null', power=10]";
        assertEquals(string,expected);
    }

    @Test
    public void setCamera()  {
        mobilePhone.setCamera("7.2M");
        String camera = mobilePhone.getCamera();
        String expected = "7.2M";
        assertEquals(camera,expected);
    }

    @Test
    public void getCriteria(){
        mobilePhone.getValue(CriteriaField.MODEL_NAME);
        mobilePhone.getValue(CriteriaField.CAMERA);

    }
}