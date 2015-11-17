package Oppgave2_Book;

public class MySemaphoreBoundedBuffer {
    public static void main(String args[]) {

        MyBoundedBuffer myBuffer = new MyBoundedBuffer();

        Thread producerThread = new Thread(new MyProducer(myBuffer));
        Thread consumerThread = new Thread(new MyConsumer(myBuffer));

        producerThread.start();
        consumerThread.start();
    }
}