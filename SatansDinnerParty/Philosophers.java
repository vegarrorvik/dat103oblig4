package SatansDinnerParty;

/**
 * Created by Jostein on 11.11.2015.
 */
public class Philosophers extends Thread{
    int i;
    int minThinkTime,maxThinkTime,minEatTime,maxEatTime;
    private Chopsticks c;
    private DiningRoom d;

    public Philosophers(int index, int minThinkTime, int maxThinkTime, int minEatTime,
                        int maxEatTime, Chopsticks chopsticks, DiningRoom diningroom){
        this.i = index;
        this.minThinkTime = minThinkTime;
        this.maxThinkTime = maxThinkTime;
        this.minEatTime = minEatTime;
        this.maxEatTime = maxEatTime;
        this.c = chopsticks;
        this.d = diningroom;
    }

    /**
     * Metode som får filosofene til å tenke
     */
    public void think(){
        try{
            System.out.println("Filosof " + (i+1) + " tenker");
            Thread.sleep((int)(Math.random()*(maxThinkTime - minThinkTime))+minThinkTime);

        }catch(InterruptedException e){

        }
    }

    /**
     * Metode som får filosofene til å spise
     */
    public void eat(){
        try{
            System.out.println("Filosof " + (i+1) + " spiser");
            Thread.sleep((int)(Math.random()*(maxEatTime - minEatTime))+minEatTime);

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
            System.out.println("Filosof " + (i+1) + " går inn i spisestuen");
            c.takeChopsticks(i);
            eat();
            c.releaseChopsticks(i);
            d.exitDiningRoom();
            System.out.println("Filosof " + (i+1) + " går ut av spisestuen");
        }
    }

}
