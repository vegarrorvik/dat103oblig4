package SatansDinnerParty_v3;

/**
 * Created by André, Vegar, Jostein, Simon
 */
public class Main {
    public static MySemaphore[] chopsticks = new MySemaphore[5];
    public static void main(String[]args) {
        final int NUMBER = 5;

        for (int i = 0; i < NUMBER; i++){
            chopsticks[i] = new MySemaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[NUMBER];

        for (int i = 0; i < NUMBER; i++){
            philosophers[i] = new Philosopher(i);
        }


        for (int i = 0; i < NUMBER; i++){
            philosophers[i].start();
        }
    }
}
