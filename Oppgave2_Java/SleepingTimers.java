package Oppgave2_Java;

/**
 * Created by ady on 12/11/15.
 */

class SleepingTimers {

    private static final int SLEEP_TIME = 5;

    /**
     * Sleep between zero and duration seconds.
     */
    public static void sleep(int duration) {
        int sleepTime = (int) (duration * Math.random());
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