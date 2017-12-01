package ua.epam.factories;

import ua.epam.classes.*;

public class VehicleFactory extends AbstractVehicleFactory{
    public Vehicle getVehicle(){
        int value = (int)(Math.random()*3);
        switch (value){
            case 0:
                Car car = new Car("CarName "+(int)(Math.random()*1000), (int)(Math.random()*7+2010));
                car.setStart(new Point(0,0));
                car.setEnd(new Point((int)(Math.random()*999), (int)(Math.random()*999)));
                car.setPrice((int)(Math.random()*100));
                car.setSpeed((int)(Math.random()*100+50));
                return car;
            case 1:
                Plane plane = new Plane("PlaneName "+(int)(Math.random()*1000), (int)(Math.random()*7+2010));
                plane.setStart(new Point(0,0));
                plane.setEnd(new Point((int)(Math.random()*999), (int)(Math.random()*999)));
                plane.setPrice((int)(Math.random()*100));
                plane.setSpeed((int)(Math.random()*950+50));
                plane.setAltitude((int)(Math.random()*10000));
                plane.setPassangers((int)(Math.random()*100));
                return plane;
            case 2:
                Ship ship = new Ship("Shipname "+(int)(Math.random()*1000), (int)(Math.random()*20+1990));
                ship.setStart(new Point(0,0));
                ship.setEnd(new Point((int)(Math.random()*999), (int)(Math.random()*999)));
                ship.setPrice((int)(Math.random()*100));
                ship.setSpeed((int)(Math.random()*950+50));
                ship.setPassangers((int)(Math.random()*100));
                ship.setPort("Portname"+(int)(Math.random()*100));
                return ship;
        }

        return null;
    }
}
