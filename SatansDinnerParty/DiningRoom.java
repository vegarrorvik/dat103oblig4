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

    /**
     * Metode som får filosofene til å gå inn i spisesalen
     * slik at de kan spise.
     */
    public synchronized void enterDiningRoom(){
        while(vacancy == 0){
            try{
                wait();
            }catch(InterruptedException e){
            }
            vacancy--;
        }
    }

    /**
     * Metode som får filosofene til å gå ut av spisesalen
     * slik at andre filosofer kan gå inn og spise
     */
    public synchronized void exitDiningRoom(){
        vacancy++;
        notifyAll();
    }
}
