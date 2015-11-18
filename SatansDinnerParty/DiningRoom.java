package SatansDinnerParty;

/**
 * Created by André, Vegar, Jostein, Simon
 */

public class DiningRoom {
    private int availableSpaces;
    private int numberOfPhilosophers;

    public DiningRoom(int philosophers){
        this.numberOfPhilosophers = philosophers;
        availableSpaces = numberOfPhilosophers - 1;

    }

    /**
     * Methods that makes the philosopher enter the dining room
     * so that he can eat
     */
    public synchronized void enterDiningRoom(){
        while(availableSpaces == 0){
            try{
                wait();
            }catch(InterruptedException e){
            }
            availableSpaces--;
        }
    }

    /**
     * Method that makes the philosopher exit the dining room
     * so that other philosophers can enter and eat
     */
    public synchronized void exitDiningRoom(){
        availableSpaces++;
        notifyAll();
    }
}
