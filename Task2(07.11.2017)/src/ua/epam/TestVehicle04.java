package ua.epam;

import ua.epam.classes.Vehicle;
import ua.epam.factories.AbstractVehicleFactory;
import ua.epam.factories.VehicleFactoryWithSuperCars;
import ua.epam.interfaces.Flyable;
import ua.epam.interfaces.MoveAble;
import ua.epam.interfaces.SwimAble;

/**
 * 4. добавить к данной иерархии машину-амфибию, и БетМобиль, создать 3 масива сгупированых по Интерфейсам Flyable, MoveAble, SwimAble
 */
public class TestVehicle04 {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[20];
        generateVehicles(vehicles);
        showVehicles(vehicles);
    }

    private static void showVehicles(Vehicle[] vehicles) {
        Vehicle[] flyable = new Vehicle[vehicles.length];
        Vehicle[] swimable = new Vehicle[vehicles.length];
        Vehicle[] moveable = new Vehicle[vehicles.length];

        int idx1 = 0, idx2 = 0, idx3 = 0;

        for (int i = 0; i < vehicles.length; i++) {
            Vehicle vehicle = vehicles[i];
            if(vehicle instanceof Flyable){
                flyable[idx1++] = vehicle;
            }
            if(vehicle instanceof SwimAble){
                swimable[idx2++] = vehicle;
            }
            if(vehicle instanceof MoveAble){
                moveable[idx3++] = vehicle;
            }
        }
        System.out.println("\nЩо вміють літати");
        for (int i = 0; i < flyable.length; i++) {
            if(flyable[i]!=null) {
                System.out.println(flyable[i]);
                ((Flyable)flyable[i]).fly();
                System.out.println();
            }
        }
        System.out.println("\nЩо вміють плавати");
        for (int i = 0; i < swimable.length; i++) {
            if(swimable[i]!=null) {
                System.out.println(swimable[i]);
                ((SwimAble)swimable[i]).swim();
                System.out.println();
            }
        }
        System.out.println("\nЩо вміють їздити");
        for (int i = 0; i < moveable.length; i++) {
            if(moveable[i]!=null) {
                System.out.println(moveable[i]);
                ((MoveAble)moveable[i]).move();
                System.out.println();
            }
        }
    }

    private static void generateVehicles(Vehicle[] vehicles) {
        AbstractVehicleFactory factory = new VehicleFactoryWithSuperCars();
        for (int i=0; i<vehicles.length; i++){
            vehicles[i] = factory.getVehicle();
        }
    }
}
