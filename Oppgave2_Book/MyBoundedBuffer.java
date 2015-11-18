package MyBuffer;

/**
 * Created by ady on 12/11/15.
 */
public class MyBoundedBuffer {

    private static final int MAX_BUFFER_SIZE = 5;

    private int nextFreePos;
    private int nextFilledPos;
    private int numberOfItems;

    private MySemaphore mutex;

    private Object[] buffer;

    public MyBoundedBuffer() {

        numberOfItems = 0;
        nextFreePos = 0;
        nextFilledPos = 0;

        buffer = new Object[MAX_BUFFER_SIZE];
        mutex = new MySemaphore(1);
    }

    public void add(Object item) {

        mutex.w8();

        if(numberOfItems < MAX_BUFFER_SIZE) {

            ++numberOfItems;
            buffer[nextFreePos] = item;
            nextFreePos = (nextFreePos + 1) % MAX_BUFFER_SIZE;

            if (numberOfItems == MAX_BUFFER_SIZE) {
                System.out.println("BUFFER IS FULL! "
                        + "Producer added the item: \"" + item
                        + "\", number of items in buffer is " + numberOfItems);
            } else if (numberOfItems < MAX_BUFFER_SIZE) {
                System.out.println("Producer added the item: \"" + item
                        + "\", number of items in buffer is " + numberOfItems);
            }
        }
        mutex.signal();
    }

    public Object remove() {
        if(numberOfItems > 0) {
            Object item;

            mutex.w8();
            --numberOfItems;
            item = buffer[nextFilledPos];
            nextFilledPos = (nextFilledPos + 1) % MAX_BUFFER_SIZE;

            if (numberOfItems == 0) {
                System.out.println("BUFFER IS EMPTY! "
                        + "Consumer removed the item: \"" + item
                        + "\", number of items in buffer is " + numberOfItems);
            } else if (numberOfItems > 0) {
                System.out.println("Consumer removed the item \"" + item
                        + "\", number of items in buffer is " + numberOfItems);
            }

            mutex.signal();
            return item;
        }
        return null;
}

}