package ua.epam;

import ua.epam.classes.Vehicle;
import ua.epam.factories.AbstractVehicleFactory;
import ua.epam.factories.VehicleFactory;

/**
 * 1. механизмы с наименьшей ценой с наибольшей скоростью и не старше 5 лет
 */
public class TestVehicle01 {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[20];
        generateVehicles(vehicles);
        showVehicles(vehicles);
    }

    private static void showVehicles(Vehicle[] vehicles) {
        int minPrice = -1;
        int minPriceidx = 0;
        int maxSpeed = -1;
        int maxSpeedidx = 0;
        int countOlder =0;

        System.out.println("механізми старіші за 5 років: ");
        for (int i = 0; i < vehicles.length; i++) {
            if(minPrice==-1)minPrice=vehicles[i].getPrice();
            if(vehicles[i].getPrice()<minPrice){
                minPrice = vehicles[i].getPrice();
                minPriceidx = i;
            }

            if(maxSpeed==-1)maxSpeed=vehicles[i].getSpeed();
            if(maxSpeed<vehicles[i].getSpeed()){
                maxSpeed = vehicles[i].getSpeed();
                maxSpeedidx = i;
            }

            if (2017-vehicles[i].getYear()>5){
                countOlder++;
                System.out.println(vehicles[i]);
            }
        }
        if(countOlder==0)
            System.out.println("не знайдено");

        System.out.println();

        System.out.println("Механізм з найменшою ціною:");
        System.out.println(vehicles[minPriceidx]);

        System.out.println("\nМеханізм з максимальною швидкістю: ");
        System.out.println(vehicles[maxSpeedidx]);
    }

    private static void generateVehicles(Vehicle[] vehicles) {
        AbstractVehicleFactory factory = new VehicleFactory();
        for (int i=0; i<vehicles.length; i++){
            vehicles[i] = factory.getVehicle();
        }
    }
}
