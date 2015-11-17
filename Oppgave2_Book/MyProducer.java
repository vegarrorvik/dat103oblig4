package Oppgave2_Book;

/**
 * Created by ady on 12/11/15.
 */
class MyProducer implements Runnable{

    private MyBoundedBuffer buffer;
    private int counter = 0;

    public MyProducer(MyBoundedBuffer b) {
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

