package ua.epam.bearandbee;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

import java.util.*;

public class BearAndBee {

    static Logger logger = Logger.getLogger(BearAndBee.class);

    protected final int treeCount;
    protected final int partCount;
    protected final int beeCount;
    private boolean[] forest;
    private final Queue<Integer> parts = new LinkedList<>();
    private final Queue<Integer> beeWorkers = new LinkedList<>();
    private volatile int beerNumber = -1;

    public BearAndBee(int treeCount, int partCount, int beeCount) {
        this.treeCount = treeCount;
        forest = new boolean[treeCount];
        this.partCount=partCount;
        this.beeCount=beeCount;
    }

    public int searchBear() {
        int part;
        while ((part = getPartOfForestFromQue()) != -1 && beerNumber == -1) {
            logger.info("Part "+part/partCount + " need to check");
            waitBeeWorkerWhereReturnFromSearch();
            startBeeWorkerToSearch(part);
        }
        return beerNumber;
    }

    protected int getPartOfForestFromQue() {
        if (!parts.isEmpty())
            return parts.poll();
        return -1;
    }

    private void waitBeeWorkerWhereReturnFromSearch() {
        while (beeWorkers.isEmpty()) {
            synchronized (beeWorkers) {
                try {
                    logger.info("No free Bee. Waiting to next one");
                    beeWorkers.wait();
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
        }
    }

    private void startBeeWorkerToSearch(int partOfForest) {
        if (!beeWorkers.isEmpty() && beerNumber == -1) {
            Integer bee = beeWorkers.poll();
            logger.info("The Bee "+bee+" is ready to work in part "+partOfForest/partCount);
            new Thread() {
                @Override
                public void run() {
                    setName("The Bee " + (bee + 1));
                    logger.info(Thread.currentThread().getName() + " fly out");
                    int delta = treeCount / partCount;
                    int index = searchBearInThePart(partOfForest, partOfForest + delta);
                    if(index != -1) {
                        synchronized (beeWorkers) {
                            beerNumber = index;
                            logger.info("The Bear is found! Stop find! "+beerNumber);
                        }
                    }
                    restoreBeeWorkerAfterReturn(bee);
                }
            }.start();
        }
    }

    protected int searchBearInThePart(int from, int to) {
        String beeName = Thread.currentThread().getName();
        int index = -1;
        int searchTime = 100;
        for (int i = from; i < to; i++) {
            logger.info(beeName + " is looking the Bear near the tree number: " + i);
            try {
                Thread.sleep(searchTime);
            } catch (InterruptedException e) {
                logger.error(e);
            }
            if (forest[i]) {
                logger.info(beeName + " found the Bear at " + i);
                index = i;
                break;
            }
            if(i==to-1){
                logger.info("The Bear not found");
            }
        }
        logger.info(beeName + " is flying home");
        try {
            Thread.sleep(searchTime);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return index;
    }
    
    private void restoreBeeWorkerAfterReturn(Integer bee) {
        synchronized (beeWorkers) {
            beeWorkers.add(bee);
            beeWorkers.notifyAll();
            logger.info(Thread.currentThread().getName() + " is at home and ready");
        }
    }

    protected void initBeeWorkersQue() {
        beeWorkers.clear();
        for (int i = 0; i < beeCount; i++) {
            beeWorkers.add(i);
        }
    }

    protected void initPartForestQueue(SearchStrategy strategy) {
        parts.clear();
        switch (strategy){
            case RANDOMIZE:
                Set<Integer> set = new HashSet<>();
                do {
                    int index = (int) (Math.random() * partCount);
                    if(!set.contains(index)) {
                        set.add(index);
                        parts.add(index * treeCount / partCount);
                    }
                } while (set.size()!=partCount);
                break;
            case FROM_MIDDLE:
                for (int i=partCount/2; i<partCount; i++){
                    parts.add(i * treeCount / partCount);
                    if(partCount-i-1>=0 && partCount-i-1!=partCount/2)
                      parts.add((partCount-i-1) * treeCount / partCount);
                }
                break;
            case FROM_BOTTOM:
                for (int i=partCount-1; i>=0; i--)
                    parts.add(i * treeCount / partCount);
                break;
            case FROM_TOP:
                for (int i = 0; i < partCount; i++)
                    parts.add(i * treeCount / partCount);
                break;
        }
    }
    
    protected void setBearRandomizeInForest() {
        forest = new boolean[treeCount];
        int bearCount = 1;
        for (int i = 0; i < bearCount; i++) {
            int index;
            do {
                index = (int) (Math.random() * treeCount);
                forest[index] = true;
                logger.info("I am The Bear. I hid at "+index+". Find me ...");
            } while (!forest[index]);
        }
    }

    public int getBeerNumber() {
        return beerNumber;
    }
}
