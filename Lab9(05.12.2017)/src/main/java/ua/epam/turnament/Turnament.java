package ua.epam.turnament;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Turnament {

    static Logger logger = Logger.getLogger(Turnament.class);

    private static final int CZI = 1000;

    public final int playersCount = 10;
    private int[] players;
    private Queue<Integer> chart = new LinkedList<>();
    private volatile int loserCount = 0;
    private volatile int battleNumber = 0;

    public Turnament() {
        makePlayers();
        chart.clear();
        makeChart(0,playersCount-1);
    }

    public void start(){
        while (loserCount<playersCount-2){
            int playerOne;
            int playerTwo;
            synchronized (chart){
                while (loserCount<playersCount-1 && chart.size()<2){
                    try {
                        chart.wait();
                    } catch (InterruptedException e) {
                        logger.warn(e);
                    }
                }
                playerOne = chart.poll();
                playerTwo = chart.poll();
            }
            new Thread(){
                @Override
                public void run() {
                    battleNumber++;
                    Thread.currentThread().setName("Battle "+battleNumber);
                    int result = battle(playerOne,playerTwo);
                    loserCount++;
                    synchronized (chart){
                        chart.add(result);
                        chart.notify();
                    }
                }
            }.start();
        }
    }

    public void makeChart(int start, int end){
        int count = end-start;

        if(count==0) {
            synchronized (chart){
                chart.add(start);
                logger.info("Player "+start+" added to chart");
                chart.notify();
            }
        } else if(count==1) {
            synchronized (chart){
                chart.add(start);
                chart.add(end);
                logger.info("Player "+start+" added to chart");
                logger.info("Player "+end+" added to chart");
                chart.notify();
            }
        } else {
            int middle = (start + end) / 2;
            makeChart(start, middle);
            makeChart(middle + 1, end);
        }
    }

    protected int battle(int one, int two){
        int idx = (int)(Math.random()*2);
        int winner = -1;
        if(idx==0) {
            winner = one;
            players[one] += players[two];
            players[two] = 0;
        }
        else {
            winner = two;
            players[two] += players[one];
            players[one] = 0;
        }
        logger.info("Battle "+(battleNumber)+" betwinn players "+one+" and "+two+" won player "+winner + " total czi is: "+players[winner]);
        return winner;
    }

    protected void makePlayers(){
        logger.info("Create "+playersCount+" players");
     players = new int[playersCount];
     for (int i=0; i<playersCount; i++){
         int idx = (int)(Math.random()*CZI);
         players[i] = idx;
     }
    }

    public int getLoserCount() {
        return loserCount;
    }
}
