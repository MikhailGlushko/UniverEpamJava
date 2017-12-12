package ua.epam.bearandbee;

public class BeerAndBeeDemo {
    public static void main(String[] args) {
        BearAndBee bearAndBee;
        bearAndBee = new BearAndBee(100,10,3);
        bearAndBee.setBearRandomizeInForest();
        bearAndBee.initPartForestQueue(SearchStrategy.FROM_MIDDLE);
        bearAndBee.initBeeWorkersQue();
        int search = bearAndBee.searchBear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The Bear is at "+search);
    }
}
