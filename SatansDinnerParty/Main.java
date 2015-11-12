package SatansDinnerParty;

/**
 * Created by Jostein on 11.11.2015.
 */
public class Main {
    public static int N = 5;

    public static void main(String[] args){
        Philosophers[] philosopher = new Philosophers[N];
        Chopsticks chopsticks = new Chopsticks(N);
        DiningRoom diningroom = new DiningRoom(N);

        for(int i = 0;i<N;i++)
            philosopher[i] = new Philosophers(i,10,20,30,100,chopsticks,diningroom);

        for(int i = 0;i<N;i++)
            philosopher[i].start();

    }
}
