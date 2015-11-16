package SatansDinnerParty_v2;

/**
 * Created by Jostein on 16.11.2015.
 */
public class DiningRoom {
    private int[] state;

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

    /**
     * Metode som får filosofen til å plukke opp spisepinnene.
     * Plukker dem opp såfremt han har mulighet til å spise.
     * @param philosopher
     */
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

    /**
     * Metode som får filosofen til å legge ned spisepinnene, og så sjekke
     * om naboene kan spise og om de sulter.
     * @param philosopher Filosofen som legger ned spisepinnene.
     */
    public synchronized void putDownChopsticks(int philosopher) {
        state[philosopher] = THINKING;

        checkIfPossibleToEat(leftNeighbor(philosopher));
        checkIfStarving(leftNeighbor(philosopher));

        checkIfPossibleToEat(rightNeighbor(philosopher));
        checkIfStarving(rightNeighbor(philosopher));

        notifyAll();
    }

    /**
     * Metode som sjekker om en filosof sulter. Han sulter hvis han er sulten,
     * og naboene ikke sulter.
     * @param philosopher Filosofen som skal sjekkes
     */
    private void checkIfStarving(int philosopher) {
        if (state[philosopher] == HUNGRY &&
                state[leftNeighbor(philosopher)] != STARVING &&
                state[rightNeighbor(philosopher)] != STARVING) {
            state[philosopher] = STARVING;
            System.out.println("Filosof " + (philosopher+1) + " sulter.");
        }
    }

    /**
     * Sjekker om en filosof har mulighet til å spise. Han har mulighet dersom
     * han er sulten eller sulter, og naboene ikke er sultne eller sulter.
     * @param philosopher Filosofem som skal sjekkes.
     */
    private void checkIfPossibleToEat(int philosopher) {
        if (state[philosopher] == HUNGRY || state[philosopher] == STARVING){
            if (state[leftNeighbor(philosopher)] != EATING && state[leftNeighbor(philosopher)] != STARVING &&
                    state[rightNeighbor(philosopher)] != EATING && state[rightNeighbor(philosopher)] != STARVING){
                state[philosopher] = EATING;
            }
        }

    }

    /**
     * Finner indeksverdien til naboen til venstre for en filosof
     * @param philosopher Filosofen som skal sjekkes naboene til
     * @return Indeksverdien til naboen til venstre
     */
    private int leftNeighbor(int philosopher) {
        if (philosopher == 0) {
            return NUM_OF_PHILOSOPHERS - 1;
        } else {
            return philosopher - 1;
        }
    }

    /**
     * Finner indeksverdien til naboen til venstre for en filosof
     * @param philosopher Filosofen som skal sjekkes naboene til
     * @return Indeksverdien til naboen til høyre.
     */
    private int rightNeighbor(int philosopher) {
        if (philosopher == NUM_OF_PHILOSOPHERS - 1) {
            return 0;
        } else {
            return philosopher + 1;
        }
    }

}
