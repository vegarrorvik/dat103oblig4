package Oppgave2;

public class Producer implements Runnable{

    private BoundedBuffer buffer;
    private int counter = 0;

    public Producer(BoundedBuffer b) {
        buffer = b;
    }

    public void run(){
        while (true) {
            //System.out.println("Producer is sleeping");
            Sleeping.sleep();
            //System.out.println("Producer produced \"" + counter + "\"");
            buffer.add(counter);
            counter++;
        }
    }
}