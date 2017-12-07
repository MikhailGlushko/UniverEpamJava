package ua.epam.bearandbee;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class BearAndBeeTest {

    BearAndBee bearAndBee;

    @Test
    @Before
    public void setBeer() {
        bearAndBee = new BearAndBee(100,10,3);
        bearAndBee.setBearRandomizeInForest();
    }

    @Test
    public void getBeer() {
        int i=0;
            bearAndBee.searchBearInThePart(i*10,i*10+10);
    }

    @Test
    public void setPartQueue(){
        bearAndBee.initPartForestQueue(SearchStrategy.FROM_TOP);
        bearAndBee.initPartForestQueue(SearchStrategy.FROM_BOTTOM);
        bearAndBee.initPartForestQueue(SearchStrategy.FROM_MIDDLE);
        bearAndBee.initPartForestQueue(SearchStrategy.RANDOMIZE);
    }

    @Test
    public void getPartQue(){
        bearAndBee.initPartForestQueue(SearchStrategy.FROM_TOP);
        for (int i = 0; i< bearAndBee.partCount; i++){
            int partQue = bearAndBee.getPartOfForestFromQue();
        }
    }

    @Test
    public void initBeeQue(){
        bearAndBee.initBeeWorkersQue();
    }

    @Test
    public void search() {
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
