package SatansDinnerParty;


/**
 * Created by Jostein on 11.11.2015.
 */
public class Chopsticks {
    private int numberOfChopsticks[];
    private int N;

    public Chopsticks(int N){
        this.N = N;
        numberOfChopsticks = new int[N];
        for(int i = 0;i<N;i++)
            numberOfChopsticks[i] = 2;
    }

    /**
     * Metode som får filosofene til å ta spisepinnene
     * @param philosopher Filosofen
     */
    public synchronized void takeChopsticks(int philosopher){
        while(numberOfChopsticks[philosopher] != 2){
            try{
                wait();
            }catch(InterruptedException e){

            }
        }
        numberOfChopsticks[(philosopher+1)%N]--;
        numberOfChopsticks[(Math.abs(philosopher-1))%N]--;
    }

    /**
     * Metode som får filosofene til å slippe spisepinnene
     * @param philosopher Filosofen
     */
    public synchronized void releaseChopsticks(int philosopher){
        numberOfChopsticks[(philosopher+1)%N]++;
        numberOfChopsticks[(Math.abs(philosopher-1))%N]++;
        notifyAll();
    }
}
