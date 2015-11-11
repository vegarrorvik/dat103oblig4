package SatansDinnerParty;

/**
 * Created by Jostein on 11.11.2015.
 */
public class Chopsticks {
    private int numOfChops[];
    private int N;

    public Chopsticks(int N){
        this.N = N;
        numOfChops = new int[N];
        for(int i = 0;i<N;i++)
            numOfChops[i] = 2;

    }

    public synchronized void take(int philosopher){
        while(numOfChops[philosopher] != 2){
            try{
                wait();
            }catch(InterruptedException e){

            }
            //System.out.println("philosopher "+philosopher+"taking");
        }
        numOfChops[(philosopher+1)%N]--;
        numOfChops[(Math.abs(philosopher-1))%N]--;
    }

    public synchronized void release(int philosopher){
        //System.out.println("philosopher "+philosopher+"releasing");
        numOfChops[(philosopher+1)%N]++;
        numOfChops[(Math.abs(philosopher-1))%N]++;
        notifyAll();
    }
}
