package ua.epam.classes;

import ua.epam.interfaces.SwimAble;

public class Amphibian extends Car implements SwimAble{
    public Amphibian(String name, int year) {
        super(name, year);
    }

    @Override
    public void swim() {
        System.out.println(getClass().getSimpleName()+" пливе зі швидкістю "+getSpeed()/5);
    }
}
