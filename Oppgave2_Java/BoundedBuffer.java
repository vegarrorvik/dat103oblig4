package Oppgave2_Java;

import java.util.concurrent.Semaphore;

/**
 * Created by ady on 12/11/15.
 */
public class BoundedBuffer{

    private static final int MAX_BUFFER_SIZE = 5;

    private int nextFreePos;
    private int nextFilledPos;
    private int numberOfItems;

    private Semaphore indexEmpty;
    private Semaphore indexFull;
    private Semaphore mutex;

    private Object[] buffer;

    public BoundedBuffer(){

        numberOfItems = 0;
        nextFreePos = 0;
        nextFilledPos = 0;

        mutex = new Semaphore(1);
        indexEmpty = new Semaphore(MAX_BUFFER_SIZE);
        indexFull = new Semaphore(0);

        buffer = new Object[MAX_BUFFER_SIZE];
    }

    public void add(Object item) {

        try{
            indexEmpty.acquire();
            mutex.acquire();
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }

        ++numberOfItems;
        buffer[nextFreePos] = item;
        nextFreePos = (nextFreePos + 1) % MAX_BUFFER_SIZE;

        if (numberOfItems == MAX_BUFFER_SIZE){
            System.out.println("BUFFER IS FULL! "
                    + "Producer added the item: \"" + item
                    + "\", number of items in buffer is " + numberOfItems);
        }
        else{
            System.out.println("Producer added the item: \"" + item
                    + "\", number of items in buffer is " + numberOfItems);
        }

        mutex.release();
        indexFull.release();
    }

    public Object remove() {
        Object item;

        try{
            indexFull.acquire();
            mutex.acquire();
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }

        --numberOfItems;
        item = buffer[nextFilledPos];
        nextFilledPos = (nextFilledPos + 1) % MAX_BUFFER_SIZE;

        if (numberOfItems == 0){
            System.out.println("BUFFER IS EMPTY! "
                    + "Consumer removed the item: \"" + item
                    + "\", number of items in buffer is " + numberOfItems);
        }
        else{
            System.out.println("Consumer removed the item \"" + item
                    + "\", number of items in buffer is " + numberOfItems);
        }

        mutex.release();
        indexEmpty.release();
        return item;
    }

}