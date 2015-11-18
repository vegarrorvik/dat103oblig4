package SatansDinnerParty_v2;

public class DiningRoom {
    public static final int PHILOSOPHERS = 5;
    private static final int THINKING = 0;
    private static final int HUNGRY = 1;
    private static final int EATING = 2;
    private static final int STARVING = 3;

    private int[] philosopherState;

    public DiningRoom() {
        philosopherState = new int[PHILOSOPHERS];

        for (int i = 0; i < PHILOSOPHERS; i++)
            philosopherState[i] = THINKING;
    }

    /**
     * Metode som sjekker om en filosof sulter. Han sulter hvis han er sulten,
     * og naboene ikke sulter.
     * @param philosopher Filosofen som skal sjekkes
     */
    private void checkIfStarving(int philosopher) {
        if (philosopherState[philosopher] == HUNGRY &&
                philosopherState[leftNeighbor(philosopher)] != STARVING &&
                philosopherState[rightNeighbor(philosopher)] != STARVING) {
            philosopherState[philosopher] = STARVING;
            System.out.println("Filosof " + (philosopher+1) + " sulter.");
        }
    }

    /**
     * Sjekker om en filosof har mulighet til å spise. Han har mulighet dersom
     * han er sulten eller sulter, og naboene ikke er sultne eller sulter.
     * @param philosopher Filosofem som skal sjekkes.
     */
    private void checkIfPossibleToEat(int philosopher) {
        if (philosopherState[philosopher] == HUNGRY || philosopherState[philosopher] == STARVING){
            if (philosopherState[leftNeighbor(philosopher)] != EATING && philosopherState[leftNeighbor(philosopher)] != STARVING &&
                    philosopherState[rightNeighbor(philosopher)] != EATING && philosopherState[rightNeighbor(philosopher)] != STARVING){
                philosopherState[philosopher] = EATING;
            }
        }
    }

    /**
     * Metode som får filosofen til å plukke opp spisepinnene.
     * Plukker dem opp såfremt han har mulighet til å spise.
     * @param philosopher Filosofen som skal ta spisepinnene
     */
    public synchronized void takeChopsticks(int philosopher) {
        philosopherState[philosopher] = HUNGRY;
        checkIfPossibleToEat(philosopher);

        while (philosopherState[philosopher] != EATING) {
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
        philosopherState[philosopher] = THINKING;

        checkIfPossibleToEat(rightNeighbor(philosopher));
        checkIfStarving(rightNeighbor(philosopher));

        checkIfPossibleToEat(leftNeighbor(philosopher));
        checkIfStarving(leftNeighbor(philosopher));

        notifyAll();
    }

    /**
     * Finner indeksverdien til naboen til høyre for en filosof
     * @param philosopher Filosofen som skal sjekkes naboene til
     * @return Indeksverdien til naboen til høyre.
     */
    private int rightNeighbor(int philosopher) {
        if (philosopher == PHILOSOPHERS - 1) {
            return 0;
        } else {
            return philosopher + 1;
        }
    }

    /**
     * Finner indeksverdien til naboen til venstre for en filosof
     * @param philosopher Filosofen som skal sjekkes naboene til
     * @return Indeksverdien til naboen til venstre
     */
    private int leftNeighbor(int philosopher) {
        if (philosopher == 0) {
            return PHILOSOPHERS - 1;
        } else {
            return philosopher - 1;
        }
    }

}
