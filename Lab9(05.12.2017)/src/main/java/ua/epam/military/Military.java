package ua.epam.military;

import org.apache.log4j.Logger;

import java.util.Stack;

public class Military {

    static Logger logger = Logger.getLogger(Military.class);

    private final Stack<Weapon> depot = new Stack<>();
    private final int countToSteal = 10;
    private volatile int countStealed = 0;
    private volatile int countLoaded = 0;
    private volatile int countCalculated = 0;
    private volatile int price = 0;

    private final Stack<Weapon> stolenWeapon = new Stack<>();
    private final Stack<Weapon> loadedWeapon = new Stack<>();
    private final Stack<Weapon> calculatedWeapon = new Stack<>();

    private volatile boolean stealed;
    private volatile boolean loaded;
    private volatile boolean calculated;

    public Military() {
        makeDepot();
    }

    public void work() {

        new Thread() {
            @Override
            public void run() {
                Thread.currentThread().setName("Нечепорчук");
                calculate();
            }
        }.start();
        logger.info("Нечепорчук ready to calculate");

        new Thread() {
            @Override
            public void run() {
                Thread.currentThread().setName("Петров");
                load();
            }
        }.start();
        logger.info("Петров ready to load");

        new Thread() {
            @Override
            public void run() {
                Thread.currentThread().setName("Иванов");
                steal();
            }
        }.start();
        logger.info("Иванов ready to steal");

        while (!calculated) {
        }
    }


    protected void steal() {
        int count = 0;
        while (count < countToSteal && !depot.empty()) {
            Weapon weapon = depot.pop();
            count++;
            logger.info("Stolen ("+count+" of "+countToSteal+") : " + weapon);
            synchronized (stolenWeapon) {
                stolenWeapon.push(weapon);
                logger.info("Need to load " + stolenWeapon.size());
                stolenWeapon.notify();
            }
            countStealed = count;
        }
        logger.info("Stolen " + count + " weapons");
        stealed = true;
    }

    protected void load() {
        int count = 0;
        while (true) {
            synchronized (stolenWeapon) {
                while (stolenWeapon.isEmpty() && !stealed) {
                    logger.info("Waiting for new weapon to load");
                    try {
                        stolenWeapon.wait();
                    } catch (InterruptedException e) {
                        logger.warn(e);
                    }
                }
                Weapon weapon = stolenWeapon.pop();
                count++;
                logger.info("Loaded ("+count+" of "+countStealed+") : " + weapon);
                synchronized (loadedWeapon) {
                    loadedWeapon.push(weapon);
                    logger.info("Please calculate price");
                    loadedWeapon.notify();
                }
            }
            countLoaded = count;
            if (stealed && stolenWeapon.isEmpty()) {
                logger.info("Loaded " + count + " weapons");
                loaded = true;
                break;
            }
        }
    }

    protected void calculate() {
        int count = 0;
        int sum = 0;
        while (true) {
            synchronized (loadedWeapon) {
                while (loadedWeapon.isEmpty() && !loaded) {
                    try {
                        loadedWeapon.wait();
                    } catch (InterruptedException e) {
                        logger.warn(e);
                    }
                }
                Weapon weapon = loadedWeapon.pop();
                count++;
                logger.info("Calculated (" + count + " of " + countLoaded + ") " + (double) sum / 100 + "+" + (double) weapon.getPrice() / 100 + "=" + (double) (sum + weapon.getPrice()) / 100);
                sum += weapon.getPrice();
                calculatedWeapon.push(weapon);
            }
            countCalculated = count;
            if (loaded && loadedWeapon.isEmpty()) {
                calculated = true;
                price = sum;
                logger.info("Calculated " + count + " weapons");
                logger.info("Total price is " + (double) sum / 100);
                calculated = true;
                break;
            }
        }
    }

    protected void makeDepot() {
        int idx = (int) (Math.random() * 50);
        depot.clear();
        for (int i = 0; i < idx; i++) {
            Weapon weapon = Weapon.getInstance();
            depot.push(weapon);
        }
    }
}
