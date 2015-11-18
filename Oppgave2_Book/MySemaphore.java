package MyBuffer;

/**
 * Created by ady on 12/11/15.
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
}
