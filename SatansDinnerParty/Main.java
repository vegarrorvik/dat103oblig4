package SatansDinnerParty;

/**
 * Created by Jostein on 11.11.2015.
 */
public class Main {
    public static void main(String[] args){
        Philosophers[] philosopher = new Philosophers[5];
        Chopsticks chopsticks = new Chopsticks(5);
        DiningRoom diningroom = new DiningRoom(5);

        for (int i = 0; i < 5; i++){
            philosopher[i] = new Philosophers(i, 200, 400, 200, 400, chopsticks, diningroom);
        }

        for(int i = 0;i<5;i++)
            philosopher[i].start();

    }
}
