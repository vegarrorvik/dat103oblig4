package Oppgave2;

public class SemaphoreBoudedBuffer{
    public static void main(String args[]) {

        BoundedBuffer myBuffer = new BoundedBuffer();

        Thread producerThread = new Thread(new Producer(myBuffer));
        Thread consumerThread = new Thread(new Consumer(myBuffer));

        producerThread.start();
        consumerThread.start();
    }
}
