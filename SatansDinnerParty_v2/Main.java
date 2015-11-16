package SatansDinnerParty_v2;

/**
 * Created by Jostein on 16.11.2015.
 */
public class Main {
    public static void main(String args[])
    {
        DiningRoom diningroom = new DiningRoom();

        Philosopher[] philosopher = new Philosopher[DiningRoom.NUM_OF_PHILOSOPHERS];

        for (int i = 0; i < DiningRoom.NUM_OF_PHILOSOPHERS; i++)
            philosopher[i] = new Philosopher(diningroom,i);

        for (int i = 0; i < DiningRoom.NUM_OF_PHILOSOPHERS; i++)
            philosopher[i].start();
    }
}
