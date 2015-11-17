package Oppgave2_Book;

import JavaBuffer.BoundedBuffer;

/**
 * Created by ady on 12/11/15.
 */

class MyConsumer implements Runnable{

    private MyBoundedBuffer buffer;
    private int counter;

    public MyConsumer(MyBoundedBuffer b) {
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
