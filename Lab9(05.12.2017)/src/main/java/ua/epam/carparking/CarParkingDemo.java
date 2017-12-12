package ua.epam.carparking;

import java.util.LinkedList;
import java.util.Queue;

public class CarParkingDemo {
    Parking[] parkings = new Parking[5];

    Queue<Car> cars = new LinkedList<>();
    private int carCount;

    public CarParkingDemo(int countCarr) {
        this.carCount = countCarr;
        for (int i=0; i<parkings.length;i++){
            int idx = (int)(Math.random()*5+1)*5;
            parkings[i] = new Parking("Парковка "+i,idx);
        }
    }

    public void makeCarQueue() {
        for (int i = 0; i < carCount; i++) {
            Car car = new Car();
            int rndIdx = (int) (Math.random() * parkings.length);
            car.setParking(parkings, rndIdx);
            cars.add(car);
        }
    }

    public void demo() {
        do {
            while (!cars.isEmpty()) {
                Car car = cars.poll();
                car.setCars(cars);
                Thread thread = new Thread(car);
                thread.setName(car.getParkings()[car.getParkingIdx()].getName()+" - Автомобіль " + car.getID());
                thread.start();
            }
        } while (getCarsNotParked()>0);
    }

    private int getCarsNotParked(){
            int result = 0;
            for (int i=0; i<parkings.length; i++){
                result += parkings[i].semaphore.getQueueLength();
            }
            return result;
        }

    public static void main(String[] args) {

        CarParkingDemo demo = new CarParkingDemo(100);
        demo.makeCarQueue();
        demo.demo();

    }
}
