package SatansDinnerParty_v3;

/**
 * Created by André, Vegar, Jostein, Simon
 */
public class MySemaphore {

    int s;
    public MySemaphore(int s){
        this.s=s;
    }

    public void w8(){
        while (s <= 0){

        }
        s--;
    }

    public void signal(){
        s++;
    }

    public String toString(){
        return s + "";
    }
}
