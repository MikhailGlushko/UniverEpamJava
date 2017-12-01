package ua.epam.classes;

import ua.epam.interfaces.Flyable;

public class BetMobile extends Amphibian implements Flyable{
    public BetMobile(String name, int year) {
        super(name, year);
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName()+" летить зі швидкістю "+getSpeed()*10);
    }
}
