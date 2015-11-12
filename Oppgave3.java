import java.util.concurrent.Semaphore;

/**
 * Created by Vegar, Jostein, André and Simon on 12.11.2015.
 */
public class Oppgave3 {
    public static int writers = 1;
    public static int readers = 2;

    public static void main(String[]args) {
        ReaderWriterLock database = new DataBase();

        Thread[] readerArray = new Thread[readers];
        Thread[] writerArray = new Thread[writers];

        for(int i = 0; i<readers;i++){
            readerArray[i] = new Thread(new Reader(i,database));
            readerArray[i].start();
        }

        for(int i = 0; i<writers;i++){
            writerArray[i] = new Thread(new Writer(i,database));
            writerArray[i].start();
        }
    }
}

interface ReaderWriterLock{
    public abstract void acquireReadLock(int readerNum);
    public abstract void acquireWriteLock(int writerNum);
    public abstract void releaseReadLock(int readerNum);
    public abstract void releaseWriteLock(int writerNum);
}

/**
 * Database with the methodes in RWLock interface
 * Uses semaphores to control reading and writing permissions
 */
class DataBase implements ReaderWriterLock{
    private int readCount = 0;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore rwMutex = new Semaphore(1);

    @Override
    public void acquireReadLock(int readerNum) {
        try{
            mutex.acquire();
        }
        catch(InterruptedException e){}

        ++readCount;

        if(readCount == 1){
            try{
                rwMutex.acquire();
            }catch(InterruptedException e){}
        }

        System.out.println("Reader " + readerNum + " is reading. Reader count is " + readCount);

        mutex.release();
    }

    @Override
    public void acquireWriteLock(int writerNum) {
        try{
            rwMutex.acquire();
        }catch (InterruptedException e){}

        System.out.println("Writer " + writerNum + " is writing.");

    }

    @Override
    public void releaseReadLock(int readerNum) {
        try{
            mutex.acquire();
        }catch (InterruptedException e){}

        --readCount;

        if(readCount == 0){
            if(readCount == 0){
                rwMutex.release();
            }
        }

        System.out.println("Reader " + readerNum + " is done reading. Reader count is " + readCount);

        mutex.release();
    }

    @Override
    public void releaseWriteLock(int writerNum) {
        System.out.println("Writer " + writerNum + " is done writing");
        rwMutex.release();
    }
}

/**
 * Writer class that is runnable
 */
class Writer implements Runnable{

    private ReaderWriterLock database;
    private int writer;

    public Writer(int writer, ReaderWriterLock database){
        this.writer = writer;
        this.database = database;
    }

    @Override
    public void run() {
        for(;;){
            SleepingTimers.sleep();

            System.out.println("Writer " + writer + " wants to write!");
            database.acquireWriteLock(writer);

            SleepingTimers.sleep();

            database.releaseWriteLock(writer);
        }
    }
}

/**
 * Reader class that is runnable
 */
class Reader implements Runnable{
    private ReaderWriterLock database;
    private int reader;

    public Reader(int reader,ReaderWriterLock database){
        this.reader = reader;
        this.database = database;
    }


    @Override
    public void run() {
        for(;;){
            SleepingTimers.sleep();

            System.out.println("Reader " + reader + " wants to read!");
            database.acquireReadLock(reader);

            SleepingTimers.sleep();

            database.releaseReadLock(reader);
        }
    }
}

/**
 * SleepingTimers, same code that is in SleepingTimers.java in the zip
 */
class SleepingTimers {

    private static final int SLEEP_TIME = 5;

    /**
     * Sleep between zero and duration seconds.
     */
    public static void sleep(int duration) {
        int sleepTime = (int) (duration * Math.random());
        //System.out.println("SleepingTimers for " + sleepTime + " seconds");
        try {
            Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * Sleep between zero and SLEEP_TIME seconds.
     */
    public static void sleep() {
        sleep(SLEEP_TIME);
    }
}