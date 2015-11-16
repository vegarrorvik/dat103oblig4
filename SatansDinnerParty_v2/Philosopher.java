package SatansDinnerParty_v2;

/**
 * Created by Jostein on 16.11.2015.
 */
public class Philosopher extends Thread {
    private DiningRoom diningRoom;
    private int philosopherIndex;
    private final int SLEEPTIME = 750;

    public Philosopher(DiningRoom t, int i) {
        diningRoom = t;
        philosopherIndex = i;
    }

    /**
     * Metode som får filosofen til å tenke.
     */
    private void think() {
        System.out.println("Filosof " + (philosopherIndex +1) + " tenker.");

        try {
            sleep(SLEEPTIME);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Metode som får filosofen til å spise
     */
    private void eat() {
        System.out.println("Filosof " + (philosopherIndex +1) + " spiser.");

        try {
            sleep(SLEEPTIME);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Metode som kjører alt. Dvs. får filosofen til å tenke/spise og ta/legge ned spisepinner
     */
    public void run() {
        while (true) {
            think();
            System.out.println("Filosof " + (philosopherIndex +1) + " er sulten.");
            diningRoom.takeChopsticks(philosopherIndex);
            eat();
            diningRoom.putDownChopsticks(philosopherIndex);
            System.out.println("Filosof " + (philosopherIndex +1) + " er ferdig med å spise.");
        }
    }

}

