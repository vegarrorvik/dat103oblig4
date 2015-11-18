package SatansDinnerParty;

import java.util.concurrent.Semaphore;

public class Philosophers extends Thread{
    int index;
    private Chopsticks c;
    private DiningRoom d;

    private final int SLEEPTIME = 750;

    public Philosophers(int i, Chopsticks c, DiningRoom d){
        this.index = i;
        this.c =c;
        this.d = d;
    }

    /**
     * Metode som får filosofene til å tenke
     */
    public void think(){
        try{
            System.out.println("Filosof " + (index+1) + " tenker");
            Thread.sleep(SLEEPTIME);

        }catch(InterruptedException e){

        }
    }

    /**
     * Metode som får filosofene til å spise
     */
    public void eat(){
        try{
            System.out.println("Filosof " + (index+1) + " spiser");
            Thread.sleep(SLEEPTIME);
        }catch(InterruptedException e){

        }
    }

    /**
     * Metode som får filosofene til å gjøre at.
     * Dvs. gå inn/ut av spisesalen, ta/slippe spisepinner og spise/tenke.
     */
    public void run() {
        while (true) {
            think();
            d.enterDiningRoom();
            System.out.println("Filosof " + (index + 1) + " går inn i spisestuen");
            c.takeChopsticks(index);
            eat();
            c.releaseChopsticks(index);
            d.exitDiningRoom();
            System.out.println("Filosof " + (index+1) + " går ut av spisestuen");
        }
    }

}
