package ua.epam;

import ua.epam.classes.Vehicle;
import ua.epam.factories.AbstractVehicleFactory;
import ua.epam.factories.VehicleFactory;

public class TestVehicle00 {
    public static void main(String[] args) {

        AbstractVehicleFactory factory = new VehicleFactory();

        Vehicle[] vehicles = new Vehicle[10];
        for (int i=0; i<vehicles.length; i++){
            vehicles[i] = factory.getVehicle();
            System.out.println(vehicles[i]);
        }
    }
}
