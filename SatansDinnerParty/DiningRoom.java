package SatansDinnerParty;

/**
 * Created by Jostein on 11.11.2015.
 */
public class DiningRoom {
    private int vacancy;
    private int n;

    public DiningRoom(int N){
        this.n = N;
        vacancy = n -1;

    }

    public synchronized void enter(){
        while(vacancy == 0){
            try{
                wait();
            }catch(InterruptedException e){
            }
            vacancy--;
        }
    }

    public synchronized void exit(){

        vacancy++;
        notify();

    }
}
