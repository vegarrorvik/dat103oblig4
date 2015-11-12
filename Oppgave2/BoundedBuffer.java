package Oppgave2;

public class BoundedBuffer{

    private static final int MAX_BUFFER_SIZE = 5;

    private int nextFreePos;
    private int nextFilledPos;
    private int numberOfItems;

    private Semaphore numberOfEmpty;
    private Semaphore numberOfFull;
    private Semaphore mutex;

    private Object[] buffer;

    public BoundedBuffer(){

        numberOfItems = 0;
        nextFreePos = 0;
        nextFilledPos = 0;

        mutex = new Semaphore(1);
        numberOfEmpty = new Semaphore(MAX_BUFFER_SIZE);
        numberOfFull = new Semaphore(0);

        buffer = new Object[MAX_BUFFER_SIZE];
    }

    public void add(Object item) {

        try{
            numberOfEmpty.acquire();
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
        numberOfFull.release();
    }

    public Object remove() {
        Object item;

        try{
            numberOfFull.acquire();
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
                    + "Producer added the item: \"" + item
                    + "\", number of items in buffer is " + numberOfItems);
        }
        else{
            System.out.println("Producer added the item \"" + item
                    + "\", number of items in buffer is " + numberOfItems);
        }

        mutex.release();
        numberOfEmpty.release();
        return item;
    }

}