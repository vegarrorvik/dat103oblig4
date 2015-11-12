package SatansDinnerParty;

/**
 * Created by Jostein on 11.11.2015.
 */
public class Philosophers extends Thread{
    int i;
    int minThinkTime,maxThinkTime,minEatTime,maxEatTime;
    private Chopsticks c;
    private DiningRoom d;

    public Philosophers(int index, int minThinkTime, int maxThinkTime, int minEatTime, int maxEatTime, Chopsticks chopsticks, DiningRoom diningroom){
        this.i = index;
        this.minThinkTime = minThinkTime;
        this.maxThinkTime = maxThinkTime;
        this.minEatTime = minEatTime;
        this.maxEatTime = maxEatTime;
        this.c = chopsticks;
        this.d = diningroom;
    }

    public void think(){
        try{
            System.out.println(i+1 + " Philosopher is thinking!");
            Thread.sleep((int)(Math.random()*(maxThinkTime - minThinkTime))+minThinkTime);

        }catch(InterruptedException e){

        }
    }

    public void eat(){
        try{
            System.out.println(i+" Philosopher is eating!");
            Thread.sleep((int)(Math.random()*(maxEatTime - minEatTime))+minEatTime);

        }catch(InterruptedException e){

        }
    }
    public void run() {
        while (true) {
            think();
            System.out.println("Philosopher "+ i+1 +" entering");
            d.enter();
            c.take(i);
            eat();
            c.release(i);
            d.exit();
            System.out.println("Philosopher "+ i+1 +" exiting");
        }
    }

}
