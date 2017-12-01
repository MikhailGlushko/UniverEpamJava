package ua.epam.classes;

import ua.epam.interfaces.MoveAble;

public class Car extends Vehicle implements MoveAble{

    public Car(String name, int year) {
        super(name, year);
    }

    @Override
    public void move() {
        System.out.println(getClass().getSimpleName()+" їде зі швидкістю "+getSpeed());
    }
}
