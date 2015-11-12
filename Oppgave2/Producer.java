/**
 * Created by ady on 12/11/15.
 */
public class Producer implements Runnable{

    private BoundedBuffer buffer;
    private int counter = 0;

    public Producer(BoundedBuffer b) {
        buffer = b;
    }

    public void run(){
        for(;;) {
            //System.out.println("Producer is sleeping");
            SleepingTimers.sleep();
            //System.out.println("Producer produced \"" + counter + "\"");
            buffer.add(counter);
            counter++;
        }
    }
}

