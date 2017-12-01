package ua.epam.appliance.entity;

import org.junit.Before;
import org.junit.Test;
import ua.epam.appliance.criteria.CriteriaField;

import static org.junit.Assert.assertEquals;

public class LaptopTest {

    private Laptop laptop;

    @Test
    @Before
    public void createLaptop() {
        laptop = new Laptop("Lenovo", 100);
    }

    @Test
    public void toStringTest(){
        String string = laptop.toString();
        String expected = "Laptop[modelName='Lenovo', inches=0.0, CPU='null', CPUFrequency='null', memoryCapacity=0, driveCapacity=0, OS='null', DVD='null', batteryCapacity=0, power=100]";
        assertEquals(string,expected);
    }

    @Test
    public void setDVD(){
        laptop.setDVD("32X");
        String dvd = laptop.getDVD();
        String expected = "32X";
        assertEquals(dvd,expected);
    }

    @Test
    public void setBatteryCapacity() {
        laptop.setBatteryCapacity(7200);
        int batterycapacity = laptop.getBatteryCapacity();
        int expected = 7200;
        assertEquals(batterycapacity,expected);
    }

    @Test
    public void setOS() {
        laptop.setOS("Windows 7");
        String os = laptop.getOS();
        String expected = "Windows 7";
        assertEquals(os,expected);
    }

    @Test
    public void setHDDCapacity() {
        laptop.setDriveCapacity(240);
        int hdd = laptop.getDriveCapacity();
        int expected = 240;
        assertEquals(hdd,expected);
    }

    @Test
    public void setMemoryCapacity() {
        laptop.setMemoryCapacity(64);
        int memory = laptop.getMemoryCapacity();
        int expected = 64;
        assertEquals(memory,expected);
    }

    @Test
    public void setCPUFrequency() {
        laptop.setCPUFrequency("1.7");
        String cpu = laptop.getCPUFrequency();
        String expected = "1.7";
        assertEquals(cpu,expected);
    }

    @Test
    public void setCPU() {
        laptop.setCPU("Intel I7");
        String cpu = laptop.getCPU();
        String expected = "Intel I7";
        assertEquals(cpu,expected);
    }

    @Test
    public void setInches() {
        laptop.setInches(14);
        double inc = laptop.getInches();
        double expected = 14d;
        assertEquals(inc,expected,0);
    }

    @Test
    public void switchOn() throws IllegalAccessException {
        laptop.switchON();
        boolean isSwiched = laptop.isSwiched();
        boolean expected = true;
        assertEquals(isSwiched,expected);
    }

    @Test(expected = IllegalAccessException.class)
    public void switchOnDouble() throws IllegalAccessException {
        laptop.switchON();
        laptop.switchON();
    }

    @Test
    public void switchOff() throws IllegalAccessException {
        laptop.switchON();
        laptop.switchOff();
        boolean isSwiched = laptop.isSwiched();
        boolean expected = false;
        assertEquals(isSwiched,expected);
    }

    @Test(expected = IllegalAccessException.class)
    public void switchOffDouble() throws IllegalAccessException {
        laptop.switchOff();
    }

    @Test
    public void getConsumedPowerIfSwichedOff() {
        int consumedPower = laptop.getConsumedPower();
        int expected = 0;
        assertEquals(consumedPower,expected);
    }

    @Test
    public void getConsumedPowerIfSwitchedOn() throws IllegalAccessException {
        laptop.switchON();
        int consumedPower = laptop.getConsumedPower();
        int expected = 100;
        assertEquals(consumedPower,expected);
    }

    @Test
    public void getCriteria(){
        laptop.getValue(CriteriaField.DVD);
        laptop.getValue(CriteriaField.MODEL_NAME);
    }
}