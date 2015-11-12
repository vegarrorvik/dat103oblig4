package Oppgave2;

public class Consumer implements Runnable{

    private BoundedBuffer buffer;
    private int counter;

    public Consumer(BoundedBuffer b) {
        buffer = b;
    }

    public void run(){
        while (true){
            //System.out.println("Consumer is sleeping");
            Sleeping.sleep();
            //System.out.println("Consumer wants to consume");
            counter = (Integer)buffer.remove();
            System.out.println("Consumer consumed \"" + counter + "\"");
        }
    }
}