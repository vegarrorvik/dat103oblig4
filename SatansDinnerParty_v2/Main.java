package SatansDinnerParty_v2;

public class Main {
    public static void main(String args[]) {
        DiningRoom diningroom = new DiningRoom();
        final int PHILOSOPHERS = DiningRoom.PHILOSOPHERS;

        Philosopher[] philosopher = new Philosopher[PHILOSOPHERS];

        for (int i = 0; i < PHILOSOPHERS; i++)
            philosopher[i] = new Philosopher(diningroom,i);

        for (int i = 0; i < PHILOSOPHERS; i++)
            philosopher[i].start();
    }
}
