package SatansDinnerParty;

/**
 * Created by André, Vegar, Jostein, Simon
 */

public class Chopsticks {
    private int listOfChopsticks[];
    private int spots;

    public Chopsticks(int N){
        this.spots = N;
        listOfChopsticks = new int[N];
        for(int i = 0;i<N;i++)
            listOfChopsticks[i] = 2;
    }

    /**
     * Method that makes the philosopher take the chopsticks.
     * He will wait until he has two of them
     * @param philosopher The philosopher
     */
    public synchronized void takeChopsticks(int philosopher){
        while (listOfChopsticks[philosopher] != 2){
            try{
                wait();
            }catch(InterruptedException e){

            }
        }
        listOfChopsticks[(philosopher+1) % spots]--;
        listOfChopsticks[(Math.abs(philosopher-1)) % spots]--;

    }

    /**
     * Method that makes the philosopher let go of the chopsticks
     * @param philosopher The philosopher
     */
    public synchronized void releaseChopsticks(int philosopher){
        listOfChopsticks[(philosopher+1) % spots]++;
        listOfChopsticks[(Math.abs(philosopher-1)) % spots]++;
        notifyAll();
    }
}
