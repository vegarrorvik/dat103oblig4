package JavaBuffer;

/**
 * Created by ady on 12/11/15.
 */
class Consumer implements Runnable{

    private BoundedBuffer buffer;
    private int counter;

    public Consumer(BoundedBuffer b) {
        buffer = b;
    }

    public void run(){
        for(;;){
            SleepingTimers.sleep();
            try {
                counter = (Integer) buffer.remove();
            }
            catch (NullPointerException e){

            }

        }
    }
}
