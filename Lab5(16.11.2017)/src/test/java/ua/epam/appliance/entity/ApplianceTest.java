package ua.epam.appliance.entity;

import org.junit.Test;
import ua.epam.appliance.criteria.CriteriaField;

import static org.junit.Assert.*;

public class ApplianceTest {

    @Test
    public void compareTo() throws Exception {
        Appliance phone = new MobilePhone("Lenovo S660",10);
        Appliance laptop = new Laptop("Lenovo T440",100);
        phone.switchON();
        laptop.switchON();

        int result = phone.compareTo(laptop);
        int expected = -90;
        assertEquals(result,expected);
    }

    @Test
    public void getName() {
        Appliance phone = new MobilePhone("Lenovo S660",10);
        String name = phone.getModelName();
        String expected = "Lenovo S660";
        assertEquals(name,expected);
    }

    @Test
    public void getPower() {
        Appliance phone = new MobilePhone("Lenovo S660",10);
        int name = phone.getPower();
        int expected = 10;
        assertEquals(name,expected);
    }

    @Test
    public void getID(){
        Appliance phone = new MobilePhone("Lenovo S660",10);
        long id = 1;
        int expected = 1;
        phone.setId(id);
        id = phone.getId();
        assertEquals(id,expected);
    }

    public void getCriteria(){
        Appliance phone = new MobilePhone("Lenovo S660",10);
        phone.getValue(CriteriaField.DIGITAL_TUNER);
        phone.getValue(CriteriaField.SCREEN_REFRESH_RATE);
        phone.getValue(CriteriaField.SCREEN_RESOLUTION);
        phone.getValue(CriteriaField.INCHES);
        phone.getValue(CriteriaField.MODEL_NAME);
        phone.getValue(CriteriaField.CAMERA);
        phone.getValue(CriteriaField.VATER_VOLUME);
        phone.getValue(CriteriaField.BATTERY_CAPACITY);
        phone.getValue(CriteriaField.BOLING_TIME);
        phone.getValue(CriteriaField.CPU);
        phone.getValue(CriteriaField.CPU_FREQUENCY);
        phone.getValue(CriteriaField.DRIVE_CAPACITY);
        phone.getValue(CriteriaField.DVD);
        phone.getValue(CriteriaField.ID);
        phone.getValue(CriteriaField.MEMORY_CAPACITY);
        phone.getValue(CriteriaField.OS);
        phone.getValue(CriteriaField.POWER);
        phone.getValue(CriteriaField.SWICHED);
    }

}