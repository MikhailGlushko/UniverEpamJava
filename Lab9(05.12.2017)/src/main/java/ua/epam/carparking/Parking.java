package ua.epam.carparking;

import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Parking {
    public static Logger logger = Logger.getLogger(Parking.class.getSimpleName());
    public final Semaphore semaphore;

    private int capacity;
    private boolean[] place;
    private String name;

    public Parking(String name, int capacity) {
        this.name = name;
        this.capacity=capacity;
        this.place = new boolean[capacity];
        this.semaphore = new Semaphore(capacity,true);
    }

    /**
     * Заняти парко-місце на стоянці
     * @return номер парко-місця або -1
     * Якщо не було збоїв в роботі, то завжди поверне коректне числобільше 0 і менше за capacity
     */
    public int takaPlace(){
        int result = -1;
        synchronized(this.place) {
            for (int i = 0; i < this.place.length; i++) {
                if (!this.place[i]) {
                    this.place[i] = true;
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Звільнити місце на стоянці
     * @param index
     */
    public void releasePlace(int index){
        if(index<0 || index>=capacity) {
            logger.error("Не допустимий індекс");
            throw new IndexOutOfBoundsException("Не допустимий індекс");
        }
        synchronized (this.place) {
            this.place[index] = false;
        }
    }

    /**
     * Заїхати на стоянку, очікує поки не воявится вільне парко-місце
     * @return
     */
    public boolean accept(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * Заїхати на стоянку, очікує певний час, поки не появиться вільне парко-місце
     * @param time - час в мілісекундах
     * @param timeUnit @see TimeUnit
     * @return
     */
    public boolean accept(int time, TimeUnit timeUnit){
        boolean tryAcquire = false;
        try {
            tryAcquire = semaphore.tryAcquire(time, timeUnit);
        } catch (InterruptedException e) {
            logger.error(e);
            return false;
        }
        return tryAcquire;
    }

    /**
     * Звільняє паркомісце
     */
    public void release(){
        semaphore.release();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}
