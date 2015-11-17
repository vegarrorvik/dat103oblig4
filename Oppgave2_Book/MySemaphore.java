package Oppgave2_Book;

/**
 * Created by ady on 12/11/15.
 */
public class MySemaphore {

    public MySemaphore(){

    }

    public void w8(int s){
        while (s <=0){

        }
        s--;
    }

    public void signal(int s){
        s++;
    }
}
