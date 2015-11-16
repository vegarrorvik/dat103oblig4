package SatansDinnerParty_v2;

/**
 * Created by Jostein on 16.11.2015.
 */
public class DiningRoom {
    private int[] state;

    public static final int NAP_TIME = 5;
    public static final int NUM_OF_PHILOSOPHERS = 5;

    private static final int THINKING = 0;
    private static final int HUNGRY = 1;
    private static final int EATING = 2;
    private static final int STARVING = 3;

    public DiningRoom() {
        state = new int[NUM_OF_PHILOSOPHERS];

        for (int i = 0; i < NUM_OF_PHILOSOPHERS; i++)
            state[i] = THINKING;
    }

    public synchronized void takeChopsticks(int philosopher) {
        state[philosopher] = HUNGRY;
        checkIfPossibleToEat(philosopher);

        while (state[philosopher] != EATING) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void putDownChopsticks(int philosopher) {
        state[philosopher] = THINKING;

        checkIfPossibleToEat(leftNeighbor(philosopher));
        checkIfStarving(leftNeighbor(philosopher));

        checkIfPossibleToEat(rightNeighbor(philosopher));
        checkIfStarving(rightNeighbor(philosopher));

        notifyAll();
    }

    /**
     * A philosopher is starving if they are hungry and their
     * left and right neighbors aren't starving.
     */
    private void checkIfStarving(int i) {
        if (state[i] == HUNGRY &&
                state[leftNeighbor(i)] != STARVING &&
                state[rightNeighbor(i)] != STARVING) {
            state[i] = STARVING;
            System.out.println("Filosof " + (i+1) + " sulter.");
        }
    }

    private void checkIfPossibleToEat(int i) {
        if (state[i] == HUNGRY || state[i] == STARVING){
            if (state[leftNeighbor(i)] != EATING && state[leftNeighbor(i)] != STARVING &&
                    state[rightNeighbor(i)] != EATING && state[rightNeighbor(i)] != STARVING){
                state[i] = EATING;
            }
        }

    }

    private int leftNeighbor(int i) {
        if (i == 0) {
            return NUM_OF_PHILOSOPHERS - 1;
        } else {
            return i - 1;
        }
    }

    private int rightNeighbor(int i) {
        if (i == NUM_OF_PHILOSOPHERS - 1) {
            return 0;
        } else {
            return i + 1;
        }
    }

}
