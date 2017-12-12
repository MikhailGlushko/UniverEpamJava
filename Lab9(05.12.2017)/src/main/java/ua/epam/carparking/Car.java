package ua.epam.carparking;

import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Car extends Thread {

    public static Logger logger = Logger.getLogger(Car.class.getSimpleName());
    public static int nextId = 1;

    private int parkingIdx;
    private Parking[] parkings;
    private Queue<Car> cars;

    private int id;
    private int parkingTime;
    private int waitingTime;

    public Car() {
        this.id = nextId++;
        this.parkingTime = (int)(Math.random()*30000);
        this.waitingTime = (int)(Math.random()*10000);
        logger.info("Автомобіль "+this.id);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        logger.info("Підїхав до автостоянки. Запитуємо наявність вільного місця");
        logger.info("Вільно : "+parkings[parkingIdx].semaphore.availablePermits()+" з "+parkings[parkingIdx].getCapacity()+" в черзі попереду : "+parkings[parkingIdx].semaphore.getQueueLength());
        if(!parkings[parkingIdx].accept(this.waitingTime, TimeUnit.MILLISECONDS)){
            logger.info("немає часу більше чекати. Їду на іншу астостоянку");
            this.setParking(parkings,++parkingIdx);
            this.parkingTime-=this.waitingTime;
            cars.add(this);
            return;
        }
        long end = System.currentTimeMillis();
        int place = -1;
        place = parkings[parkingIdx].takaPlace();
        logger.info("Зайняв місце "+place);
        logger.info("Вільно : "+parkings[parkingIdx].semaphore.availablePermits()+" з "+parkings[parkingIdx].getCapacity()+" в черзі позаду : "+parkings[parkingIdx].semaphore.getQueueLength());
        try {
            this.parkingTime = Math.max(this.parkingTime -(int) Math.max(end-start,0),0);
            logger.info("Залишаю автомобіль на "+ this.parkingTime);
            Thread.sleep(this.parkingTime);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        parkings[parkingIdx].releasePlace(place);
        logger.info("Звільнив місце "+place);
        parkings[parkingIdx].release();
        logger.info("Залишив автостоянку");
        logger.info("Вільно : "+parkings[parkingIdx].semaphore.availablePermits()+" з "+parkings[parkingIdx].getCapacity()+" в черзі : "+parkings[parkingIdx].semaphore.getQueueLength());
    }

    @Override
    public String toString() {
        return "Автомобіль " + id;
    }

    public Parking[] getParkings() {
        return parkings;
    }

    public int getParkingIdx() {
        return parkingIdx;
    }

    public void setParking(Parking[] parking, int index) {
        this.parkingIdx = index;
        this.parkings = parking;

        if(this.parkingIdx == this.parkings.length)
            this.parkingIdx=0;
    }

    public Queue<Car> getCars() {
        return cars;
    }

    public void setCars(Queue<Car> cars) {
        this.cars = cars;
    }

    public int getID(){
        return this.id;
    }

}
