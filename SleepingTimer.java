package Oppgave2;

public class SleepingTimers {

    private static final int SLEEP_TIME = 5;

    /**
     * Sleep between zero and duration seconds.
     */
    public static void sleep(int duration) {
        int sleepTime = (int) (duration * Math.random() );
        //System.out.println("SleepingTimers for " + sleepTime + " seconds");
        try { Thread.sleep(sleepTime*1000); }
        catch (InterruptedException e) {
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
