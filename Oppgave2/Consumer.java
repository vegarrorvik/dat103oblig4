/**
 * Created by ady on 12/11/15.
 */
public class Consumer implements Runnable{

    private BoundedBuffer buffer;
    private int counter;

    public Consumer(BoundedBuffer b) {
        buffer = b;
    }

    public void run(){
        for(;;){
            //System.out.println("Consumer is sleeping");
            SleepingTimers.sleep();
            //System.out.println("Consumer wants to consume");
            counter = (Integer)buffer.remove();
            System.out.println("Consumer consumed \"" + counter + "\"");
        }
    }
}
