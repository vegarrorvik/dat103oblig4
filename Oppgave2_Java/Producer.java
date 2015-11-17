package Oppgave2_Java;

/**
 * Created by ady on 12/11/15.
 */
class Producer implements Runnable{

    private BoundedBuffer buffer;
    private int counter = 0;

    public Producer(BoundedBuffer b) {
        buffer = b;
    }

    public void run(){
        for(;;) {
            SleepingTimers.sleep();
            try {
                buffer.add(counter);
            }catch (NullPointerException e){

            }
            counter++;
        }
    }
}

