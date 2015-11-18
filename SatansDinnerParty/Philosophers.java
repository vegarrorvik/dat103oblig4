package SatansDinnerParty;

/**
 * Created by André, Vegar, Jostein, Simon
 */

public class Philosophers extends Thread implements Runnable{
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
     * Method that makes the philosopher think
     */
    public void think(){
        try{
            System.out.println("Filosof " + (index+1) + " tenker");
            Thread.sleep(SLEEPTIME);

        }catch(InterruptedException e){

        }
    }

    /**
     * Method that makes the philosopher eat
     */
    public void eat(){
        try{
            System.out.println("Filosof " + (index+1) + " spiser");
            Thread.sleep(SLEEPTIME);
        }catch(InterruptedException e){

        }
    }

    @Override
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
