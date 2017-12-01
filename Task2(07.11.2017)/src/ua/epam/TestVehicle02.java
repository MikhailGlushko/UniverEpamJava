package ua.epam;

import ua.epam.classes.Plane;
import ua.epam.classes.Vehicle;
import ua.epam.factories.AbstractVehicleFactory;
import ua.epam.factories.VehicleFactory;

/**
 * 2. найти из механизмов Plane c с высотой полета выше 5000 с годом выпуска после 2000
 */
public class TestVehicle02 {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[20];
        generateVehicles(vehicles);
        showVehicles(vehicles);
    }

    private static void showVehicles(Vehicle[] vehicles) {

        int count = 0;

        System.out.println("Механізми Plane з висотою польоту вище 5000 та роком випуску після 2000: ");
        for (int i = 0; i < vehicles.length; i++) {
            if(vehicles[i] instanceof Plane){
                Plane plane = (Plane)vehicles[i];
                if(plane.getAltitude()>5000 && plane.getYear()>2000) {
                    System.out.println(plane+", altitude="+plane.getAltitude());
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
