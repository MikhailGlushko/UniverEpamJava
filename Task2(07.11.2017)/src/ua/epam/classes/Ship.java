package ua.epam.classes;

import ua.epam.interfaces.SwimAble;

public class Ship extends Vehicle implements SwimAble {

    private int passangers;
    private String port;

    public Ship(String name, int year) {
        super(name, year);
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getPassangers() {
        return passangers;
    }

    public void setPassangers(int passangers) {
        this.passangers = passangers;
    }

    @Override
    public void swim() {
        System.out.println(getClass().getSimpleName()+" пливе зі шаидкістю "+getSpeed());
    }
}
