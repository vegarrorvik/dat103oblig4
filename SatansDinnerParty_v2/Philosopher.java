package SatansDinnerParty_v2;

/**
 * Created by Jostein on 16.11.2015.
 */
public class Philosopher extends Thread {
    private DiningRoom runner;
    private int tNum;
    private int sleepTime;

    public Philosopher(DiningRoom t, int i) {
        runner = t;
        tNum = i;
    }

    private void think() {
        System.out.println("Filosof " + (tNum+1) + " tenker.");

        sleepTime = (int) (DiningRoom.NAP_TIME * Math.random());
        try {
            sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
        }
    }

    private void eat() {
        System.out.println("Filosof " + (tNum+1) + " spiser.");

        sleepTime = (int) (DiningRoom.NAP_TIME * Math.random());
        try {
            sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
        }
    }

    public void run() {
        while (true) {
            think();
            System.out.println("Filosof " + (tNum+1) + " er sulten.");
            runner.takeChopsticks(tNum);
            eat();
            runner.putDownChopsticks(tNum);
            System.out.println("Filosof " + (tNum+1) + " er ferdig med å spise.");
        }
    }

}

