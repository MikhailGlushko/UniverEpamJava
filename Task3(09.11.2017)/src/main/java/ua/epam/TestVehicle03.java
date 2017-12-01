package ua.epam;

import ua.epam.classes.Plane;
import ua.epam.classes.Vehicle;
import ua.epam.factories.AbstractVehicleFactory;
import ua.epam.factories.VehicleFactory;

/**
 * 3. найти механизмы с максимальной скоростью в диапазоне 200 - 500, но не Plane
 */
public class TestVehicle03 {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[20];
        generateVehicles(vehicles);
        showVehicles(vehicles);
    }

    private static void showVehicles(Vehicle[] vehicles) {

        int count = 0;

        System.out.println("Механізми не Plane зі швидкістю в діапазоні 200-500: ");
        for (int i = 0; i < vehicles.length; i++) {
            if (!(vehicles[i] instanceof Plane)){
                Vehicle vehicle = vehicles[i];
                if(vehicle.getSpeed()>=200 && vehicle.getSpeed()<=500) {
                    System.out.println(vehicle);
                    count++;
                }
            }
        }
        if(count==0)
            System.out.println("не знайдено");

        System.out.println();


    }

    private static void generateVehicles(Vehicle[] vehicles) {
        AbstractVehicleFactory factory = new VehicleFactory();
        for (int i=0; i<vehicles.length; i++){
            vehicles[i] = factory.getVehicle();
        }
    }

}
