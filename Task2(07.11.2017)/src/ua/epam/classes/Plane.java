package ua.epam.classes;

import ua.epam.interfaces.Flyable;

public class Plane extends Vehicle implements Flyable{
    private int altitude;
    private int passangers;

    public int getPassangers() {
        return passangers;
    }

    public void setPassangers(int passangers) {
        this.passangers = passangers;
    }

    public Plane(String name, int year) {
        super(name, year);
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName()+" летить зі швидкістю "+getSpeed()+" на висоті "+getAltitude());
    }
}
