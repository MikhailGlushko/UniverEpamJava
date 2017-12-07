package ua.epam.military;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponTest {

    @Test
    public void getInstance() {
        Weapon weapon = Weapon.getInstance();
        System.out.println(weapon);
    }
}