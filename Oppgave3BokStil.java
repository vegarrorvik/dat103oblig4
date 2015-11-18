/**
 * Created by Vegar on 13.11.2015.
 */
public class Oppgave3BokStil {
    public static int writers = 1;
    public static int readers = 2;

    public static void main(String[]args) {
        aDatabase database = new aDatabase();

        Thread[] readerArray = new Thread[readers];
        Thread[] writerArray = new Thread[writers];

        for(int i = 0; i<readers;i++){
            readerArray[i] = new Thread(new aReader(i,database));
            readerArray[i].start();
        }

        for(int i = 0; i<writers;i++){
            writerArray[i] = new Thread(new aWriter(i,database));
            writerArray[i].start();
        }
    }
}

class MySemaphore{
    MyInt i;

    public MySemaphore(MyInt i){
        this.i = i;
    }
    public synchronized void signal(){
        i.setI(i.getI()+1);
    }

    public synchronized void w8(){
        while(i.getI() <= 0){
        }i.setI(i.getI()-1);
    }
}

class aDatabase{
    MyInt myInt = new MyInt(1);
    MyInt myInt2 = new MyInt(1);
    MySemaphore mutex = new MySemaphore(myInt);
    MySemaphore readerWriterMutex = new MySemaphore(myInt2);
    int readcount = 0;

    public void read(){
        mutex.w8();

        readcount++;
        System.out.println("Readcount is " + readcount);
        if(readcount==1 && (readerWriterMutex.i.getI()==1))
            readerWriterMutex.w8();

        System.out.println("rwmutex is " + readerWriterMutex.i.getI());
        mutex.signal();
    }

    public void write(){
        readerWriterMutex.w8();
        mutex.w8();
    }

    public void readRelease(){
        readcount--;
        System.out.println("Readcount is " + readcount);
        if(readcount==0)
            readerWriterMutex.signal();
        System.out.println("rwmutex is " + readerWriterMutex.i.getI());
    }

    public void writeRelease(){
        readerWriterMutex.signal();
        mutex.signal();
        System.out.println("MUTEXCOUNT IS " + mutex.i.getI());
        System.out.println("rwmutex is " + readerWriterMutex.i.getI());
    }
}

class aWriter implements Runnable{
    int writer;
    aDatabase dt;

    public aWriter(int writer, aDatabase dt){
        this.writer = writer;
        this.dt = dt;
    }
    @Override
    public void run() {
        for(;;) {
            aSleepingTimers.sleep();

            System.out.println("Writer " + writer + " wants to write!");
            dt.write();
            System.out.println("Writer " + writer + " is writing");

            aSleepingTimers.sleep();
            dt.writeRelease();
            System.out.println("Writer " + writer + " is done writing.");
        }
    }
}

class aReader implements Runnable{
    int reader;
    aDatabase dt;

    public aReader(int reader, aDatabase dt){
        this.reader = reader;
        this.dt = dt;
    }

    @Override
    public void run(){
        for(;;) {
            aSleepingTimers.sleep();

            System.out.println("Reader " + reader + " wants to read!");
            dt.read();
            System.out.println("Reader " + reader + " is reading!");

            aSleepingTimers.sleep();
            dt.readRelease();
            System.out.println("Reader " + reader + " is done reading.");
        }
    }
}

/**
 * SleepingTimers, same code that is in SleepingTimers.java in the zip
 */
class aSleepingTimers {

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

class MyInt{
    int i;

    public MyInt(int i ){
        this.i = i;
    }

    public int getI() {
        return i;
    }
    public void setI(int i){
        this.i = i;
    }
}
