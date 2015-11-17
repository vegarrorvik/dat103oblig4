package SatansDinnerParty;

public class MySemaphore {

    public MySemaphore(){

    }

    public void w8(int s){
        while (s <=1){

        }
        s++;
    }

    public void signal(int s){
        s++;
    }
}
